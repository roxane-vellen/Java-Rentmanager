package com.epf.rentmanager.uni;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import com.epf.rentmanager.model.Reservation;

import org.junit.jupiter.api.Test;

public class ReservationTest {

	@Test
	void is_more_than_7_days_True() {
		// Given
		Reservation reservation = new Reservation(1, 1, 1, LocalDate.parse("2000-07-01"), LocalDate.parse("2000-07-30"));
		// Then
		assertTrue(reservation.ismore7Days());
	}
	
	@Test
	void is_more_than_7_days_False() {
		// Given
		Reservation reservation = new Reservation(1, 1, 1, LocalDate.parse("2000-07-01"), LocalDate.parse("2000-07-02"));
		// Then
		assertFalse(reservation.ismore7Days());
	}
}
