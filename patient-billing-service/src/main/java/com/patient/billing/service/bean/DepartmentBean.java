package com.patient.billing.service.bean;

public class DepartmentBean {

	private long id;
	private String name;
	
	public DepartmentBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DepartmentBean(long id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return "DepartmentBean [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
