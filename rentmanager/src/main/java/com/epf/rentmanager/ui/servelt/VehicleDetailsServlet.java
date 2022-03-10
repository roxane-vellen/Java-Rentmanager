package com.epf.rentmanager.ui.servelt;

import java.io.IOException;
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

@WebServlet("/cars/details")
public class VehicleDetailsServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private static String Vue_Formulaire = "/WEB-INF/views/vehicles/details.jsp";
	
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

        List<Client> listClients = new ArrayList<>();
        List<Client> listClientsSort = new ArrayList<>();
        List<Reservation> listReservations = new ArrayList<>();

        Vehicle vehicle = new Vehicle();
        try {
            vehicle = vehicleService.findById(id);
            listReservations = reservationService.findByVehicleId(id);
            for (int i = 0; i < listReservations.size(); i++) {
                listClients.add(clientService.findById(listReservations.get(i).getIdclient()));
            }
            
            if(!listClients.isEmpty()) {
            	
            	List<Integer> listId = new ArrayList<>();
            	
            	listClientsSort.add(listClients.get(0));
            	listId.add(listClients.get(0).getId());
            	
                if(listClients.size() > 1) {
                	for (int i = 1; i < listClients.size(); i++) {
                        if(!listId.contains(listClients.get(i).getId())) {
                        	listClientsSort.add(listClients.get(i));
                        }                	
                    }
                }
            }
                        
            
        } catch (ServiceException e) {
        	e.printStackTrace();
        }


        request.setAttribute("constructeur", vehicle.getConstructor());
        request.setAttribute("modele", vehicle.getModel());

        request.setAttribute("reservations", listReservations);
        request.setAttribute("clients", listClientsSort);

        request.setAttribute("clientSize", listClientsSort.size());
        request.setAttribute("reservationSize", listReservations.size());
                
        this.getServletContext().getRequestDispatcher(Vue_Formulaire).forward(request, response);
		
		
	}
	
}
