package com.patient.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.entity.PatientEntity;

public interface PatientRepository extends JpaRepository<PatientEntity, Integer>{




}
