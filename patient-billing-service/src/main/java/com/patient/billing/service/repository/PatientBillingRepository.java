package com.patient.billing.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.billing.service.entity.PatientBillingEntity;

public interface PatientBillingRepository  extends JpaRepository<PatientBillingEntity, Integer>
{



}
