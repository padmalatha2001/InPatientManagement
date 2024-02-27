package com.patient.billing.service.dto;

import java.sql.Date;

import com.patient.billing.service.entity.BedEntity;
import com.patient.billing.service.entity.RoomEntity;
import com.patient.billing.service.entity.RoomType;

public class BedAllocationDto {

	
	public BedAllocationDto() {
		super();
	}

	private String firstName;
	private String lastName;
	private int patientAge;
	private String patientGender;
	private long patientContactNo;
	private int noOfDays;
	private int id;
	private Date startDate;
	private Date endDate;
	private BedEntity bedId;
	private String status;
	private double paidAmount;
	private double totalAmount;
	public BedAllocationDto(String firstName, String lastName, int patientAge, String patientGender,
			long patientContactNo,  int noOfDays, int id, Date startDate, Date endDate,
			BedEntity bedId, String status, double paidAmount, double totalAmount) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.patientAge = patientAge;
		this.patientGender = patientGender;
		this.patientContactNo = patientContactNo;
		this.noOfDays = noOfDays;
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.bedId = bedId;
		this.status = status;
		this.paidAmount = paidAmount;
		this.totalAmount = totalAmount;
		//[java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, java.lang.Long, 
//		 java.lang.Integer, java.lang.Integer, java.sql.Date, java.sql.Date, com.patient.billing.service.entity.BedEntity, 
//		 java.lang.String, java.lang.Double, 
//		 java.lang.Double], and not every argument has an alias)
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
	public int getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}
	public String getPatientGender() {
		return patientGender;
	}
	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}
	public long getPatientContactNo() {
		return patientContactNo;
	}
	public void setPatientContactNo(long patientContactNo) {
		this.patientContactNo = patientContactNo;
	}	
	public int getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public BedEntity getBedId() {
		return bedId;
	}
	public void setBedId(BedEntity bedId) {
		this.bedId = bedId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}	
	
//	 [java.lang.String, java.lang.String, java.lang.Integer, java.lang.Character, java.lang.Long, java.lang.Long, java.lang.Integer, java.lang.Integer, java.sql.Date, java.sql.Date,
//	  com.patient.billing.service.entity.BedEntity, java.lang.String], and not every argument has an alias)
	
}
