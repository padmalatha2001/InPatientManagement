package com.patient.billing.service.serviceimplimentation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.patient.billing.service.bean.BedAllocationBean;
import com.patient.billing.service.bean.BedBean;
import com.patient.billing.service.bean.PatientBean;
import com.patient.billing.service.bean.PatientBillingBean;
import com.patient.billing.service.bean.RoomBean;
import com.patient.billing.service.dto.BedAllocationDto;
import com.patient.billing.service.dto.PatientBillingDTO;
import com.patient.billing.service.entity.CustomMonth;
import com.patient.billing.service.entity.PatientBillingEntity;
import com.patient.billing.service.entity.PatientEntity;
import com.patient.billing.service.exception.BillingDetailsNotFoundException;
import com.patient.billing.service.exception.BillingIdNotFoundException;
import com.patient.billing.service.repository.PatientBillingRepository;
import com.patient.billing.service.service.PatientBillingService;

@Service
public class PatientBillingServiceImplimentation implements PatientBillingService {
	@Autowired
	private PatientBillingRepository patientBillingRepository;
	@Autowired
	private RestTemplate restTemplate;
	//private List<PatientBillingEntity> PatientBillingEntity  result;
	private List<PatientBillingEntity> findByBillingDateBetween;


	@Override
	public BedAllocationBean getDetails(int bedId) {
		String url = "http://localhost:8083/bedAllocation/getById/" + bedId;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);

		ResponseEntity<BedAllocationBean> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				BedAllocationBean.class);
		BedAllocationBean bedAllocation = responseEntity.getBody();

		return bedAllocation;

	}

	@Override
	public PatientBean getPatitentDetails(int patitentid) {

		String url = "http://localhost:8081/registration/" + patitentid;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<PatientBean> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<PatientBean>() {
				});
//				if(responseEntity!=null) {

		PatientBean patitentBean = responseEntity.getBody();
		return patitentBean;
	}
