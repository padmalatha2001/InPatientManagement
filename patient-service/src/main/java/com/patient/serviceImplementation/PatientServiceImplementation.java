package com.patient.serviceImplementation;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.bean.PatientBean;
import com.patient.entity.PatientEntity;
import com.patient.exception.PatientIdNotFoundException;
import com.patient.repository.PatientRepository;
import com.patient.service.PatientService;
@Service
public class PatientServiceImplementation implements PatientService{
	@Autowired
	private PatientRepository patientRepository;
	

	@Override
	public void save(PatientBean patientBean) {
		
		PatientEntity patientEntity=new PatientEntity();
		BeanToEntity(patientEntity, patientBean);
		patientRepository.save(patientEntity);

		
	}

	
	@Override
	public List<PatientBean> getAll() {
		List<PatientBean> patientBean=new ArrayList<>();
		//List<PatientEntity> patientEntity=new ArrayList<>();
	    List<PatientEntity>	patientEntity=patientRepository.findAll();
        entityToBean(patientEntity,patientBean);
		return patientBean;
	}
	

//	@Override
//	public void update(int patientId, String paymentStatus, double paymentAmmount) {
//		Optional<PatientBillingEntity> patientBillingEntity=patientBillingRepository.findById(billingId);
//		int patientBillingId=patientBillingEntity.get().getBillId();
//		//if(patientBillingId.)
//		
//		
//	}
//	

	@Override
	public void delete(Integer id) {
		
		 patientRepository.deleteById(id);
		
	}



	@Override
	public void update(PatientBean patientBean) {

		
	}


	@Override
	public Optional<PatientEntity> getPatientById(Integer id) {
				
			PatientBean patientBean=new PatientBean();
			Optional<PatientEntity> patientEntity=patientRepository.findById(id);
			//entityToBean(patientEntity, patientBean);
			//int billingId=patientEntity.get().getBillId();
			boolean patientId =patientRepository.existsById(id);
			if(patientId!=true)
			{
				throw new PatientIdNotFoundException("Patient Id not found");
			}
			else
			{
				return patientEntity ;
	 
			}
		
	}



	
	public void BeanToEntity(PatientEntity patientEntity,PatientBean patientBean) {
		patientEntity.setFirstName(patientBean.getFirstName());
		patientEntity.setLastName(patientBean.getLastName());
		patientEntity.setPatientGender(patientBean.getPatientGender());
		patientEntity.setPatientAge(patientBean.getPatientAge());
		patientEntity.setPatientContactNo(patientBean.getPatientContactNo());
		patientEntity.setPatientAlternteContactNo(patientBean.getPatientAlternteContactNo());
	
	}

	public void entityToBean(List<PatientEntity> patientEntity,List<PatientBean> patientBean)
	{
		
		PatientBean patientbean=new PatientBean();
		for(PatientEntity patientEntity1:patientEntity)
		{
			patientbean.setPatientId(patientEntity1.getPatientId());
			patientbean.setFirstName(patientEntity1.getFirstName());
			patientbean.setLastName(patientEntity1.getLastName());
			patientbean.setPatientGender(patientEntity1.getPatientGender());
			patientbean.setPatientAge(patientEntity1.getPatientAge());
			patientbean.setPatientContactNo(patientEntity1.getPatientContactNo());
			patientbean.setPatientAlternteContactNo(patientEntity1.getPatientAlternteContactNo());
			patientBean.add(patientbean);
			
		}
	}
		
		public void entityToBean(PatientEntity patientEntity,PatientBean patientBean)
		{
			
			PatientBean patientbean=new PatientBean();
			
			patientbean.setPatientId(patientEntity.getPatientId());
			patientbean.setFirstName(patientEntity.getFirstName());
			patientbean.setLastName(patientEntity.getLastName());
			patientbean.setPatientGender(patientEntity.getPatientGender());
			patientbean.setPatientAge(patientEntity.getPatientAge());
			patientbean.setPatientContactNo(patientEntity.getPatientContactNo());
			patientbean.setPatientAlternteContactNo(patientEntity.getPatientAlternteContactNo());
				
			
	}
		
	
	
	
}



