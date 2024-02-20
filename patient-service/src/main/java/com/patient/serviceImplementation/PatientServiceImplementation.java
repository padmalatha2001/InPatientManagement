package com.patient.serviceImplementation;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.bean.DoctorBean;
import com.patient.bean.PatientBean;
import com.patient.entity.DoctorEntity;
import com.patient.entity.PatientEntity;
import com.patient.exception.PatientIdNotFoundException;
import com.patient.repository.PatientRepository;
import com.patient.service.PatientService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
@Service
public class PatientServiceImplementation implements PatientService{
	@Autowired
	private PatientRepository patientRepository;
	
	@PersistenceContext
    private EntityManager entityManager;

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
		DoctorBean doctorBean=patientBean.getDoctorBean();
		DoctorEntity doctorEntity=new DoctorEntity();
		beanToEntity(doctorBean,doctorEntity);
		patientEntity.setDoctor(doctorEntity);
	}

	private void beanToEntity(DoctorBean doctorBean, DoctorEntity doctorEntity) {
		// TODO Auto-generated method stub
		doctorEntity.setId(doctorBean.getId());
		doctorEntity.setName(doctorBean.getName());
		doctorEntity.setDepartmentId(doctorBean.getDepartmentId());
	}
	public void entityToBean(List<PatientEntity> patientEntity,List<PatientBean> patientBean)
	{
		
		
		for(PatientEntity patientEntity1:patientEntity)
		{
			PatientBean patientbean=new PatientBean();
			patientbean.setPatientId(patientEntity1.getPatientId());
			patientbean.setFirstName(patientEntity1.getFirstName());
			patientbean.setLastName(patientEntity1.getLastName());
			patientbean.setPatientGender(patientEntity1.getPatientGender());
			patientbean.setPatientAge(patientEntity1.getPatientAge());
			patientbean.setPatientContactNo(patientEntity1.getPatientContactNo());
			patientbean.setPatientAlternteContactNo(patientEntity1.getPatientAlternteContactNo());
			DoctorEntity doctorEntity=patientEntity1.getDoctor();
			DoctorBean doctorBean=new DoctorBean();
			entityToBean(doctorEntity,doctorBean);
			patientbean.setDoctorBean(doctorBean);
			patientBean.add(patientbean);
			
			
		}
	}
		
	private void entityToBean(DoctorEntity doctorEntity, DoctorBean doctorBean) {
		// TODO Auto-generated method stub
		doctorBean.setDepartmentId(doctorEntity.getDepartmentId());
		doctorBean.setId(doctorEntity.getId());
		doctorBean.setName(doctorEntity.getName());
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
			DoctorEntity doctorEntity=patientEntity.getDoctor();
			DoctorBean doctorBean=new DoctorBean();
			entityToBean(doctorEntity,doctorBean);
			patientbean.setDoctorBean(doctorBean);
				
			
	}


		
		public List<Object[]> getPatientDetailsByDoctor(String doctorName) {
         
	        String sqlQuery = "SELECT p.first_name, p.last_name, bed.bed_no, room.room_no, " +
                    "ward.ward_name, department.department_name " +
                    "FROM patientregistration p " +
                    "JOIN bed_allocation ba ON p.patient_id = ba.patient_id " +
                    "JOIN bed ON ba.bed_id = bed.bed_id " +
                    "JOIN room ON bed.room_id = room.room_id " +
                    "JOIN ward ON room.ward_id = ward.ward_id " +
                    "JOIN department ON ward.department_id = department.dept_id " +
                    "JOIN doctor d ON p.doctor = d.doctor_id " +
                    "WHERE d.doctor_name = :doctorName " +
                    "AND CURRENT_DATE BETWEEN ba.start_date AND ba.end_date";
	        List<Object[]> resultList = entityManager.createNativeQuery(sqlQuery)
	                .setParameter("doctorName", doctorName)
	                .getResultList();
	        
	        for (Object[] row : resultList) {
	            for (Object value : row) {
	                System.out.print(value + "\t"); 
	            }
	            System.out.println();
	        }
	        return resultList;
	    }
		
		
		 public List<Object[]> getPatientDetailsByFullName(String fullName) {
		        String nativeQuery = "SELECT p.first_name, p.last_name, bed.bed_no, room.room_no, " +
		                            "ward.ward_name, department.department_name " +
		                            "FROM patientregistration p " +
		                            "JOIN bed_allocation ba ON p.patient_id = ba.patient_id " +
		                            "JOIN bed ON ba.bed_id = bed.bed_id " +
		                            "JOIN room ON bed.room_id = room.room_id " +
		                            "JOIN ward ON room.ward_id = ward.ward_id " +
		                            "JOIN department ON ward.department_id = department.dept_id " +
		                            "WHERE CONCAT(p.first_name, p.last_name) = :fullName";

		        List<Object[]> resultList = entityManager.createNativeQuery(nativeQuery)
		                .setParameter("fullName", fullName)
		                .getResultList();

		        return resultList;
		    }
	
	
	
}



