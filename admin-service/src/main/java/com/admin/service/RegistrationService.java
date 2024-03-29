package com.admin.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.admin.bean.LoginBean;
import com.admin.bean.OtpBean;
import com.admin.bean.RegistrationBean;
import com.admin.entity.RegistrationForm;

public interface RegistrationService {
      RegistrationBean save(RegistrationBean registration);
      RegistrationBean getById(int id);
      List<RegistrationBean> getAll();
      void delete(int id);
      void update(RegistrationBean registration);
      //boolean getDetails(String email);
	RegistrationForm validateLogin(LoginBean loginBean);
	 RegistrationForm forgetPassword(String email);
    void sendOtpEmail(String toEmail, String otp);
	String generateOtp();
		void saveOtp(String email, String otp, Timestamp expirationTime);
	boolean verifyOtp(String email, String enteredOtp);
}
