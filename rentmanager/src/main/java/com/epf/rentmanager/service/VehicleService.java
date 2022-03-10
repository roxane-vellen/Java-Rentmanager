package com.epf.rentmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.VehicleDao;

@Service
public class VehicleService {

	private VehicleDao vehicleDao;
	
	@Autowired
	public VehicleService(VehicleDao vehicleDao){
		this.vehicleDao = vehicleDao;
		}
	
	
	public int create(Vehicle vehicle) throws ServiceException {
		if(!vehicle.hasConstructor()) {
			throw new ServiceException("La voiture n'a pas de constructeur");	
		}
		else if(!vehicle.hasModel()) {
			throw new ServiceException("La voiture n'a pas de modele");	
		}
		if(!vehicle.rightNbPlaces()) {
			throw new ServiceException("La voiture n'a pas un nombre de place adéquat");	
		}
		else {
			try {
				this.vehicleDao.create(vehicle);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	public long update(Vehicle vehicle) throws ServiceException {
		if(!vehicle.hasConstructor()) {
			throw new ServiceException("La voiture n'a pas de constructeur");	
		}
		else if(!vehicle.hasModel()) {
			throw new ServiceException("La voiture n'a pas de modele");	
		}
		if(!vehicle.rightNbPlaces()) {
			throw new ServiceException("La voiture n'a pas un nombre de place adéquat");	
		}
		else {
			try {
				this.vehicleDao.update(vehicle);
			} catch (DaoException e) {
				e.printStackTrace();
			}			
		}
		return 0;		
	}

	public long delete(Vehicle vehicle) throws ServiceException {
		try {
			this.vehicleDao.delete(vehicle);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;		
	}
	
	public long delete(int id) throws ServiceException {
		try {
			this.vehicleDao.delete(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;		
	}
	
	public Vehicle findById(int id) throws ServiceException {
		try {
			return this.vehicleDao.findById(id).get();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Vehicle> findAll() throws ServiceException {
		try {
			return this.vehicleDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int count() throws ServiceException {
		try {
			return this.vehicleDao.count();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
