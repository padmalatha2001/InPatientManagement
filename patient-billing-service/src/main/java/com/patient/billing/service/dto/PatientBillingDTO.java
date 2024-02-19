package com.patient.billing.service.dto;

import java.time.LocalDate;

import jakarta.persistence.Column;

public class PatientBillingDTO {

	private int billId;

	private LocalDate billingDate;

	private int bedAllocationId;

	private double paidAmount;

	private double discount;

	private double totalAmount;
	private String paymentStatus;
	private String firstName;
	private String lastName;
	public PatientBillingDTO(int billId, LocalDate billingDate, int bedAllocationId, double paidAmount, double discount,
			double totalAmount, String paymentStatus, String firstName, String lastName) {
		super();
		this.billId = billId;
		this.billingDate = billingDate;
		this.bedAllocationId = bedAllocationId;
		this.paidAmount = paidAmount;
		this.discount = discount;
		this.totalAmount = totalAmount;
		this.paymentStatus = paymentStatus;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public LocalDate getBillingDate() {
		return billingDate;
	}
	public void setBillingDate(LocalDate billingDate) {
		this.billingDate = billingDate;
	}
	public int getBedAllocationId() {
		return bedAllocationId;
	}
	public void setBedAllocationId(int bedAllocationId) {
		this.bedAllocationId = bedAllocationId;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
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
	public PatientBillingDTO() {
		super();
	}

	

}
