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

@WebServlet("/users/details")
public class ClientDetailsServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private static String Vue_Formulaire = "/WEB-INF/views/users/details.jsp";
	
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

        List<Vehicle> listVehicles = new ArrayList<>();
        List<Vehicle> listVehiclesSort = new ArrayList<>();
        List<Reservation> listReservations = new ArrayList<>();

        Client client = new Client();
        try {
            client = clientService.findById(id);
            listReservations = reservationService.findByClientId(id);
            
            for (int i = 0; i < listReservations.size(); i++) {
                listVehicles.add(vehicleService.findById(listReservations.get(i).getIdvehicule()));
            }
            
            if(!listVehicles.isEmpty()) {
            	
            	List<Integer> listId = new ArrayList<>();
            	
            	listVehiclesSort.add(listVehicles.get(0));
            	listId.add(listVehicles.get(0).getId());
            	
                if(listVehicles.size() > 1) {
                	for (int i = 1; i < listVehicles.size(); i++) {
                        if(!listId.contains(listVehicles.get(i).getId())) {
                        	listVehiclesSort.add(listVehicles.get(i));
                        }                	
                    }
                }
            }
                        
            
        } catch (ServiceException e) {
        	e.printStackTrace();
        }


        request.setAttribute("prenom", client.getFirstname());
        request.setAttribute("nom", client.getLastname());
        request.setAttribute("email", client.getEmail());

        request.setAttribute("reservations", listReservations);
        request.setAttribute("vehicles", listVehiclesSort);

        request.setAttribute("vehicleSize", listVehiclesSort.size());
        request.setAttribute("reservationSize", listReservations.size());
                
        this.getServletContext().getRequestDispatcher(Vue_Formulaire).forward(request, response);
		
		
	}
	
}

