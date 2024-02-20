package com.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.entity.RegistrationForm;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationForm,Integer> {
	RegistrationForm findByEmail(String email);

}
