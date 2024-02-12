package com.patient.billing.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.patient.billing.service.entity.PatientBillingEntity;

import jakarta.transaction.Transactional;
@Transactional
public interface PatientBillingRepository  extends JpaRepository<PatientBillingEntity, Integer>
{
	
//	select * from billing join bed_allocation 
//	ON billing.bed_allocation_id=bed_allocation.bed_allocation_id
//	join  patientregistration on bed_allocation.patient_id=patientregistration.patient_id;
//  getAllThedetails()
  
  @Query(value="SELECT * FROM billing  " +
          "JOIN bed_allocation  ON billing.bed_allocation_id = bed_allocation.bed_allocation_id " +
          "JOIN patientregistration  ON bed_allocation.patient_id = patientregistration.patient_id",nativeQuery = true)
   List getBillingResults();


}
