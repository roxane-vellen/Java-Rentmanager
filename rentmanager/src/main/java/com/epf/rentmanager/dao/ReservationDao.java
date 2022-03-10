package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class ReservationDao {

	private ReservationDao() {}

	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String UPDATE_RESERVATION_QUERY = "UPDATE Reservation SET client_id = ?, vehicle_id = ?, debut = ?, fin = ? WHERE id = ?;";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATION_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String COUNT_RESERVATIONS_QUERY = "SELECT COUNT(id) AS count FROM Reservation;";
		
	public void create(Reservation reservation) throws DaoException {
				
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(CREATE_RESERVATION_QUERY);
								
			pstmt.setInt(1, reservation.getIdclient());
			pstmt.setInt(2, reservation.getIdvehicule());
			pstmt.setDate(3, Date.valueOf(reservation.getDebut()));
			pstmt.setDate(4, Date.valueOf(reservation.getFin()));			

			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public void update(Reservation reservation) throws DaoException {
		
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_RESERVATION_QUERY);
			
			pstmt.setInt(1, reservation.getIdclient());
			pstmt.setInt(2, reservation.getIdvehicule());
			pstmt.setDate(3, Date.valueOf(reservation.getDebut()));
			pstmt.setDate(4, Date.valueOf(reservation.getFin()));
			pstmt.setInt(5, reservation.getId());
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Reservation reservation) throws DaoException {
		
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_RESERVATION_QUERY);
			
			pstmt.setInt(1, reservation.getId());			
						
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id) throws DaoException {
		
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_RESERVATION_QUERY);
			
			pstmt.setInt(1, id);			
						
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Optional<Reservation> findById(int id) throws DaoException {	
		Reservation reservation = new Reservation();

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATION_QUERY);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				reservation.setId(id);
				reservation.setIdclient(rs.getInt("client_id"));
				reservation.setIdvehicule(rs.getInt("vehicle_id"));
				reservation.setDebut(rs.getDate("debut").toLocalDate());
				reservation.setFin(rs.getDate("fin").toLocalDate());
			}
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Optional.ofNullable(reservation);
	}
	
	public List<Reservation> findResaByClientId(int clientId) throws DaoException {
		List<Reservation> reservationsList = new ArrayList<>();
		
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			
			pstmt.setInt(1, clientId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int reservationId = rs.getInt("id");
				int reservationVehicleId = rs.getInt("vehicle_id");
				LocalDate reservationDebut = rs.getDate("debut").toLocalDate();
				LocalDate reservationFin = rs.getDate("fin").toLocalDate();				
				
				Reservation reservation = new Reservation(reservationId, clientId, reservationVehicleId, reservationDebut, reservationFin);
				
				reservationsList.add(reservation);
			}
			pstmt.close();		
			conn.close();			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return reservationsList;
	}
	
	public List<Reservation> findResaByVehicleId(int vehicleId) throws DaoException {
		List<Reservation> reservationsList = new ArrayList<>();
		
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			
			pstmt.setInt(1, vehicleId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int reservationId = rs.getInt("id");
				int reservationClientId = rs.getInt("client_id");
				LocalDate reservationDebut = rs.getDate("debut").toLocalDate();
				LocalDate reservationFin = rs.getDate("fin").toLocalDate();				
				
				Reservation reservation = new Reservation(reservationId, reservationClientId, vehicleId, reservationDebut, reservationFin);
				
				reservationsList.add(reservation);
			}			
			pstmt.close();
			conn.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return reservationsList;
		
	}

	public List<Reservation> findAll() throws DaoException {
		
		List<Reservation> reservationsList = new ArrayList<>();
		try {
			Connection conn = ConnectionManager.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_QUERY);
						
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int reservationId = rs.getInt("id");
				int reservationClientId = rs.getInt("client_id");
				int reservationVehicleId = rs.getInt("vehicle_id");
				LocalDate reservationDebut = rs.getDate("debut").toLocalDate();
				LocalDate reservationFin = rs.getDate("fin").toLocalDate();				
				
				Reservation reservation = new Reservation(reservationId, reservationClientId, reservationVehicleId, reservationDebut, reservationFin);
				
				reservationsList.add(reservation);
			}
			pstmt.close();
			conn.close();
									
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reservationsList;
	}
	
	public int count()  throws DaoException {
		int nbrReservations = 0;
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(COUNT_RESERVATIONS_QUERY);
			
			ResultSet rs = pstmt.executeQuery();
			rs.last();			
			nbrReservations = rs.getInt("count");	
			
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nbrReservations;
	}
}
