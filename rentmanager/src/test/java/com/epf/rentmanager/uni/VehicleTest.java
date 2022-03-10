package com.epf.rentmanager.uni;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epf.rentmanager.model.Vehicle;

import org.junit.jupiter.api.Test;

public class VehicleTest {

	@Test
	void has_Proper_Constructor_True() {
		// Given
		Vehicle legalVehicule = new Vehicle(1, "Renautlt", "clio", 4);
		// Then
		assertTrue(legalVehicule.hasConstructor());
	}

	@Test
	void has_Proper_Constructeur_False() {
		// Given
		Vehicle legalVehicule = new Vehicle(1, null, "clio", 4);
		// Then
		assertFalse(legalVehicule.hasConstructor());
	}
	
	@Test
	void has_Proper_Model_True() {
		// Given
		Vehicle legalVehicule = new Vehicle(1, "Renautlt", "clio", 4);
		// Then
		assertTrue(legalVehicule.hasModel());
	}

	@Test
	void has_Proper_Model_False() {
		// Given
		Vehicle legalVehicule = new Vehicle(1, "Renautlt", null, 4);
		// Then
		assertFalse(legalVehicule.hasModel());
	}

	@Test
	void has_Proper_NbPlaces_True() {
		// Given
		Vehicle legalVehicule = new Vehicle(1, "Renautlt", "clio", 4);
		// Then
		assertTrue(legalVehicule.rightNbPlaces());
	}

	@Test
	void has_Proper_NbPlaces_False() {
		// Given
		Vehicle legalVehicule = new Vehicle(1, "Renautlt", "clio", 1);
		// Then
		assertFalse(legalVehicule.rightNbPlaces());
	}
}
