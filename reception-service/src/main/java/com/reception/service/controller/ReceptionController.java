package com.reception.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.reception.service.bean.BillingBean;
import com.reception.service.bean.PatitentBean;
import com.reception.service.service.ReceptionService;



@RestController
public class ReceptionController 
{
	@Autowired
	private ReceptionService  receptionService;
	@GetMapping(path = "details/{id}")
	public PatitentBean getPatientDetails(@PathVariable(value="id") int id)
	{
		PatitentBean patitentBean=receptionService.patitentDetails(id);
		return patitentBean;
	}
	@GetMapping(path = "billingdetails/{id}")
	public BillingBean getBillingDetails(@PathVariable (value = "id") int id)
	{
		BillingBean billingBean=receptionService.billingDetails(id);
		return billingBean;
		
	}

}
