package com.patient.billing.service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.patient.billing.service.bean.BedAllocationBean;
import com.patient.billing.service.bean.PatientBillingBean;
import com.patient.billing.service.entity.PatientBillingEntity;
import com.patient.billing.service.service.PatientBillingService;

@RestController
public class PatientBillingController 
{
	@Autowired
	   private PatientBillingService patientBillingService;
//    @Autowired
//    private RestTemplate restTemplate;
	
	
	
	  @GetMapping(path="/details/{id}")
	  public ResponseEntity<BedAllocationBean>details(@PathVariable (value ="id")Integer bedId)
	   {
		   System.out.println("controller");
		  BedAllocationBean patientBillingBean= patientBillingService.getDetails(bedId);
		   return new ResponseEntity<BedAllocationBean>(patientBillingBean,HttpStatus.OK) ;
	    }
	  
	   @PostMapping(path="/save")
	   public ResponseEntity<PatientBillingBean>save(@RequestBody PatientBillingBean patientBillingBean)
	   {
		   System.out.println("controller");
		   patientBillingService.save(patientBillingBean);
		   return new ResponseEntity<PatientBillingBean>(patientBillingBean,HttpStatus.OK) ;
	   }
	   
	   @GetMapping(path="/getAll")
	   public ResponseEntity<List<PatientBillingBean>> getAll()
	   {
		   System.out.println("controller");
		   List<PatientBillingBean> patientBillingBean=patientBillingService.getAll();
		   try {
		   return new ResponseEntity<List<PatientBillingBean>>(patientBillingBean,HttpStatus.OK) ;
		   }
		   catch (Exception e) {
			System.out.println(e.getMessage());
			  return new ResponseEntity<List<PatientBillingBean>>(patientBillingBean,HttpStatus.NOT_FOUND) ;
		}
	   }
	   @GetMapping(path="/fetch/{id}")
	   public ResponseEntity<PatientBillingBean> getById(@PathVariable(value = "id") Integer billingId)
	   {
			PatientBillingBean patientBillingEntity=patientBillingService.getById(billingId);
		   return new ResponseEntity<PatientBillingBean>(patientBillingEntity,HttpStatus.OK) ;
	   }
	   
//	   @PutMapping(path="update{/id}/payedAmount")
//        public ResponseEntity<PatientBillingEntity> update(@PathVariable (value = "id") @RequestParam (value = "payedAmount"))
//        {
//		   
//			return null;
//		   
//        }
	   
	   @PutMapping(path="/update")
	   public ResponseEntity<PatientBillingEntity> update(
	           @RequestParam (value="billId") int billingId,
	           @RequestParam (value="paymentAmount") double paymentAmount) {
	       
		      
		   PatientBillingEntity updatedEntity = patientBillingService.update(billingId, paymentAmount);
		  try {
		   if (updatedEntity != null) {
	           return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
	       } else {
	           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	       }
		  }
		  catch(Exception e)
		  {
			  System.out.println("exception occured");
			  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
	   

//	       if (updatedEntity != null) {
//	           return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
//	       } else {
//	           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	       }
//	   
	   }
	   

}
