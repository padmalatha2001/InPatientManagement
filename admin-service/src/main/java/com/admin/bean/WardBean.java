package com.admin.bean;

public class WardBean {
	private long id;
	private String name;
	private int capacity;
	private int availability;
	private DepartmentBean departmentId;
	
	
	public WardBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public WardBean(long id, String name, int capacity, int availability, DepartmentBean departmentId) {
		super();
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.availability = availability;
		this.departmentId = departmentId;
	}


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
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getAvailability() {
		return availability;
	}
	public void setAvailability(int availability) {
		this.availability = availability;
	}
	
	
	public DepartmentBean getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(DepartmentBean departmentId) {
		this.departmentId = departmentId;
	}
	
	@Override
	public String toString() {
		return "WardBean [id=" + id + ", name=" + name + ", capacity=" + capacity + ", availability=" + availability
				+ ", departmentId=" + departmentId + "]";
	}
	
	
}
	