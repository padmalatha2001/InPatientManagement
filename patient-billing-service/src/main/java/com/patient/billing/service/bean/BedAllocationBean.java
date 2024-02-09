package com.patient.billing.service.bean;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BedAllocationBean {
	private int id;
	private int patientId;
	private int noOfDays;
	//private RoomTypeBean roomTypeId;
	private RoomTypeBean roomTypeId;
	private Date startDate;
	private Date endDate;
	private String status;
	public BedAllocationBean(int id, int patientId, int noOfDays,
			com.patient.billing.service.bean.RoomTypeBean roomTypeId, Date startDate, Date endDate, String status) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.noOfDays = noOfDays;
		this.roomTypeId = roomTypeId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}
	public RoomTypeBean getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(RoomTypeBean roomTypeId) {
		this.roomTypeId = roomTypeId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BedAllocationBean() {
		super();
	}
	
	
	
}
