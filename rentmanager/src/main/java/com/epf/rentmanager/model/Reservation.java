package com.epf.rentmanager.model;

import java.time.LocalDate;

public class Reservation {
	
	private int id;
	private int idclient;
	private int idvehicule;
	private LocalDate debut;
	private LocalDate fin;
	
	public Reservation() {
		
	}
	
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
	
	
	

}
