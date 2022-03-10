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

@WebServlet("/rents/create")
public class ReservationCreateServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private static String Vue_Formulaire = "/WEB-INF/views/rents/create.jsp";
	
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

        List<Vehicle> listVehicles = new ArrayList<>();
        try {
        	listVehicles = vehicleService.findAll();
        } catch (ServiceException e) {
        	e.printStackTrace();
        }
        request.setAttribute("vehicles", listVehicles);

        List<Client> listClients = new ArrayList<>();
        try {
        	listClients = clientService.findAll();
        } catch (ServiceException e) {
        	e.printStackTrace();
        }
        request.setAttribute("clients", listClients);
       
        
        this.getServletContext().getRequestDispatcher(Vue_Formulaire).forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int client_id = Integer.valueOf(request.getParameter("client"));
        int vehicule_id = Integer.valueOf(request.getParameter("car"));
        LocalDate debut = LocalDate.parse(request.getParameter("begin"));
        LocalDate fin = LocalDate.parse(request.getParameter("end"));

        Reservation reservation = new Reservation(client_id, vehicule_id, debut, fin);
        
        try {
            reservationService.create(reservation);
        } catch (ServiceException e) {
        	e.printStackTrace();
        }
        response.sendRedirect("http://localhost:8080/rentmanager/rents"); 

    }

}
