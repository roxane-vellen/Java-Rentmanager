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
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;


@Repository
public class ClientDao {
		
	private ClientDao() {}
		
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String UPDATE_CLIENT_QUERY = "UPDATE Client SET nom = ?, prenom = ?, email = ?, naissance = ? WHERE id= ?;";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String COUNT_CLIENTS_QUERY = "SELECT COUNT(id) AS count FROM Client;";
	private static final String FIND_EMAIL_QUERY = "SELECT id FROM Client WHERE email=?;";
	
	public void create(Client client) throws DaoException {
				
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(CREATE_CLIENT_QUERY);
						
			pstmt.setString(1, client.getLastname());
			pstmt.setString(2, client.getFirstname());
			pstmt.setString(3, client.getEmail());
			pstmt.setDate(4, Date.valueOf(client.getBirthdate()));			

			pstmt.executeUpdate();
			pstmt.close();
			conn.close();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Client client) throws DaoException {
		
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_CLIENT_QUERY);
			
			pstmt.setString(1, client.getLastname());
			pstmt.setString(2, client.getFirstname());
			pstmt.setString(3, client.getEmail());
			pstmt.setDate(4, Date.valueOf(client.getBirthdate()));
			pstmt.setInt(5, client.getId());

			pstmt.execute();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Client client) throws DaoException {
		
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_CLIENT_QUERY);
			
			pstmt.setInt(1, client.getId());			
						
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
			PreparedStatement pstmt = conn.prepareStatement(DELETE_CLIENT_QUERY);
			
			pstmt.setInt(1, id);			
						
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Optional<Client> findById(int id) throws DaoException {
			
		Client client = new Client();
		
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_CLIENT_QUERY);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				client.setId(id);
				client.setLastname(rs.getString("nom"));
				client.setFirstname(rs.getString("prenom"));
				client.setEmail(rs.getString("email"));
				client.setBirthdate(rs.getDate("naissance").toLocalDate());
			}
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(client);						
	}

	public List<Client> findAll() throws DaoException {
		
		List<Client> clientsList = new ArrayList<>();
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_CLIENTS_QUERY);
						
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int clientId = rs.getInt("id");
				String clientLastname = rs.getString("nom");
				String clientFirstname = rs.getString("prenom");
				String clientEmail = rs.getString("email");
				LocalDate clientBirthdate = rs.getDate("naissance").toLocalDate();
				
				Client client = new Client(clientId, clientLastname, clientFirstname, clientEmail, clientBirthdate);
				
				clientsList.add(client);
			}
			pstmt.close();
			conn.close();
									
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return clientsList;
	}
	
	public int count()  throws DaoException {
		int nbrClients = 0;
		
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(COUNT_CLIENTS_QUERY);
			
			ResultSet rs = pstmt.executeQuery();
			rs.last();			
			nbrClients = rs.getInt("count");
			
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nbrClients;
	}

	public boolean hasSameEmail(Client client) throws DaoException{
		boolean sameEmail = false;
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_EMAIL_QUERY);
			pstmt.setString(1, client.getEmail());
			
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				//if (resultSet.getInt("id") != client.getId()) {
				sameEmail = true;
				//}
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			throw new DaoException("L'email choisit existe déjà");
		}
		return sameEmail;
	}
}
