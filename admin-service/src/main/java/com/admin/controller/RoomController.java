package com.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.bean.RoomBean;
import com.admin.bean.RoomTypeBean;
import com.admin.entity.RoomEntity;
import com.admin.repository.RoomRepository;
import com.admin.service.RoomService;
@RestController
@RequestMapping("/room")
public class RoomController {
	@Autowired
	RoomService roomService;
	@Autowired
	RoomRepository roomRepository;
	
	@PostMapping(path = "/save")
	public ResponseEntity<RoomBean> save(@RequestBody RoomBean roomBean){
		roomService.save(roomBean);
		ResponseEntity<RoomBean> entity=new ResponseEntity<>(roomBean,HttpStatus.CREATED);
		System.out.println("inserted");
		return entity;
		
		
	}
	@GetMapping(path ="/getAll")
	public ResponseEntity<List<RoomBean>> getAll(){
		List<RoomBean> list=roomService.getAll();
		return new ResponseEntity<List<RoomBean>>(list,HttpStatus.OK);
	}
	@GetMapping(path ="/getByWardId/{id}")
	public ResponseEntity<List<RoomEntity>> getAllByWard(@PathVariable long id){
		List<RoomEntity> list=roomService.findByWardId(id);
		return new ResponseEntity<List<RoomEntity>>(list,HttpStatus.OK);
	}
	@GetMapping(path = "/getById/{id}")
	public ResponseEntity<RoomBean> getById(@PathVariable Long id){
		RoomBean roombyid = roomService.getById(id);
		return new ResponseEntity<RoomBean>(roombyid,HttpStatus.OK);	
	}
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<RoomEntity> delete(@PathVariable Long id){
		roomService.delete(id);
		return new ResponseEntity<RoomEntity>(HttpStatus.OK);
		
	}

	@PutMapping(path = "/update")
	public ResponseEntity<RoomBean> put(@RequestBody RoomBean roomBean) throws Exception {

		RoomBean roomBean1 = roomService.getById(roomBean.getId());
		if (roomBean1 != null) {
			roomBean1.setRoomSharing(roomBean.getRoomSharing());
			roomBean1.setRoomPrice(roomBean.getRoomPrice());
			roomBean1.setRoomNo(roomBean.getRoomNo());
			roomBean1.setWardId(roomBean.getWardId());
			roomBean1.setRoomTypeId(roomBean.getRoomTypeId());
			roomService.save(roomBean1);
		}
		ResponseEntity<RoomBean> responseEntity = new ResponseEntity<>(roomBean, HttpStatus.OK);
		return responseEntity;
	}
	
	@PutMapping("/updateStatus")
	public void put(@RequestBody RoomEntity roomEntity)
	{
		roomService.updateStatus(roomEntity);
	}

}
