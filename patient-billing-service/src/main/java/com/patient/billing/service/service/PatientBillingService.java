package com.patient.billing.service.service;

import java.util.List;
import java.util.Optional;

import com.patient.billing.service.bean.BedAllocationBean;
import com.patient.billing.service.bean.PatientBillingBean;
import com.patient.billing.service.entity.PatientBillingEntity;


public interface PatientBillingService {
	public void save(PatientBillingBean patientBillingBean);
	public List<PatientBillingBean> getAll();
	public PatientBillingBean getById(Integer patientBillingId);
    public PatientBillingEntity update(Integer billingId,Double paymentAmmount);
	BedAllocationBean getDetails(int bedId);
	
}
