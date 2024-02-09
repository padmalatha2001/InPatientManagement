package com.admin.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.bean.RegistrationBean;
import com.admin.entity.RegistrationForm;
import com.admin.exception.RecordNotFoundException;
import com.admin.repository.RegistrationRepository;
import com.admin.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService{

	@Autowired
	RegistrationRepository registrationRepository;
	@Override
	public RegistrationBean save(RegistrationBean registrationBean) {
		
		RegistrationForm registrationEntity=new RegistrationForm();
		beanToEntity(registrationBean,registrationEntity);
		registrationRepository.save(registrationEntity);
		return registrationBean;
		
		
	}
	private void beanToEntity(RegistrationBean registrationBean, RegistrationForm registrationEntity) {
		
		registrationEntity.setId(registrationBean.getId());
		registrationEntity.setFirstName(registrationBean.getFirstName());
		registrationEntity.setLastName(registrationBean.getLastName());
		registrationEntity.setEmail(registrationBean.getEmail());
		registrationEntity.setGender(registrationBean.getGender());
		registrationEntity.setBirthDay(registrationBean.getBirthDay());
		registrationEntity.setServiceType(registrationBean.getServiceType());
		registrationEntity.setPhoneNumber(registrationBean.getPhoneNumber());
	
	}
	@Override
	public RegistrationBean getById(int id) {
		RegistrationForm registrationEntity=registrationRepository.findById(id).orElseThrow(()->new RecordNotFoundException("No Record Found with given id"));
		RegistrationBean registrationBean=new RegistrationBean();
		entityToBean(registrationEntity,registrationBean);
		return registrationBean;
	}
	private void entityToBean(RegistrationForm registrationEntity, RegistrationBean registrationBean) {
		registrationBean.setId(registrationEntity.getId());
		registrationBean.setFirstName(registrationEntity.getFirstName());
		registrationBean.setLastName(registrationEntity.getLastName());
		registrationBean.setEmail(registrationEntity.getEmail());
		registrationBean.setGender(registrationEntity.getGender());
		registrationBean.setBirthDay(registrationEntity.getBirthDay());
		registrationBean.setPhoneNumber(registrationEntity.getPhoneNumber());
		registrationBean.setServiceType(registrationEntity.getServiceType());
		System.out.println(registrationBean.getPhoneNumber());
		
	}
	@Override
	public List<RegistrationBean> getAll() {
		List<RegistrationForm> entityList=registrationRepository.findAll();
		List<RegistrationBean> beanList=new ArrayList<>();
		entityToBean(entityList,beanList);
		return beanList;
	}
	private void entityToBean(List<RegistrationForm> entityList, List<RegistrationBean> beanList) {
         for(RegistrationForm registrationEntity:entityList)
         {
        	 RegistrationBean registrationBean=new  RegistrationBean();
        	 entityToBean(registrationEntity,registrationBean);
        	 beanList.add(registrationBean);
        	 
         }
		
	}
	@Override
	public void delete(int id) {
		RegistrationForm RegistrationForm=registrationRepository.findById(id).orElseThrow(()->new RecordNotFoundException("No Record Found with given id"));
		registrationRepository.delete(RegistrationForm);
	}
	@Override
	public void update(RegistrationBean registration) {
		RegistrationForm RegistrationForm=registrationRepository.findById(registration.getId()).orElseThrow(()->new RecordNotFoundException("No Record Found with given id"));
		beanToEntity(registration,RegistrationForm);
		registrationRepository.save(RegistrationForm);
	}

}
