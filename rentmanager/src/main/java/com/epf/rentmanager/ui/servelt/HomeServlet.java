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
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/home")
public class HomeServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
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
		String Vue_Formulaire = "/WEB-INF/views/home.jsp";
		
		
					try {
						request.setAttribute("clientCount", this.clientService.count());
						request.setAttribute("vehicleCount", this.vehicleService.count());
						request.setAttribute("reservationCount", this.reservationService.count());
					} catch (ServiceException e) {
						e.printStackTrace();
					}
			
		this.getServletContext().getRequestDispatcher(Vue_Formulaire).forward(request, response);
		
	}
}
