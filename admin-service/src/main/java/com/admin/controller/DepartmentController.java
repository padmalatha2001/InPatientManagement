package com.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.bean.DepartmentBean;
import com.admin.entity.Department;
import com.admin.exception.RecordNotFoundException;
import com.admin.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	private static Logger log = LoggerFactory.getLogger(DepartmentController.class.getSimpleName());

	@PostMapping("/save")
	public ResponseEntity<DepartmentBean> save(@RequestBody DepartmentBean department) {
		log.info("Saving Department entity");
		try {
			departmentService.save(department);
			ResponseEntity<DepartmentBean> responseEntity = new ResponseEntity<>(department, HttpStatus.CREATED);
			log.info("Saving Department entity is done");
			return responseEntity;
		} catch (Exception e) {
			log.error("error handled");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<DepartmentBean> getById(@PathVariable long id) {
		log.info("Fetching Department by Id");
		
			DepartmentBean departmentBean = departmentService.getById(id);
//			ResponseEntity<DepartmentBean> responseEntity = new ResponseEntity<>(departmentBean, HttpStatus.OK);
			log.info("Fetching Department by Id is done");
			return ResponseEntity.status(HttpStatus.OK).body(departmentBean);
		
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<DepartmentBean>> getAll() {
		log.info("Fetching All Department details");
	
			List<DepartmentBean> list = departmentService.getAll();
			ResponseEntity<List<DepartmentBean>> responseEntity = new ResponseEntity<>(list, HttpStatus.OK);
			log.info("Fetching All Department details is done");
			return responseEntity;
		
	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity deleteById(@PathVariable long id) {
		log.info("Deleting Department by ID");
		try {
			departmentService.delete(id);
			ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.OK);
			log.info("Deleting Department by ID is done");
			return responseEntity;
		} catch (RecordNotFoundException e) {
			log.error("error handled");
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<DepartmentBean> put(@RequestBody Department department,@PathVariable long id) throws Exception {

		log.info("Updating Department");
		try {
			DepartmentBean department1 = departmentService.getById(id);
			if (department1 != null) {
				department1.setName(department.getName());
				departmentService.save(department1);
			}
			ResponseEntity<DepartmentBean> responseEntity = new ResponseEntity<>(department1, HttpStatus.OK);
			log.info("Updating Department is done");
			return responseEntity;
		} catch (RecordNotFoundException e) {
			log.error("error handled");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
