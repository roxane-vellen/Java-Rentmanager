package com.epf.rentmanager.model;

import java.time.LocalDate;
import java.time.Period;

public class Client {

	private int id;
	private String lastname;
	private String firstname;
	private String email;
	private LocalDate birthdate;

	public Client() {
	}

	public Client(int id, String lastname, String firstname, String email, LocalDate birthdate) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.birthdate = birthdate;
	}

	public Client(String lastname, String firstname, String email, LocalDate birthdate) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", birthdate=" + birthdate + "]";
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public int getAge() {
		LocalDate now = LocalDate.now();
		return Period.between(this.birthdate, now).getYears();
	}

	public boolean isLegal() {
		return this.getAge() >= 18;
	}

	public boolean isLastnameLong() {
		if (this.getLastname().length() >= 3) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isFirstnameLong() {
		if (this.getFirstname().length() >= 3) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEmailRight() {
		
		if (this.getEmail().contains("@") && !this.getEmail().contains(" ")) {
			
			int indexA = this.getEmail().indexOf("@");
			int indexP = this.getEmail().lastIndexOf(".");
			if (indexA < indexP ) {	
				return true; 
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

}
