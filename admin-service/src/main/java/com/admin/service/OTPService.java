package com.admin.service;

import java.time.LocalDateTime;

public interface OTPService {
	void saveOtp(String email, String otp, LocalDateTime expirationTime);

}
