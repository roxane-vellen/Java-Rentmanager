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
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/cars")
public class VehicleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static String Vue_Formulaire = "/WEB-INF/views/vehicles/list.jsp";
	
	@Autowired
	VehicleService vehicleService;
	
	@Override
	public void init() throws ServletException {
	super.init();
	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			request.setAttribute("listVehicles", this.vehicleService.findAll());

			this.getServletContext().getRequestDispatcher(Vue_Formulaire).forward(request, response);

		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