//		else
//		{
//			//throw new PatitentDetailsNotFoundException("Patitent Details Not found");
//		}
//	

	// }

	@Override
	public void save(PatientBillingBean patientBillingBean) {

		PatientBillingEntity patientBillingEntity = new PatientBillingEntity();
		beanToEntity(patientBillingEntity, patientBillingBean);

		patientBillingRepository.save(patientBillingEntity);

	}

	@Override
	public List<PatientBillingBean> getAll() {

		List<PatientBillingBean> patientBillingBean = new ArrayList<>();
		List<PatientBillingEntity> patiBillingEntity = patientBillingRepository.findAll();
		if (patiBillingEntity.isEmpty()) {
			throw new BillingDetailsNotFoundException("Billing details not found");
		} else {

//		   PatientBillingEntity billId	=patiBillingEntity.get(1);
//		   BedAllocationBean bean  =getDetails(billId.getBedAllocationId());
//		   System.out.println(bean.getPatientId());
			entityToBean(patiBillingEntity, patientBillingBean);

			return patientBillingBean;
		}
	}

	private BedAllocationBean getDetails(PatientBillingEntity billId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientBillingBean getById(Integer patientBillingId) {

		PatientBillingBean patientBillingBean = new PatientBillingBean();
		if (patientBillingRepository.existsById(patientBillingId)) {
			Optional<PatientBillingEntity> patientEntity = patientBillingRepository.findById(patientBillingId);
			entityToBean(patientEntity.get(), patientBillingBean);

		}

		else {
			throw new BillingIdNotFoundException("billing id not found");
		}
		return patientBillingBean;
	}

	@Override
	public void update(PatientBillingBean patitentBean) {
		Optional<PatientBillingEntity> patientBillingEntity = patientBillingRepository.findById(patitentBean.getBillId());
		if (patientBillingEntity.isEmpty()) {
			System.out.println(patientBillingEntity);
			throw new BillingIdNotFoundException("billing id not found");
		}

		else {
			PatientBillingEntity patientEntity = patientBillingEntity.get();

			int patientBillingId = patientBillingEntity.get().getBillId();
			double totalAmount = patientEntity.getTotalAmount();
			double paidAmount = patientBillingEntity.get().getPaidAmount();
//			beanToEntity(patientEntity,patitentBean);
			double totalPaidAmount = paidAmount + patitentBean.getPaidAmount();
			double discount=patitentBean.getDiscount();
			beanToEntity(patientEntity,patitentBean);
			//double discountAmount=totalPaidAmount-(totalPaidAmount*(patitentBean.getDiscount()/100));
			
			if (totalPaidAmount == totalAmount) {
				patientBillingEntity.get().setPaymentStatus("Completed");
				patientBillingRepository.save(patientEntity);

			} else {
				patientBillingEntity.get().setPaymentStatus("pending");
				patientBillingRepository.save(patientEntity);
				// throw new BillingIdNotFoundException("Billing Id not found") ;

			}

		}

	}

	public void beanToEntity(PatientBillingEntity patientBillingEntity, PatientBillingBean patientBillingBean) {
		patientBillingEntity.setBillingDate(LocalDate.now());
		patientBillingEntity.setBedAllocationId(patientBillingBean.getBedAllocationId());

		int bedAllocationId = patientBillingEntity.getBedAllocationId();
		BedAllocationBean bedAllocationBean=getDetails(bedAllocationId);
		int days=bedAllocationBean.getNoOfDays();
		BedBean BedBean=bedAllocationBean.getBedId();
		 RoomBean roomBean = BedBean.getRoomId();
		 double roomPrice=roomBean.getRoomPrice();
		double amount=roomPrice*days;
		 System.out.println(amount);
		patientBillingEntity.setDiscount(patientBillingBean.getDiscount());
		patientBillingEntity.setPaidAmount(patientBillingBean.getPaidAmount());
		 patientBillingEntity.setTotalAmount(amount);
		patientBillingEntity.setPaymentStatus(patientBillingBean.getPaymentStatus());

	}

	public void entityToBean(List<PatientBillingEntity> patientBillingEntity,
			List<PatientBillingBean> patientBillingBean) {

		for (PatientBillingEntity patientEntity : patientBillingEntity) {
			int billId = patientEntity.getBillId();
			BedAllocationBean bedAllocationBean = getDetails(billId);
//			int noOfDays = bedAllocationBean.getNoOfDays();
//			RoomTypeBean roomTypeBean = bedAllocationBean.getRoomTypeId();
//			double roomPrice = roomTypeBean.getRoomPrice();
//			double amount = roomPrice * noOfDays;
			int patitentId = bedAllocationBean.getPatientId();
			PatientBean patitentDetails = getPatitentDetails(patitentId);

			PatientBillingBean patientbean = new PatientBillingBean();
			patientbean.setBillId(patientEntity.getBillId());
			patientbean.setBedAllocationId(patientEntity.getBedAllocationId());
			patientbean.setBillingDate(patientEntity.getBillingDate());
			patientbean.setPaidAmount(patientEntity.getPaidAmount());
			patientbean.setDiscount(patientEntity.getDiscount());
			patientbean.setTotalAmount(patientEntity.getTotalAmount());
			patientbean.setPaymentStatus(patientEntity.getPaymentStatus());
			patientBillingBean.add(patientbean);

		}
	}

	public void entityToBean(PatientBillingEntity patientBillingEntity, PatientBillingBean patientBillingBean) {

		// PatientBillingBean patientbean = new PatientBillingBean();

		patientBillingBean.setBillId(patientBillingEntity.getBillId());

//		 int k=patientBillingEntity.getBedAllocationId();
//			BedAllocationBean bedAllocationBean=getDetails(k);
//			patientBillingBean.setBedAllocationId(bedAllocationBean);
//		int patientBillingId = patientBillingEntity.getBedAllocationId();
//		int days=bedAllocationBean.getNoOfDays();
//		BedBean BedBean=bedAllocationBean.getBedId();
//		 RoomBean roomBean = BedBean.getRoomId();
//		 double roomPrice=roomBean.getRoomPrice();
//		double amount=roomPrice*days;

		patientBillingBean.setBedAllocationId(patientBillingEntity.getBedAllocationId());
		patientBillingBean.setBillingDate(patientBillingEntity.getBillingDate());
		patientBillingBean.setPaidAmount(patientBillingEntity.getPaidAmount());
		patientBillingBean.setDiscount(patientBillingEntity.getDiscount());
		patientBillingBean.setPaymentStatus(patientBillingEntity.getPaymentStatus());
		patientBillingBean.setTotalAmount(patientBillingEntity.getTotalAmount());

	}

	@Override
	public List<PatientBillingDTO> getAllDetails() {
List<PatientBillingDTO> data = patientBillingRepository.getBillingResults();
    	
    	System.out.println("filterde data"+data);
    	return data;
		
		
	}
	public static int convertMonthNameToNumber(String monthName) {
	    CustomMonth month;

	    if (monthName.length() <= 3) {
	        
	        month = CustomMonth.monthName(monthName);
	    } else {
	        	        month = CustomMonth.fullMonthName(monthName);
	    }

	    return month.getValue();
	}

    
//    public List<PatientBillingEntity> getDataByMonth(String monthName) {
//        int monthNumber = convertMonthNameToNumber(monthName.toUpperCase());
//        System.out.println("Month Number: " + monthNumber);
//
//        List<PatientBillingEntity> result = patientBillingRepository.findByMonth(monthNumber);
//        
//        // Process the result or return it based on your requirements
//        return result;
//    }
    @Override
    public List<PatientBillingDTO> filterByDateRange(LocalDate startDate, LocalDate endDate) {
    	List<PatientBillingDTO> data = patientBillingRepository.findByBillingDateBetween(startDate, endDate);
    	
    	System.out.println("filterde data"+data);
    	return data;
    }

	@Override
	public List<PatientBillingEntity> filterByDateRange(LocalDate startDate, Optional<LocalDate> endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PatientBillingEntity> getDataByMonth(String monthName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BedAllocationDto> getBedDetails() {
		List<BedAllocationDto>bedAllocationDetails=patientBillingRepository.getBedAllocationDetails();
				return bedAllocationDetails;
	}

	@Override
	public void deleteRecord(int billId, String recordStatus) {
	boolean existsById = patientBillingRepository.existsById(billId);
	if(existsById)
	{
		//patientBillingRepository.
	}
		
	}
	

	@Override
	public BedAllocationDto getByPatientNo(String number) {
	
		BedAllocationDto details=patientBillingRepository.findPatientDataByPatientNumber(number);
		
//		String patientNumber=details.getPatientNumber();
//		if(patientNumber.equalsIgnoreCase(number));
		return details;
	}
	}

	
//	@Override
//	public BillingDetailsBean getBillingDetails() {
//		// TODO Auto-generated method stub
//		
//		PatientBean patientBean=getPatitentDetails(patientId);
//		PatientBillingBean patientBillingBean=getById(patientBillingId);
//		BillingDetailsBean billingDetailsBean=new BillingDetailsBean();
//		billingDetailsBean.setBillId(patientBillingBean.getBillId());
//		
//		billingDetailsBean.setBillingDate(patientBillingBean.getBillingDate());
//		billingDetailsBean.setBedAllocationId(patientBillingBean.getBedAllocationId());
//		billingDetailsBean.setFirstName(billingDetailsBean.getFirstName());
//		billingDetailsBean.setLastName(patientBean.getLastName());
//		billingDetailsBean.setPaidAmount(billingDetailsBean.getPaidAmount());
//		billingDetailsBean.setDiscount(billingDetailsBean.getDiscount());
//		billingDetailsBean.setTotalAmount(billingDetailsBean.getTotalAmount());
//		billingDetailsBean.setPaymentStatus(patientBillingBean.getPaymentStatus());
//		
//		return billingDetailsBean;
//	}
	


