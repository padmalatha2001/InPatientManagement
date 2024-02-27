package com.patient.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.patient.bean.PatientBean;
import com.patient.entity.PatientEntity;


public interface PatientService {

	void save(PatientBean patientBean);
	
	List<PatientBean> getAll();
	
	Optional<PatientEntity> getPatientById(Integer id);
	
	void delete(Integer id);

    void update(PatientBean patientRegistration);

    List<Object[]> getPatientDetailsByDoctor(String doctorName);
    
    List<Object[]> getPatientDetailsByFullName(String fullName);
<<<<<<< HEAD
    
    void updateStatus(PatientEntity patient);
=======

    String generatePatientNo();


>>>>>>> 91059446d64804056c3376ea3fcebe6379d46f8c

}

