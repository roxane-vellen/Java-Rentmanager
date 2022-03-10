package com.epf.rentmanager.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
	
	private int id;
	private int idclient;
	private String nameClient;
	private int idvehicule;
	private String nameVehicle;
	private LocalDate debut;
	private LocalDate fin;
	
	public Reservation() {}
	
	public Reservation(int id, int idclient, int idvehicule, LocalDate debut, LocalDate fin) {
		super();
		this.id = id;
		this.idclient = idclient;
		this.idvehicule = idvehicule;
		this.debut = debut;
		this.fin = fin;
	}
	
	public Reservation(int idclient, int idvehicule, LocalDate debut, LocalDate fin) {
		super();
		this.idclient = idclient;
		this.idvehicule = idvehicule;
		this.debut = debut;
		this.fin = fin;
	}
	
	public Reservation(int id, String nameClient, String nameVehicle, LocalDate debut, LocalDate fin) {
		super();
		this.id = id;
		this.nameClient = nameClient;
		this.nameVehicle = nameVehicle;
		this.debut = debut;
		this.fin = fin;
	}
		

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", idclient=" + idclient + ", idvehicule=" + idvehicule + ", debut=" + debut
				+ ", fin=" + fin + "]";
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdclient() {
		return idclient;
	}
	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}
	public int getIdvehicule() {
		return idvehicule;
	}
	public void setIdvehicule(int idvehicule) {
		this.idvehicule = idvehicule;
	}
	public LocalDate getDebut() {
		return debut;
	}
	public void setDebut(LocalDate debut) {
		this.debut = debut;
	}
	public LocalDate getFin() {
		return fin;
	}
	public void setFin(LocalDate fin) {
		this.fin = fin;
	}
	
	
	
	public String getNameClient() {
		return nameClient;
	}
	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}
	public String getNameVehicle() {
		return nameVehicle;
	}
	public void setNameVehicle(String nameVehicle) {
		this.nameVehicle = nameVehicle;
	}
	
	
	

	public int getNbDays() {
        int nbDays = (int) ChronoUnit.DAYS.between(this.getDebut(), this.getFin());
        return nbDays;
    }
	
	public boolean ismore7Days() {
		boolean ismore7Days = false;
		if(this.getNbDays() > 7) {
			ismore7Days = true;
		}
		return ismore7Days;
	}
		

}
