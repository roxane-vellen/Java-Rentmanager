package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class VehicleDao {

	private VehicleDao() {
	}

	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES(?, ?, ?);";
	private static final String UPDATE_VEHICLE_QUERY = "UPDATE Vehicle SET constructeur = ?, modele = ?, nb_places = ? WHERE id= ?;";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle;";
	private static final String COUNT_VEHICLES_QUERY = "SELECT COUNT(id) AS count FROM Vehicle;";

	public void create(Vehicle vehicle) throws DaoException {
			
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(CREATE_VEHICLE_QUERY);
						
			pstmt.setString(1, vehicle.getConstructor());
			pstmt.setString(2, vehicle.getModel());
			pstmt.setInt(3, vehicle.getNb_places());

			pstmt.executeUpdate();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Vehicle vehicle) throws DaoException {
		
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_VEHICLE_QUERY);
			
			pstmt.setString(1, vehicle.getConstructor());
			pstmt.setString(2, vehicle.getModel());
			pstmt.setInt(3, vehicle.getNb_places());
			pstmt.setInt(4, vehicle.getId());

			pstmt.executeUpdate();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Vehicle vehicle) throws DaoException {

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_VEHICLE_QUERY);

			pstmt.setInt(1, vehicle.getId());

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
			PreparedStatement pstmt = conn.prepareStatement(DELETE_VEHICLE_QUERY);

			pstmt.setInt(1, id);

			pstmt.executeUpdate();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Optional<Vehicle> findById(int id) throws DaoException {
		Vehicle vehicle = new Vehicle();

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_VEHICLE_QUERY);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				vehicle.setId(id);
				vehicle.setConstructor(rs.getString("constructeur"));
				vehicle.setModel(rs.getString("modele"));
				vehicle.setNb_places(rs.getInt("nb_places"));

			}
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			throw new DaoException();
		}
		return Optional.ofNullable(vehicle);
	}

	public List<Vehicle> findAll() throws DaoException {

		List<Vehicle> vehiclesList = new ArrayList<>();
		try {
			Connection conn = ConnectionManager.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(FIND_VEHICLES_QUERY);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int vehicleId = rs.getInt("id");
				String vehicleConstructor = rs.getString("constructeur");
				String vehicleModel = rs.getString("modele");
				int vehicleNbrPLace = rs.getInt("nb_places");

				Vehicle vehicle = new Vehicle(vehicleId, vehicleConstructor, vehicleModel, vehicleNbrPLace);

				vehiclesList.add(vehicle);
			}
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vehiclesList;
	}

	public int count() throws DaoException {
		int nbrVehicles = 0;
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(COUNT_VEHICLES_QUERY);

			ResultSet rs = pstmt.executeQuery();
			rs.last();
			nbrVehicles = rs.getInt("count");
			
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nbrVehicles;
	}

}
