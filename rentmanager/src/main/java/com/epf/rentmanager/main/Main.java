package com.epf.rentmanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.appconfiguration.AppConfiguration;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.persistence.FillDatabase;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

public class Main {
	
	@Autowired
    ClientService clientService;
    @Autowired
    VehicleService vehicleService;
    @Autowired 
    ReservationService reservationService;

    public void init() throws InterruptedException {
        super.wait();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        ClientService clientService = context.getBean(ClientService.class);
        VehicleService vehicleService = context.getBean(VehicleService.class);
        ReservationService reservationService = context.getBean(ReservationService.class);
        
//        LocalDate date = LocalDate.parse("2000-01-30");
//    	Client client = new Client("NomTest4", "PrenomTest4", "prenomtest.nomtest@mail.com", date);
//    	
//    	List<Client> listClients = new ArrayList<>();
        
//        Vehicle vehicle = new Vehicle("constructeur1", "modele1", 2);
//        List<Vehicle> listVehicles = new ArrayList<>();
//        
//    	
//    	try {
//			int id = vehicleService.create(vehicle);
//			
//			listVehicles = vehicleService.findAll();
//    		
//			System.out.println(listVehicles);
//			
//			
//		} catch (ServiceException e) {
//			e.printStackTrace();
//		}
    	
    }
}
