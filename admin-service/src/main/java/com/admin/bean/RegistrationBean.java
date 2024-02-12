package com.admin.bean;

import java.sql.Date;

public class RegistrationBean {
	private int id;
	private String firstName;
	private String lastName;
	private Date birthDay;
	private String email;
	private String gender;
	private long phoneNumber;
	private String serviceType;
	private String password;
	
	


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public RegistrationBean(int id, String firstName, String lastName, Date birthDay, String email, String gender,
			long phoneNumber, String serviceType, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDay = birthDay;
		this.email = email;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.serviceType = serviceType;
		this.password = password;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public RegistrationBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}


	@Override
	public String toString() {
		return "RegistrationBean [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDay="
				+ birthDay + ", email=" + email + ", gender=" + gender + ", phoneNumber=" + phoneNumber
				+ ", serviceType=" + serviceType + "]";
	}
	

}
