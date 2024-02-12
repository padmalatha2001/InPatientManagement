package com.patient.billing.service.service;

import java.util.List;
import java.util.Map;

import com.patient.billing.service.bean.BedAllocationBean;
import com.patient.billing.service.bean.PatientBean;
import com.patient.billing.service.bean.PatientBillingBean;


public interface PatientBillingService {
	public void save(PatientBillingBean patientBillingBean);
	public List<PatientBillingBean> getAll();
	public PatientBillingBean getById(Integer patientBillingId);
//    public PatientBillingEntity update(Integer billingId,Double paymentAmmount);
	BedAllocationBean getDetails(int bedId);
  PatientBean getPatitentDetails(int patitentId);
void update(PatientBillingBean patitentBean);
 // BillingDetailsBean getBillingDetails(int patientId,Integer patientBillingId);
List<Map<String, Object>> getAllDetails();
	
}
