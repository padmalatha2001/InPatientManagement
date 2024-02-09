package com.patient.billing.service.bean;



public class RoomTypeBean {
	private long id;
	private String name;
	private int roomSharing;
	private double roomPrice;
	private WardBean wardId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRoomSharing() {
		return roomSharing;
	}
	public void setRoomSharing(int roomSharing) {
		this.roomSharing = roomSharing;
	}
	public double getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}
	public WardBean getWardId() {
		return wardId;
	}
	public void setWardId(WardBean wardId) {
		this.wardId = wardId;
	}
	public RoomTypeBean(long id, String name, int roomSharing, double roomPrice, WardBean wardId) {
		super();
		this.id = id;
		this.name = name;
		this.roomSharing = roomSharing;
		this.roomPrice = roomPrice;
		this.wardId = wardId;
	}
	public RoomTypeBean() {
		super();
	}


}
