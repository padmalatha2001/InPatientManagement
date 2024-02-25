package com.patient.billing.service.controller;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.patient.billing.service.bean.BedAllocationBean;
import com.patient.billing.service.bean.PatientBillingBean;
import com.patient.billing.service.dto.BedAllocationDto;
import com.patient.billing.service.dto.PatientBillingDTO;
import com.patient.billing.service.entity.PatientBillingEntity;
import com.patient.billing.service.service.PatientBillingService;

@RestController
public class PatientBillingController {
	@Autowired


	   private PatientBillingService patientBillingService;

	private static Logger log = LoggerFactory.getLogger(PatientBillingController.class.getSimpleName());

	@GetMapping(path = "/details/{id}")
	public ResponseEntity<BedAllocationBean> details(@PathVariable(value = "id") Integer bedId) {
		log.info("bed details{}"+bedId);
		System.out.println("controller");
		BedAllocationBean patientBillingBean = patientBillingService.getDetails(bedId);
		return new ResponseEntity<BedAllocationBean>(patientBillingBean, HttpStatus.OK);
	}

	@PostMapping(path = "/save")
	public ResponseEntity<PatientBillingBean> save(@RequestBody PatientBillingBean patientBillingBean) {
		System.out.println("controller");
		log.info("billing save method{}"+patientBillingBean);
		patientBillingService.save(patientBillingBean);
		return new ResponseEntity<PatientBillingBean>(patientBillingBean, HttpStatus.OK);
	}

	@GetMapping(path = "/getAll")
	public ResponseEntity<List<PatientBillingBean>> getAll() {
		System.out.println("controller");
		log.info("billing getAll method{}");
		List<PatientBillingBean> patientBillingBean = patientBillingService.getAll();
		try {
			return new ResponseEntity<List<PatientBillingBean>>(patientBillingBean, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<List<PatientBillingBean>>(patientBillingBean, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/fetch/{id}")
	public ResponseEntity<PatientBillingBean> getById(@PathVariable(value = "id") Integer billingId) {
		log.info("billing getById method{}"+billingId);
		PatientBillingBean patientBillingEntity = patientBillingService.getById(billingId);
		return new ResponseEntity<PatientBillingBean>(patientBillingEntity, HttpStatus.OK);
	}

	@PutMapping(path = "/update")
	public ResponseEntity<PatientBillingBean> update(@RequestBody PatientBillingBean patientBillingBean ) {
         log.info("billing update method{}");
		 patientBillingService.update(patientBillingBean);
		try {
			if (patientBillingBean != null) {
				return new ResponseEntity<>(patientBillingBean, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			System.out.println("exception occured");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}  
	}
	
	@GetMapping(path = "/get")
	public ResponseEntity<List<PatientBillingDTO>> getAlldeatils() {
		log.info("billing getById method{}");
		List<PatientBillingDTO> patientBillingEntity = patientBillingService.getAllDetails();
		return new ResponseEntity(patientBillingEntity, HttpStatus.OK);
	}
	
	
//	 @GetMapping("/getByMonth/{month}")
//	    public List<PatientBillingEntity> getDataByMonth(@PathVariable int month) {
//	        return patientBillingService.getDataByMonth(month);
//	    }
	@GetMapping("/getByMonth")
    public ResponseEntity<List<PatientBillingEntity>> getByMonth(@RequestParam String monthName) {
        List<PatientBillingEntity> result = patientBillingService.getDataByMonth(monthName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
	
	@GetMapping("/filter")
	    public ResponseEntity<List<PatientBillingDTO>> filterByDateRange(
	            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
		System.out.println("startDate: " + startDate);
        System.out.println("endDate: " + endDate);
		if (endDate == null) {
            endDate = LocalDate.now();
        }
		 List<PatientBillingDTO>  result  = patientBillingService.filterByDateRange(startDate, endDate);
		 System.out.println(result.toString());
		 return new ResponseEntity<>(result, HttpStatus.OK);

	    }
	@GetMapping("bedDetails")
	public ResponseEntity<List<BedAllocationDto>>getDetails()
	{
		List<BedAllocationDto> bedDetails=patientBillingService.getBedDetails();
		return new ResponseEntity<List<BedAllocationDto>>(bedDetails,HttpStatus.OK);
	}
	@GetMapping("/getno")
	public ResponseEntity<BedAllocationDto>getAllDetails(@RequestParam String patientNumber)
	{
		BedAllocationDto bedDetails=patientBillingService.getByPatientNo(patientNumber);
		return new ResponseEntity<BedAllocationDto>(bedDetails,HttpStatus.OK);
	}
	

}
