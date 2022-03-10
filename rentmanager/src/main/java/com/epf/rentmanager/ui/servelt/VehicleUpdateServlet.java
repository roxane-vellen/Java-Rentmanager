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
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/cars/update")
public class VehicleUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static String Vue_Formulaire = "/WEB-INF/views/vehicles/update.jsp";
	
	private int id;

	@Autowired
	VehicleService vehicleService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		id = Integer.valueOf(request.getQueryString().substring(3));
		
        Vehicle vehicle = new Vehicle();
        try {
            vehicle = vehicleService.findById(id);
        } catch (ServiceException e) {
        	e.printStackTrace();
        }

        request.setAttribute("constructor", vehicle.getConstructor());
        request.setAttribute("modele", vehicle.getModel());
        request.setAttribute("nbSeats", vehicle.getNb_places());
        
        this.getServletContext().getRequestDispatcher(Vue_Formulaire).forward(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String Constructeur = request.getParameter("manufacturer");
		String Modele = request.getParameter("model");
		int Nb_places = Integer.parseInt(request.getParameter("seats"));		

		Vehicle vehicle = new Vehicle(id, Constructeur, Modele, Nb_places); 

		try {
			vehicleService.update(vehicle);

		} catch (ServiceException e) {
			e.printStackTrace();
		}
		response.sendRedirect("http://localhost:8080/rentmanager/cars");
	}

}
