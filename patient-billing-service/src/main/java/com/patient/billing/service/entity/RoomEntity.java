package com.patient.billing.service.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "room")
public class RoomEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roomId")
	private long id;
	@Column(name = "roomNo")
	private int roomNo;
	@Column(name = "roomSharing")
	private int roomSharing;
	@Column(name = "roomPrice")
	private double roomPrice;
	@ManyToOne
	@JoinColumn(name = "roomTypeId", referencedColumnName = "roomTypeId")
	private RoomType roomTypeId;
	@ManyToOne
	@JoinColumn(name = "wardId", referencedColumnName = "wardId")
	private Ward wardId;
	
	public RoomEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoomEntity(long id, int roomNo, int roomSharing, double roomPrice, RoomType roomTypeId, Ward wardId) {
		super();
		this.id = id;
		this.roomNo = roomNo;
		this.roomSharing = roomSharing;
		this.roomPrice = roomPrice;
		this.roomTypeId = roomTypeId;
		this.wardId = wardId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
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

	public RoomType getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(RoomType roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public Ward getWardId() {
		return wardId;
	}

	public void setWardId(Ward wardId) {
		this.wardId = wardId;
	}

	@Override
	public String toString() {
		return "RoomEntity [id=" + id + ", roomNo=" + roomNo + ", roomSharing=" + roomSharing + ", roomPrice="
				+ roomPrice + ", roomTypeId=" + roomTypeId + ", wardId=" + wardId + "]";
	}
	
	
   
	

}
