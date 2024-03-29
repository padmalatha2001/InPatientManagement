package com.patient.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.patient.bean.DoctorBean;
import com.patient.entity.DoctorEntity;
import com.patient.service.DoctorService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
@Autowired
	
	private DoctorService doctorService;
	private static Logger log = LoggerFactory
			.getLogger(DoctorController.class.getSimpleName());
	

	@PostMapping("/save")
		public ResponseEntity<DoctorBean> save(@RequestBody DoctorBean doctorBean) {
		log.info("Saving Doctor");
		try {
			doctorBean = doctorService.save( doctorBean);
		   ResponseEntity<DoctorBean> responseEntity = new ResponseEntity<>(doctorBean, HttpStatus.CREATED);
		   log.info("Saving doctor is done");
		   return responseEntity;
		}catch(Exception e) {
			log.error("error handled");
			return new ResponseEntity<>( HttpStatus.NOT_FOUND);
		}		
	}
	
	 @GetMapping("/getById/{id}")
		public ResponseEntity<DoctorBean> getById(@PathVariable long id) {
			 log.info("Getting Doctor Details by Id");
			  DoctorBean doctorBean = doctorService.getById(id);
			 log.info("Getting Doctor Details by Id is done");
			 return new ResponseEntity<DoctorBean>(doctorBean, HttpStatus.OK);
	
       }
	 

		@GetMapping("/getAll")
		public ResponseEntity<List<DoctorBean>> getAll() {
			log.info("Getting  All Doctor Details");
			
			    List<DoctorBean> list = doctorService.getAll();
			    ResponseEntity<List<DoctorBean>> responseEntity = new ResponseEntity<>(list, HttpStatus.OK);
			    log.info("Getting  All Doctor Details is done");
			    return responseEntity;
			
		}
		
		@PutMapping("/update")
		public ResponseEntity<String> put(@RequestBody DoctorBean doctorBean) {

			log.info("Updating Doctor");
			doctorService.update(doctorBean);
			return null;
		}
		
		@PutMapping("/updateStatus")
		public void updateStatus(@RequestBody DoctorEntity doctor) {
			doctorService.updateStatus(doctor);
		}
}
