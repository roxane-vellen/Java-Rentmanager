package com.epf.rentmanager.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epf.rentmanager.model.Vehicle;

import org.junit.jupiter.api.Test;

public class VehicleTest {

	@Test
	public void has_Constructor_True() {
		// Given
		Vehicle vehicule = new Vehicle(1, "Renautlt", "clio", 4);
		// Then
		assertTrue(vehicule.hasConstructor());
	}

	@Test
	public void has_Constructor_False() {
		// Given
		Vehicle vehicule = new Vehicle(1, null, "clio", 4);
		// Then
		assertFalse(vehicule.hasConstructor());
	}
	
	@Test
	public void has_Model_True() {
		// Given
		Vehicle vehicule = new Vehicle(1, "Renautlt", "clio", 4);
		// Then
		assertTrue(vehicule.hasModel());
	}

	@Test
	public void has_Model_False() {
		// Given
		Vehicle legalVehicule = new Vehicle(1, "Renautlt", null, 4);
		// Then
		assertFalse(legalVehicule.hasModel());
	}

	@Test
	public void right_NbPlaces_True() {
		// Given
		Vehicle vehicule = new Vehicle(1, "Renautlt", "clio", 4);
		// Then
		assertTrue(vehicule.rightNbPlaces());
	}

	@Test
	public void right_NbPlaces_False() {
		// Given
		Vehicle vehicule = new Vehicle(1, "Renautlt", "clio", 1);
		// Then
		assertFalse(vehicule.rightNbPlaces());
	}
}
