package com.epf.rentmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.dao.ClientDao;

@Service
public class ClientService {

	private ClientDao clientDao;

	@Autowired
	public ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	public int create(Client client) throws ServiceException {
		if (!client.isLegal()) {
			throw new ServiceException("L'utilisateur doit avoir au moins 18ans");		
		}
		else if(!client.isLastnameLong()) {
			throw new ServiceException("Le nom de l'utilisateur doit avoir au moins 3 caractères");	
		}
		else if(!client.isFirstnameLong()) {
			throw new ServiceException("Le prénom de l'utilisateur doit avoir au moins 3 caractères");	
		}
		else if(hasSameEmail(client)) {
			throw new ServiceException("L'email est déjà utilisé");	
		}
		else {
			try {
				this.clientDao.create(client);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}				
		return 0;		
	}

	public long update(Client client) throws ServiceException {
		if (!client.isLegal()) {
			throw new ServiceException("L'utilisateur doit avoir au moins 18ans");		
		}
		else if(!client.isLastnameLong()) {
			throw new ServiceException("Le nom de l'utilisateur doit avoir au moins 3 charactères");	
		}
		else if(!client.isFirstnameLong()) {
			throw new ServiceException("Le prénom de l'utilisateur doit avoir au moins 3 charactères");	
		}
		else if(hasSameEmail(client)) {
			throw new ServiceException("L'email est déjà utilisé");	
		}
		else {
			try {
				this.clientDao.update(client);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}	
		
		return 0;
	}

	public long delete(Client client) throws ServiceException {
		try {
			this.clientDao.delete(client);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long delete(int id) throws ServiceException {
		try {
			this.clientDao.delete(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Client findById(int id) throws ServiceException {
		try {
			return this.clientDao.findById(id).get();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Client> findAll() throws ServiceException {

		try {
			return this.clientDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int count() throws ServiceException {
		int count = 0;
		try {
			count = this.clientDao.count();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public boolean hasSameEmail(Client client) throws ServiceException {
		boolean sameEmail = false;
		try {
			sameEmail = this.clientDao.hasSameEmail(client);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return sameEmail;
	}

}
