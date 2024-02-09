package com.reception.service.service;

import com.reception.service.bean.BillingBean;
import com.reception.service.bean.PatitentBean;

public interface ReceptionService {
	PatitentBean patitentDetails(int patitentid);
	BillingBean billingDetails(int billId);

}
