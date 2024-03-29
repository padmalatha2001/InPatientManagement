package com.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.bean.RoomTypeBean;
import com.admin.entity.RoomType;
import com.admin.service.RoomTypeService;

@Controller
@RequestMapping("/roomType")
public class RoomTypeController {
	@Autowired
	RoomTypeService roomTypeService;
	@PostMapping(path = "/save")
	public ResponseEntity<RoomTypeBean> save(@RequestBody RoomTypeBean roomTypeBean){
		roomTypeService.save(roomTypeBean);
		ResponseEntity<RoomTypeBean> entity=new ResponseEntity<>(roomTypeBean,HttpStatus.CREATED);
		System.out.println("inserted");
		return entity;
		
		
	}
	@GetMapping(path ="/getAll")
	public ResponseEntity<List<RoomTypeBean>> getAll(){
		List<RoomTypeBean> list=roomTypeService.getAll();
		return new ResponseEntity<List<RoomTypeBean>>(list,HttpStatus.OK);
	}
	
	@GetMapping(path = "/getById/{id}")
	public ResponseEntity<RoomTypeBean> getById(@PathVariable Long id){
		RoomTypeBean roombyid = roomTypeService.getById(id);
		return new ResponseEntity<RoomTypeBean>(roombyid,HttpStatus.OK);	
	}
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity delete(@PathVariable Long id){
		roomTypeService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
		
	}
	
	@PutMapping("/updateStatus")
	public void put(@RequestBody RoomType roomType)
	{
		roomTypeService.updateStatus(roomType);
	}

}
