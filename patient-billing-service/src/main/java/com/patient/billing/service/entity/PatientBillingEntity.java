package com.patient.billing.service.entity;



import java.time.LocalDate;
import java.util.Date;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.patient.billing.service.bean.BedAllocationBean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "billing")
public class PatientBillingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bill_id")
	private int billId;

	@Column(name = "bill_date")
	private LocalDate billingDate;
	
	@Column(name = "bed_allocation_id")
	private int bedAllocationId;
	@Column(name = "paid_amount")
	private double paidAmount;
	@Column(name = "discount")
	private double discount;  
	@Column(name = "total_amount")
	private double totalAmount;
	@Column(name = "payment_status")
	private String paymentStatus;
	@Column(name= "status")
	private String status;
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

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PatientBillingEntity(int billId, LocalDate billingDate, int bedAllocationId, double paidAmount,
			double discount, double totalAmount, String paymentStatus, String status) {
		super();
		this.billId = billId;
		this.billingDate = billingDate;
		this.bedAllocationId = bedAllocationId;
		this.paidAmount = paidAmount;
		this.discount = discount;
		this.totalAmount = totalAmount;
		this.paymentStatus = paymentStatus;
		this.status = status;
	}

	public PatientBillingEntity() {
		super();
	}

	@Override
	public String toString() {
		return "PatientBillingEntity [billId=" + billId + ", billingDate=" + billingDate + ", bedAllocationId="
				+ bedAllocationId + ", paidAmount=" + paidAmount + ", discount=" + discount + ", totalAmount="
				+ totalAmount + ", paymentStatus=" + paymentStatus + ", status=" + status + "]";
	}

	
}
