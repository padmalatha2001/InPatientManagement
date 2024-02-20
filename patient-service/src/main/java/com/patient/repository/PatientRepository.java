package com.patient.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patient.entity.PatientEntity;
@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Integer>{


//	 SELECT p.first_name,p.last_name,
//     bed.bed_no,
//    room.room_no,
//    ward.ward_name,
//    department.department_name
//FROM patientregistration p
//JOIN bed_allocation ba ON p.patient_id = ba.patient_id
//JOIN bed ON ba.bed_id= bed.bed_id
//JOIN room ON bed.room_id = room.room_id
//JOIN ward ON room.ward_id = ward.ward_id
//JOIN department ON ward.department_id = department.dept_id
//JOIN doctor d ON p.doctor = d.doctor_id 
//WHERE d.doctor_name = 'Radha'
//AND CURRENT_DATE BETWEEN ba.start_date AND ba.end_date;

}
