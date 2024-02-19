package com.admin.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public interface OTPService {
	void saveOtp(String email, String otp, Timestamp expirationTime);

}
