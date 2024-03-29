package com.patient.billing.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bed")
public class BedEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bedId")
    private Long id;
	@Column(name="bedNo")
	private int bedNo;
	@Column(name = "status")
	private String status;
	@ManyToOne
	@JoinColumn(name = "roomId", referencedColumnName = "roomId")
	private RoomEntity roomId;
	
	public BedEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public BedEntity(Long id, int bedNo, String status, RoomEntity roomId) {
		super();
		this.id = id;
		this.bedNo = bedNo;
		this.status = status;
		this.roomId = roomId;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public RoomEntity getRoomId() {
		return roomId;
	}

	public void setRoomId(RoomEntity roomId) {
		this.roomId = roomId;
	}

	
	public int getBedNo() {
		return bedNo;
	}



	public void setBedNo(int bedNo) {
		this.bedNo = bedNo;
	}



	@Override
	public String toString() {
		return "BedEntity [id=" + id + ", bedNo=" + bedNo + ", status=" + status + ", roomId=" + roomId + "]";
	}




    	
	
	
}
