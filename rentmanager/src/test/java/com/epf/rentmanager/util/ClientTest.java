package com.epf.rentmanager.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import com.epf.rentmanager.model.Client;

import org.junit.jupiter.api.Test;

public class ClientTest {

	@Test
	public void is_Legal_True() {
		// Given
		Client client = new Client("Smith", "John", "smith.john@mail.com", LocalDate.parse("1999-10-09"));
		// Then
		assertTrue(client.isLegal());
	}

	@Test
	public void is_Legal_False() {
		// Given
		Client client = new Client("Smith", "John", "smith.john@mail.com", LocalDate.now());
		// Then
		assertFalse(client.isLegal());
	}

	@Test
	public void is_Lastname_Long_True() {
		// Given
		Client client = new Client("Smith", "John", "smith.john@mail.com", LocalDate.parse("1999-10-09"));
		// Then
		assertTrue(client.isLastnameLong());
	}

	@Test
	public void is_Lastname_Long_False() {
		// Given
		Client client = new Client("Sm", "John", "smith.john@mail.com", LocalDate.parse("1999-10-09"));
		// Then
		assertFalse(client.isLastnameLong());
	}

	@Test
	public void is_Firstname_Long_True() {
		// Given
		Client client = new Client("Smith", "John", "smith.john@mail.com", LocalDate.parse("1999-10-09"));
		// Then
		assertTrue(client.isFirstnameLong());
	}

	@Test
	public void is_Firstname_Long_False() {
		// Given
		Client client = new Client("Smith", "Jo", "smith.john@mail.com", LocalDate.parse("1999-10-09"));
		// Then
		assertFalse(client.isFirstnameLong());
	}
	
	@Test
	public void is_Email_Right_True() {
		// Given
		Client client = new Client("Smith", "John", "smith.john@mail.com", LocalDate.parse("1999-10-09"));
		// Then
		assertTrue(client.isEmailRight());
	}
	
	@Test
	public void is_Email_Right_False() {
		// Given
		Client client = new Client("Smith", "John", "smith.john@mailcom", LocalDate.parse("1999-10-09"));
		// Then
		assertFalse(client.isEmailRight());
	}
}
