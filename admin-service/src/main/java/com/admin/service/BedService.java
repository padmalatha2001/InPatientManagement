package com.admin.service;

import java.util.List;

import com.admin.bean.BedBean;
import com.admin.entity.BedEntity;

public interface BedService {
	BedBean save(BedBean bedBean);
	
	List<BedBean> getAll();
	void delete(long bedId);
	void update(long bedId,BedBean updatedBed);
	List<BedBean> findByBedIdRoomEntityId(Long roomId);
	BedBean getById(long bedId);
}
