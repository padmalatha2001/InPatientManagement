 package com.patient.billing.service.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.patient.billing.service.dto.PatientBillingDTO;
import com.patient.billing.service.entity.PatientBillingEntity;

import jakarta.transaction.Transactional;
@Transactional
@EnableJpaRepositories
public interface PatientBillingRepository  extends JpaRepository<PatientBillingEntity, Integer>
{
	
//	select * from billing join bed_allocation 
//	ON billing.bed_allocation_id=bed_allocation.bed_allocation_id
//	join  patientregistration on bed_allocation.patient_id=patientregistration.patient_id;
//  getAllThedetails()
  
//  @Query(value="SELECT * FROM billing b  " +
//          "JOIN bed_allocation ba   ON b.bed_allocation_id = ba.bed_allocation_id " +
//          "JOIN patientregistration p  ON ba.patient_id = p.patient_id",nativeQuery = true)
	@Query("SELECT new com.patient.billing.service.dto.PatientBillingDTO (b.billId,b.billingDate,b.bedAllocationId,b.paidAmount,"
	  		+ "b.discount,b.totalAmount,b.paymentStatus,p.firstName,p.lastName) " +
		        "FROM PatientBillingEntity b " +
		        " JOIN BedAllocation ba ON b.bedAllocationId = ba.id " +
		        " JOIN PatientEntity p ON ba.patientId = p.patientId " )
   List getBillingResults();
  
//  
//  @Query(value="SELECT * FROM billing p WHERE MONTH(p.bill_date) = :month",nativeQuery=true)
//  List<PatientBillingEntity> findByMonth(@Param("month") int month);
// 
  
//  @Query(value="SELECT * FROM billing b "+
//	  		"JOIN bed_allocation ba ON b.bed_allocation_id = ba.bed_allocation_id "+
//	  		"JOIN patientregistration p ON ba.patient_id = p.patient_id "+
//	  		" WHERE MONTH(b.bill_date) = :month",nativeQuery=true)
  
  
  //@Query(value="SELECT b.bill_id , b.bill_date, b.discount,b.paid_amount,b.payment_status,b.total_amount,ba.bed_allocation_id,ba.patient_id, p.patient_id  FROM billing b JOIN bed_allocation ba ON b.bed_allocation_id = ba.bed_allocation_id JOIN patientregistration p ON ba.patient_id = p.patient_id WHERE MONTH(b.bill_date) = :month",nativeQuery=true)

	  //List<PatientBillingEntity> findByMonth(@Param("month") int month);
//  @Query(value="SELECT b.*, p.first_name AS patientFirstName, p.last_name AS patientLastName " +
//          "FROM billing b " +
//          "JOIN bed_allocation ba ON b.bed_allocation_id = ba.bed_allocation_id " +
//          "JOIN patientregistration p ON ba.patient_id = p.patient_id " +
//          "WHERE MONTH(b.bill_date) = :month",nativeQuery = true)
//  @Query(value="SELECT b.*, " +
//          "(SELECT p.first_name FROM patientregistration p WHERE p.patient_id = ba.patient_id) AS patientFirstName, " +
//          "(SELECT p.last_name FROM patientregistration p WHERE p.patient_id = ba.patient_id) AS patientLastName " +
//          "FROM billing b " +
//          "JOIN bed_allocation ba ON b.bed_allocation_id = ba.bed_allocation_id " +
//          "WHERE MONTH(b.bill_date) = :month", nativeQuery = true)
   //List<PatientBillingEntity> findByMonth(@Param("month") int month);
  
  
  @Query("SELECT new com.patient.billing.service.dto.PatientBillingDTO (b.billId,b.billingDate,b.bedAllocationId,b.paidAmount,"
  		+ "b.discount,b.totalAmount,b.paymentStatus,p.firstName,p.lastName) " +
	        "FROM PatientBillingEntity b " +
	        " JOIN BedAllocation ba ON b.bedAllocationId = ba.id " +
	        " JOIN PatientEntity p ON ba.patientId = p.patientId " +
	        " WHERE b.billingDate BETWEEN :startDate AND :endDate")
	List<PatientBillingDTO> findByBillingDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
/*
  @Query(value="SELECT  p.first_name AS patientFirstName " +
          "FROM billing b " +
          "JOIN bed_allocation ba ON b.bed_allocation_id = ba.bed_allocation_id " +
          "JOIN patientregistration p ON ba.patient_id = p.patient_id " +
          "WHERE b.bill_date BETWEEN :startDate AND :endDate", nativeQuery = true)
// @Query(value= "SELECT * FROM billing bill  " +
//         "JOIN bed_allocation bed   ON bill.bed_allocation_id = bed.bed_allocation_id " +
//         "JOIN patientregistration p  ON bed.patient_id = p.patient_id where bill.bill_date BETWEEN:startDate AND :endDate",nativeQuery = true)

  List<PatientBillingDTO> findByBillingDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
*/
//  List<PatientBillingDTO> findByBillingDateBetween(LocalDate startDate, LocalDate endDate);
//  @Query(value = "SELECT * FROM billing b " +
//	        "JOIN bed_allocation ba ON b.bed_allocation_id = ba.bed_allocation_id " +
//	        "JOIN patientregistration p ON ba.patient_id = p.patient_id " +
//	        "WHERE b.bill_date BETWEEN :startDate AND :endDate", nativeQuery = true)
//  List<PatientBillingEntity> findByBillingDateBetween(@Param("startDate") LocalDate startDate, 
//	        @Param("endDate") LocalDate endDate);
}

