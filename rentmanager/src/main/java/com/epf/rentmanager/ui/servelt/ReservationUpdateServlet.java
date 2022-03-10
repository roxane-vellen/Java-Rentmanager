package com.epf.rentmanager.ui.servelt;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/rents/update")
public class ReservationUpdateServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private static String Vue_Formulaire = "/WEB-INF/views/rents/update.jsp";
	
	private int id;
	
	@Autowired
    ClientService clientService;
	@Autowired
    VehicleService vehicleService;
    @Autowired
    ReservationService reservationService;
    
    @Override
	public void init() throws ServletException {
	super.init();
	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	id = Integer.valueOf(request.getQueryString().substring(3));
    	
    	Reservation reservation = new Reservation();
    	
        List<Vehicle> listVehicles = new ArrayList<>();
        List<Client> listClients = new ArrayList<>();
        
        try {
        	reservation = reservationService.findById(id);
        	
        	listVehicles = vehicleService.findAll();        	
        	Vehicle vehicle = vehicleService.findById(reservation.getIdvehicule());
        	listVehicles.remove(vehicle);
        	listVehicles.add(0, vehicle);
        	

        	listClients = clientService.findAll();
        	Client client = clientService.findById(reservation.getIdclient());
        	listClients.remove(client);
        	listClients.add(0, client);
        	       	
        	        	
        } catch (ServiceException e) {
        	e.printStackTrace();
        }
        request.setAttribute("vehicles", listVehicles);   
        request.setAttribute("clients", listClients);        
        request.setAttribute("debut", reservation.getDebut());
        request.setAttribute("fin", reservation.getFin());       
        
        this.getServletContext().getRequestDispatcher(Vue_Formulaire).forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int client_id = Integer.valueOf(request.getParameter("client"));
        int vehicule_id = Integer.valueOf(request.getParameter("car"));
        LocalDate debut = LocalDate.parse(request.getParameter("begin"));
        LocalDate fin = LocalDate.parse(request.getParameter("end"));

        Reservation reservation = new Reservation(id, client_id, vehicule_id, debut, fin);
        
        try {
            reservationService.update(reservation);
        } catch (ServiceException e) {
        	e.printStackTrace();
        }
        response.sendRedirect("http://localhost:8080/rentmanager/rents"); 

    }

}
