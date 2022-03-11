package com.epf.rentmanager.ui.servelt;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

@WebServlet("/users/update")
public class ClientUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static String Vue_Formulaire = "/WEB-INF/views/users/update.jsp";
	
	private int id;
	
	@Autowired
	ClientService clientService;
	
	@Override
	public void init() throws ServletException {
	super.init();
	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
				
		id = Integer.valueOf(request.getQueryString().substring(3));
		
        Client client = new Client();
        try {
            client = clientService.findById(id);
        } catch (ServiceException e) {
        	e.printStackTrace();
        }

        request.setAttribute("nom", client.getLastname());
        request.setAttribute("prenom", client.getFirstname());
        request.setAttribute("email", client.getEmail());
        request.setAttribute("naissance", client.getBirthdate());
        
        this.getServletContext().getRequestDispatcher(Vue_Formulaire).forward(request, response);
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nom = request.getParameter("last_name");
        String prenom = request.getParameter("first_name");
        String email = request.getParameter("email");
        LocalDate naissance = LocalDate.parse(request.getParameter("birthdate"));

        Client client = new Client(id, nom, prenom, email, naissance);

        try {
            clientService.update(client);
        } catch (ServiceException e) {
        	e.printStackTrace();
        }
        response.sendRedirect("http://localhost:8080/rentmanager/users");
        
    }
	
}
