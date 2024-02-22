package com.admin.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.bean.DepartmentBean;
import com.admin.bean.RoomBean;
import com.admin.bean.RoomTypeBean;
import com.admin.bean.WardBean;
import com.admin.entity.Department;
import com.admin.entity.RoomEntity;
import com.admin.entity.RoomType;
import com.admin.entity.Ward;
import com.admin.exception.RecordNotFoundException;
import com.admin.repository.RoomRepository;
import com.admin.repository.WardRepository;
import com.admin.service.RoomService;

import jakarta.persistence.EntityManager;

@Service
public class RoomServiceImplementation implements RoomService {

	@Autowired
	RoomRepository roomRepository;
	@Autowired
	private WardRepository wardRepository;

	@Override
	public void save(RoomBean roomBean) {
//		RoomEntity roomEntity=new RoomEntity();
//	     beanToEntity(roomEntity,roomBean);
//		 RoomRepository.save(roomEntity);

		WardBean ward = roomBean.getWardId();
		Integer totalRoomSharing = roomRepository.sumRoomSharingByWard(ward.getId());
		if (totalRoomSharing == null) {
			totalRoomSharing = 0;
		}
		if (totalRoomSharing + roomBean.getRoomSharing() <= ward.getCapacity()) {
			RoomEntity roomEntity = new RoomEntity();
			beanToEntity(roomEntity, roomBean);
			roomRepository.save(roomEntity);
		} else {
			throw new RecordNotFoundException("Room capacity exceeded for ward");
		}

	}

	@Override
	public List<RoomBean> getAll() {
		List<RoomBean> roomBean = new ArrayList<>();
		List<RoomEntity> roomEntity = roomRepository.findAll();
		entityToBean(roomEntity, roomBean);
		return roomBean;
	}

	@Override
	public RoomBean getById(long id) {
		RoomBean roomBean = new RoomBean();
		RoomEntity roomEntity = roomRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("record not found"));
		entityToBean(roomEntity, roomBean);
		return roomBean;

	}

	@Override
	public RoomBean update(long id) {
		RoomEntity room = roomRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("record not found"));
		RoomBean roomBean = new RoomBean();
		entityToBean(room, roomBean);
		return roomBean;

	}

	@Override
	public void delete(long id) {
		roomRepository.deleteById(id);

	}

	public void beanToEntity(RoomEntity roomEntity, RoomBean roomBean) {
		roomEntity.setId(roomBean.getId());
		roomEntity.setRoomNo(roomBean.getRoomNo());
		RoomTypeBean roomTypeBean = roomBean.getRoomTypeId();
		RoomType roomType = new RoomType();
		beanToEntity(roomTypeBean, roomType);
		roomEntity.setRoomTypeId(roomType);
		roomEntity.setRoomPrice(roomBean.getRoomPrice());
		roomEntity.setRoomSharing(roomBean.getRoomSharing());
		roomEntity.setAvailability(roomBean.getAvailability());
		WardBean wardBean = roomBean.getWardId();
		Ward entity = new Ward();
		beanToEntity(entity, wardBean);
		roomEntity.setWardId(entity);
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

	private void entityToBean(RoomType roomType, RoomTypeBean roomTypeBean) {
		// TODO Auto-generated method stub
		roomTypeBean.setId(roomType.getId());
		roomTypeBean.setName(roomType.getName());
	}

	public void entityToBean(List<RoomEntity> listEntity, List<RoomBean> listbean) {

		for (RoomEntity roomEntity : listEntity) {
			RoomBean roomBean = new RoomBean();
			roomBean.setId(roomEntity.getId());
			roomBean.setRoomNo(roomEntity.getRoomNo());
			RoomType roomType = roomEntity.getRoomTypeId();
			RoomTypeBean roomTypeBean = new RoomTypeBean();
			entityToBean(roomType, roomTypeBean);
			roomBean.setRoomTypeId(roomTypeBean);
			roomBean.setAvailability(roomEntity.getAvailability());
			roomBean.setRoomPrice(roomEntity.getRoomPrice());
			roomBean.setRoomSharing(roomEntity.getRoomSharing());
			WardBean wardBean = new WardBean();
			Ward entity = roomEntity.getWardId();
			entityToBean(wardBean, entity);
			roomBean.setWardId(wardBean);
			listbean.add(roomBean);
		}
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
		roomBean.setAvailability(roomEntity.getAvailability());

		Ward entity = roomEntity.getWardId();
		WardBean wardBean = new WardBean();
		entityToBean(wardBean, entity);
		roomBean.setWardId(wardBean);

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
	public List<RoomEntity> findByWardId(Long wardId) {
		// TODO Auto-generated method stub
		return roomRepository.findByWardId_Id(wardId);
	}

}
