package com.patient.billing.service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.patient.billing.service.dto.BedAllocationDto;
import com.patient.billing.service.dto.PatientBillingDTO;
import com.patient.billing.service.entity.PatientBillingEntity;

public interface PatientBillingService {

	Optional<List<PatientBillingDTO>> getAllDetails();

	Optional<List<PatientBillingDTO>> filterByDateRange(LocalDate startDate, LocalDate endDate);

	void updateStatus(PatientBillingEntity patientBillingEntity);

	Optional<BedAllocationDto> getByPatientNo(String number);

	void save(BedAllocationDto patientBillingBean);

}
