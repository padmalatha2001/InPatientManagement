package com.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.entity.BedAllocation;
@Repository
public interface BedAllocationRepository extends JpaRepository<BedAllocation,Integer>{

	public BedAllocation getByPatientId(int id);
}
