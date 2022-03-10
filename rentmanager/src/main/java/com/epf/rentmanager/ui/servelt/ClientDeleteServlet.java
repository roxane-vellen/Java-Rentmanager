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
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;

@WebServlet("/users/delete")
public class ClientDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	
	@Autowired
	ClientService clientService;
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
        try {
            
            List<Reservation> listReservations = new ArrayList<>();     
            listReservations = reservationService.findByClientId(id);
            
            List<Integer> listIdReservation = new ArrayList<>(); 
            for (Reservation reservation : listReservations){
            	listIdReservation.add(reservation.getId());
            }
            
            for (Integer idReservation : listIdReservation) {
            	reservationService.delete(idReservation);
            }
            
            clientService.delete(id);            
            
        } catch (ServiceException e) {
        	e.printStackTrace();
        }
        response.sendRedirect("http://localhost:8080/rentmanager/users");
    
	}	
	
}
