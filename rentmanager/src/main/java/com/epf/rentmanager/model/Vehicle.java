package com.epf.rentmanager.model;

public class Vehicle {

	private int id;
	private String constructor;
	private String model;
	private int nb_places;
	
	public Vehicle() {
		
	}
	
	public Vehicle(int id, String constructor, String model, int nb_places) {
		super();
		this.id = id;
		this.constructor = constructor;
		this.model = model;
		this.nb_places = nb_places;
	}
	
	public Vehicle(String constructor, String model, int nb_places) {
		super();
		this.constructor = constructor;
		this.model = model;
		this.nb_places = nb_places;
	}
	
	public Vehicle(int id, String constructor, int nb_places) {
		super();
		this.id = id;
		this.constructor = constructor;
		this.nb_places = nb_places;
	}
	
	
	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", constructor=" + constructor + ", model=" + model + ", nb_places=" + nb_places
				+ "]";
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getConstructor() {
		return constructor;
	}
	public void setConstructor(String constructor) {
		this.constructor = constructor;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getNb_places() {
		return nb_places;
	}
	public void setNb_places(int nb_places) {
		this.nb_places = nb_places;
	}
	
	public boolean hasConstructor() {
		boolean hasConstructor = false;
		if(!this.getConstructor().isBlank()) {
			hasConstructor = true;
		}
		return hasConstructor;
	}
	
	public boolean hasModel() {
		boolean hasModel = false;
		if(!this.getModel().isBlank()) {
			hasModel = true;
		}
		return hasModel;
	}
	
	public boolean rightNbPlaces() {
		boolean rightNbPlaces = false;
		if(this.getNb_places() >= 2 && this.getNb_places() <= 9) {
			rightNbPlaces = true;
		}
		return rightNbPlaces;
	}
	
}
