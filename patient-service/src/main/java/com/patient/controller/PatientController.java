package com.patient.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patient.bean.PatientBean;
import com.patient.entity.PatientEntity;
import com.patient.service.PatientService;


@RestController
public class PatientController {
@Autowired
private PatientService patientService;

@PostMapping("/save")
	public ResponseEntity<PatientBean> save(@RequestBody PatientBean patientBean) {
	
	patientService.save(patientBean);
	ResponseEntity<PatientBean> responseEntity = new ResponseEntity<>(patientBean,
			HttpStatus.OK);
	return responseEntity;
}

@GetMapping
public List<PatientBean> getAll() {
	
	 List<PatientBean> patientBean=patientService.getAll();
     return (List<PatientBean>) new ResponseEntity<List<PatientBean>>(patientBean,HttpStatus.OK) ;
     
	}

@GetMapping("/{id}")
public ResponseEntity<PatientEntity> getPatientById(@PathVariable Integer id) {

	   Optional<PatientEntity> patientEntity=patientService.getPatientById(id);
	   return new ResponseEntity<PatientEntity>(patientEntity.get(),HttpStatus.OK) ;
	   
}
  

@DeleteMapping("/delete/{id}")
public ResponseEntity<Void> deletePatient(@PathVariable Integer id) {
	patientService.delete(id);
    return ResponseEntity.noContent().build();
}


}




