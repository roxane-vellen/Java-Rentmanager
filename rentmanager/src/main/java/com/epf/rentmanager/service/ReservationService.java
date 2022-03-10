package com.epf.rentmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;

@Service
public class ReservationService {

	private ReservationDao reservationDao;

	@Autowired
	public ReservationService(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}

	public long create(Reservation reservation) throws ServiceException {
		try {
			this.reservationDao.create(reservation);
		} catch (DaoException e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	public long update(Reservation reservation) throws ServiceException {
		try {
			this.reservationDao.update(reservation);
		} catch (DaoException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public long delete(Reservation reservation) throws ServiceException {
		try {
			this.reservationDao.delete(reservation);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long delete(int id) throws ServiceException {
		try {
			this.reservationDao.delete(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Reservation findById(int id) throws ServiceException {
		try {
			return this.reservationDao.findById(id).get();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Reservation> findByClientId(int clientId) throws ServiceException {
		try {
			return this.reservationDao.findResaByClientId(clientId);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Reservation> findByVehicleId(int vehicleId) throws ServiceException {

		try {
			return this.reservationDao.findResaByVehicleId(vehicleId);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Reservation> findAll() throws ServiceException {

		try {
			return this.reservationDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int count() throws ServiceException {
		try {
			return this.reservationDao.count();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
