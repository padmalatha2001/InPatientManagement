package com.patient.service;

import java.util.List;

import com.patient.bean.DoctorBean;

public interface DoctorService {

DoctorBean save(DoctorBean doctorBean);
	
	DoctorBean getById(long id);
	
	List<DoctorBean> getAll();
	
	void delete(Long id);

    void update(DoctorBean doctorbean);
}
