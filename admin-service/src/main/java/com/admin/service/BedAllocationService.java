package com.admin.service;

import java.util.List;
import java.util.Map;

import com.admin.bean.BedAllocationBean;
import com.admin.bean.PatientBean;
<<<<<<< HEAD
import com.admin.entity.BedAllocation;
=======
import com.admin.dto.BedAllocationDto;

>>>>>>> 91059446d64804056c3376ea3fcebe6379d46f8c

public interface BedAllocationService {

	BedAllocationBean save(BedAllocationBean bedAllocationBean);
	BedAllocationBean getById(int id);
	List<BedAllocationBean> getAll();
	void delete(int id);
	void update(BedAllocationBean bedAllocationBean);
	PatientBean getDetails(int id);
<<<<<<< HEAD
	
=======

	List<BedAllocationDto>getBedDetails();

>>>>>>> 91059446d64804056c3376ea3fcebe6379d46f8c
	List<Map<String, Object>> getAllBedAllocationsWithPatientNames();
}
