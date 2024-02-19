package com.admin.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.admin.entity.OTPEntity;

import jakarta.transaction.Transactional;
@Repository
public interface OtpRepository  extends JpaRepository<OTPEntity, Integer>
{
	Optional<OTPEntity> findByEmail(String email);
	@Transactional
	@Modifying
    @Query("DELETE FROM OTPEntity o WHERE o.expirationTime <= :currentTime")
    void deleteExpiredOtps(@Param("currentTime") LocalDateTime currentTime);
}


