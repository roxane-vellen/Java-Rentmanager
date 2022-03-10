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

@WebServlet("/rents")
public class ReservationListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static String Vue_Formulaire = "/WEB-INF/views/rents/list.jsp";
	
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			List<Reservation> listReservations = reservationService.findAll();
			List<Reservation> listReservationsWithName = new ArrayList<>();
			
			for (Reservation reservation : listReservations) {	
				
				Client client = clientService.findById(reservation.getIdclient());
				Vehicle vehicle = vehicleService.findById(reservation.getIdvehicule());
				
				String nameClient = client.getLastname() + " " + client.getFirstname();
				String nameVehicle = vehicle.getConstructor() + " " + vehicle.getModel();				
				
				Reservation reservationWithName = new Reservation(reservation.getId(), nameClient, nameVehicle, reservation.getDebut(), reservation.getFin());
				listReservationsWithName.add(reservationWithName);
			}			
			
			request.setAttribute("listReservations", listReservationsWithName);

			this.getServletContext().getRequestDispatcher(Vue_Formulaire).forward(request, response);

		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
