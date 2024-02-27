package com.patient.billing.service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.patient.billing.service.bean.BedAllocationBean;
import com.patient.billing.service.bean.PatientBean;
import com.patient.billing.service.bean.PatientBillingBean;
import com.patient.billing.service.dto.BedAllocationDto;
import com.patient.billing.service.dto.PatientBillingDTO;
import com.patient.billing.service.entity.PatientBillingEntity;



public interface PatientBillingService {
	//public void save(PatientBillingBean patientBillingBean);
	public List<PatientBillingBean> getAll();
	public PatientBillingBean getById(Integer patientBillingId);
//    public PatientBillingEntity update(Integer billingId,Double paymentAmmount);
	BedAllocationBean getDetails(int bedId);
  PatientBean getPatitentDetails(int patitentId);
void update(PatientBillingBean patitentBean);
 // BillingDetailsBean getBillingDetails(int patientId,Integer patientBillingId);
List<PatientBillingDTO> getAllDetails();
//List<PatientBillingEntity> getDataByMonth(String date);
List<PatientBillingEntity> getDataByMonth(String monthName);
//List<PatientBillingEntity> filterByDateRange(LocalDate startDate, LocalDate endDate);
//List<PatientBillingEntity> filterByDateRange(Date startDate, Date endDate);
//List<PatientBillingEntity> filterByDateRange(LocalDate startDate, LocalDate endDate);
List<PatientBillingEntity> filterByDateRange(LocalDate startDate, Optional<LocalDate> endDate);
List<PatientBillingDTO> filterByDateRange(LocalDate startDate, LocalDate endDate);
List<BedAllocationDto>getBedDetails();
void deleteRecord(int billId,String recordStatus);
BedAllocationDto getByPatientNo(String number);
void save(BedAllocationDto patientBillingBean);
	
}
