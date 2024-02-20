package com.admin.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.bean.BedBean;
import com.admin.bean.DepartmentBean;
import com.admin.bean.RoomBean;
import com.admin.bean.RoomTypeBean;
import com.admin.bean.WardBean;
import com.admin.entity.BedEntity;
import com.admin.entity.Department;
import com.admin.entity.RoomEntity;
import com.admin.entity.RoomType;
import com.admin.entity.Ward;
import com.admin.exception.RecordNotFoundException;
import com.admin.repository.BedEntityRepository;
import com.admin.service.BedService;

@Service
public class BedServiceImpl implements BedService {
   
	@Autowired
	BedEntityRepository bedEntityRepository;
	
	@Override
	public BedBean save(BedBean bedBean) {
		// TODO Auto-generated method stub
		BedEntity bedEntity=new BedEntity();
		beanToEntity(bedBean,bedEntity);
		bedEntityRepository.save(bedEntity);
		return bedBean;
	}

	private void beanToEntity(BedBean bedBean, BedEntity bedEntity) {
		bedEntity.setId(bedBean.getId()); 
		bedEntity.setBedNo(bedBean.getBedNo());
		bedEntity.setStatus(bedBean.getStatus());
		RoomBean roomBean=bedBean.getRoomId();
	    RoomEntity roomEntity=new RoomEntity();
	    beanToEntity(roomEntity,roomBean);
	    bedEntity.setRoomId(roomEntity);
	    
	}

	public void beanToEntity(RoomEntity roomEntity, RoomBean roomBean) {
		roomEntity.setId(roomBean.getId());
		roomEntity.setRoomNo(roomBean.getRoomNo());
		RoomTypeBean roomTypeBean=roomBean.getRoomTypeId();
		RoomType roomType=new RoomType();
		beanToEntity(roomTypeBean,roomType);
		roomEntity.setRoomTypeId(roomType);
		roomEntity.setRoomPrice(roomBean.getRoomPrice());
		roomEntity.setRoomSharing(roomBean.getRoomSharing());
		WardBean wardBean=roomBean.getWardId();
		Ward ward=new Ward();
		beanToEntity(ward,wardBean);
		roomEntity.setWardId(ward);
	}
	private void beanToEntity(Ward ward, WardBean wardBean) {
		ward.setId(wardBean.getId());
		ward.setName(wardBean.getName());
		ward.setCapacity(wardBean.getCapacity());
		ward.setAvailability(wardBean.getAvailability());
		DepartmentBean DepartmentBean = wardBean.getDepartmentId();
		Department Department = new Department();
		beanToEntity(DepartmentBean, Department);
		ward.setDepartmentId(Department);

	}
	
	public void beanToEntity(DepartmentBean DepartmentBean, Department Department) {
		Department.setId(DepartmentBean.getId());
		Department.setName(DepartmentBean.getName());

	}
	private void beanToEntity(RoomTypeBean roomTypeBean, RoomType roomType) {
		// TODO Auto-generated method stub
		roomType.setId(roomTypeBean.getId());
		roomType.setName(roomTypeBean.getName());
	}

	@Override
	public BedBean getById(long bedId) {
		// TODO Auto-generated method stub
		
		BedEntity bedEntity= bedEntityRepository.findById(bedId).orElseThrow(()->new RecordNotFoundException("No Record Found with given id"));
		BedBean bedBean=new BedBean();
		entityToBean(bedEntity,bedBean);
		return bedBean;
	
	}

	private void entityToBean(BedEntity bedEntity, BedBean bedBean) {
		// TODO Auto-generated method stub
		bedBean.setId(bedEntity.getId());
		bedBean.setBedNo(bedEntity.getBedNo());
		RoomEntity roomEntity=bedEntity.getRoomId();
		RoomBean roomBean=new RoomBean();
		entityToBean(roomEntity,roomBean);
		bedBean.setRoomId(roomBean);
		bedBean.setStatus(bedEntity.getStatus());
	}
public void entityToBean(RoomEntity roomEntity, RoomBean roomBean) {

		
		roomBean.setId(roomEntity.getId());

		RoomType roomType = roomEntity.getRoomTypeId();
		RoomTypeBean roomTypeBean = new RoomTypeBean();
		entityToBean(roomType, roomTypeBean);
		roomBean.setRoomTypeId(roomTypeBean);

		roomBean.setRoomNo(roomEntity.getRoomNo());
		roomBean.setRoomPrice(roomEntity.getRoomPrice());
		roomBean.setRoomSharing(roomEntity.getRoomSharing());
		
		Ward entity = roomEntity.getWardId();
		WardBean wardBean = new WardBean();
		entityToBean(wardBean, entity);
		roomBean.setWardId(wardBean);

	}

private void entityToBean(RoomType roomType, RoomTypeBean roomTypeBean) {
	// TODO Auto-generated method stub
	roomTypeBean.setId(roomType.getId());
	roomTypeBean.setName(roomType.getName());
}
	private void entityToBean(WardBean wardBean, Ward ward) {
		wardBean.setId(ward.getId());
		wardBean.setName(ward.getName());
		wardBean.setCapacity(ward.getCapacity());
		wardBean.setAvailability(ward.getAvailability());
		DepartmentBean DepartmentBean = new DepartmentBean();
		Department Department = ward.getDepartmentId();
		entityToBean(Department, DepartmentBean);
		wardBean.setDepartmentId(DepartmentBean);

	}
	public void entityToBean(Department Department, DepartmentBean DepartmentBean) {
		DepartmentBean.setId(Department.getId());
		DepartmentBean.setName(Department.getName());
	}

	

	@Override
	public List<BedBean> getAll() {
		// TODO Auto-generated method stub
		List<BedEntity> entityList=bedEntityRepository.findAll();
		List<BedBean> beanList=new ArrayList<>();
		entityToBean(entityList,beanList);
		return beanList;
	}

	private void entityToBean(List<BedEntity> entityList, List<BedBean> beanList) {
		// TODO Auto-generated method stub
		for(BedEntity bedEntity:entityList)
		{
			BedBean bedBean=new BedBean();
			entityToBean(bedEntity,bedBean);
			beanList.add(bedBean);
		}
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		bedEntityRepository.deleteById(id);
	}

	@Override
	public void update(long bedId,BedBean updatedBed) {
		// TODO Auto-generated method stub
		BedEntity bedEntity = bedEntityRepository.findById(bedId)
				.orElseThrow(() -> new RecordNotFoundException("No Record Found with given id"));
		if (bedEntity != null) {
			bedEntity.setBedNo(updatedBed.getBedNo());
			RoomBean room=updatedBed.getRoomId();
			RoomEntity roomEntity=new RoomEntity();
			beanToEntity(roomEntity,room);
			bedEntity.setRoomId(roomEntity);
			bedEntity.setStatus(updatedBed.getStatus());
//			if (bedEntity.getStatus().equalsIgnoreCase("Active")) {
//				bedEntity.setStatus("InActive");
//			} else {
//				bedEntity.setStatus("Active");
//			}
		}
		bedEntityRepository.save(bedEntity);
		
	}
	@Override
	public List<BedBean> findByBedIdRoomEntityId(Long roomEntityId) {
		// TODO Auto-generated method stub
		List<BedEntity> entityList=bedEntityRepository.findByRoomId_Id(roomEntityId);
		List<BedBean> beanList=new ArrayList<>();
		entityToBean(entityList,beanList);
		return beanList;
	}

	
	
}
