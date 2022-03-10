package com.epf.rentmanager.ui.servelt;

import java.io.IOException;

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

@WebServlet("/rents/details")
public class ReservationDetailsServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private static String Vue_Formulaire = "/WEB-INF/views/rents/details.jsp";
	
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
		
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
				
		int id = Integer.valueOf(request.getQueryString().substring(3));
		
		Reservation reservation = new Reservation();
		Client client = new Client();
		Vehicle vehicle = new Vehicle();
		
		try {
			reservation = reservationService.findById(id);
			client = clientService.findById(reservation.getIdclient());
			vehicle = vehicleService.findById(reservation.getIdvehicule());
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		        
        request.setAttribute("id", id);
        request.setAttribute("debut", reservation.getDebut());
        request.setAttribute("fin", reservation.getFin());
        
        request.setAttribute("idClient", client.getId());
        request.setAttribute("nom", client.getLastname());
        request.setAttribute("prenom", client.getFirstname());
        request.setAttribute("email", client.getEmail());
        request.setAttribute("naissance", client.getBirthdate());
                     
        request.setAttribute("idVehicle", vehicle.getId());
        request.setAttribute("constructor", vehicle.getConstructor());
        request.setAttribute("model", vehicle.getModel());
        request.setAttribute("seats", vehicle.getNb_places());

                
        this.getServletContext().getRequestDispatcher(Vue_Formulaire).forward(request, response);
		
		
	}
	
}