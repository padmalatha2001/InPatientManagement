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
import com.patient.billing.service.bean.RoomTypeBean;
import com.patient.billing.service.entity.PatientBillingEntity;
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
	public List<Map<String,Object>> getAllDetails() {
		List<Object[]> resultList=patientBillingRepository.getBillingResults();
		 List<Map<String, Object>> mappedResults = new ArrayList<>();
		 for (Object[] result : resultList) {
			 System.out.println(resultList);
			 System.out.println("result:"+result);
	            Map<String, Object> map = new HashMap<>();
	            map.put("billingId", result[0]);
	           map.put("bedId", result[1]);
	           map.put("billingDate", result[2]);
	           map.put("discount", result[3]);
	           map.put("paidAmount", result[4]);
	           map.put("totalAmount", result[6]);
	           map.put("paymentStatus", result[5]);
	           map.put("bedAllocationId", result[7]);
	           map.put("noOfDays", result[9]);
	           map.put("patientid", result[10]);
	           map.put("fistName", result[15]);
	           map.put("lastName",result[16]);
	           

	            mappedResults.add(map);
	        }
		 System.out.println(mappedResults);
		return mappedResults;
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
	

}
