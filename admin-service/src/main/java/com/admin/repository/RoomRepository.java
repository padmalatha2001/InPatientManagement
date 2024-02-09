package com.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.admin.entity.RoomEntity;

public interface RoomRepository extends JpaRepository<RoomEntity,Long>{
	List<RoomEntity> findByWardId_Id(Long wardId);
}
