package com.patient.serviceImplementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.bean.DoctorBean;
import com.patient.entity.DoctorEntity;
import com.patient.exception.PatientIdNotFoundException;
import com.patient.repository.DoctorRepository;
import com.patient.service.DoctorService;
@Service
public class DoctorServiceImplementation implements DoctorService{
	
	@Autowired
	DoctorRepository doctorRepository;

	@Override
	public DoctorBean save(DoctorBean doctorBean) {
		// TODO Auto-generated method stub
		DoctorEntity doctorEntity=new DoctorEntity();
		doctorBean.setStatus("Active");
		BeanToEntity(doctorBean, doctorEntity);
		doctorRepository.save(doctorEntity);
		return doctorBean;
	}

	private void BeanToEntity(DoctorBean doctorBean, DoctorEntity doctorEntity) {
		// TODO Auto-generated method stub
		doctorEntity.setId(doctorBean.getId());
		doctorEntity.setName(doctorBean.getName());
		doctorEntity.setDepartmentId(doctorBean.getDepartmentId());
		doctorEntity.setStatus(doctorBean.getStatus());
	}

	@Override
	public DoctorBean getById(long id) {
		// TODO Auto-generated method stub

		DoctorEntity doctorEntity= doctorRepository.findById(id).orElseThrow(()->new PatientIdNotFoundException("No record found with the given id"));
		DoctorBean doctorBean=new DoctorBean();
		
		entityToBean(doctorEntity, doctorBean);

		return doctorBean;	
	}

	private void entityToBean(DoctorEntity doctorEntity, DoctorBean doctorBean) {
		// TODO Auto-generated method stub
		doctorBean.setDepartmentId(doctorEntity.getDepartmentId());
		doctorBean.setId(doctorEntity.getId());
		doctorBean.setName(doctorEntity.getName());
	}

	@Override
	public List<DoctorBean> getAll() {
		// TODO Auto-generated method stub
		List<DoctorBean> doctorBean=new ArrayList<>();
	    List<DoctorEntity>	doctorEntity=doctorRepository.findAll();
        entityToBean(doctorEntity,doctorBean);
		return doctorBean;
	}

	private void entityToBean(List<DoctorEntity> doctorEntity, List<DoctorBean> doctorBean) {
		// TODO Auto-generated method stub
		for(DoctorEntity doctorEntity1:doctorEntity)
		{
			DoctorBean doctorbean=new DoctorBean();
			doctorbean.setId(doctorEntity1.getId());
			doctorbean.setName(doctorEntity1.getName());
			doctorbean.setDepartmentId(doctorEntity1.getDepartmentId());
			doctorbean.setStatus(doctorEntity1.getStatus());
			doctorBean.add(doctorbean);
		}
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		doctorRepository.deleteById(id);
	}

	@Override
	public void update(DoctorBean doctorbean) {
		// TODO Auto-generated method stub

		DoctorEntity doctorEntity=doctorRepository.getReferenceById(doctorbean.getId());

			//DoctorBean doctorBean=new DoctorBean();
			doctorEntity.setId(doctorbean.getId());
			doctorEntity.setName(doctorbean.getName());
			doctorEntity.setDepartmentId(doctorbean.getDepartmentId());
			doctorEntity.setStatus(doctorbean.getStatus());
			doctorRepository.save(doctorEntity);
	}

	@Override
	public void updateStatus(DoctorEntity doctor) {
		// TODO Auto-generated method stub
		doctor.setStatus("InActive");
		doctorRepository.save(doctor);
	}

}
