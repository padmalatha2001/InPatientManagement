package com.admin.service.implementation;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.admin.bean.LoginBean;
import com.admin.bean.RegistrationBean;
import com.admin.controller.RegistrationController;
import com.admin.entity.OTPEntity;
import com.admin.entity.RegistrationForm;
import com.admin.exception.EmailAlreadyExistsException;
import com.admin.exception.EmailNotFoundException;
import com.admin.exception.InvalidOtpException;
import com.admin.exception.PasswordMismatchException;
import com.admin.exception.RecordNotFoundException;
import com.admin.repository.OtpRepository;
import com.admin.repository.RegistrationRepository;
import com.admin.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	RegistrationRepository registrationRepository;
	@Autowired
	OtpRepository otpRepository;
	private static Logger log = LoggerFactory.getLogger(RegistrationController.class.getSimpleName());

	@Override
	public RegistrationBean save(RegistrationBean registrationBean) {
		log.info("save the registration details");

		if (registrationRepository.existsByEmail(registrationBean.getEmail())) {
			log.info("emaiId is already exits");
			throw new EmailAlreadyExistsException("Email is already exit...");

		} else {
			RegistrationForm registrationEntity = new RegistrationForm();
			beanToEntity(registrationBean, registrationEntity);
			registrationRepository.save(registrationEntity);
			log.info("save the details sucessfully");
			return registrationBean;
		}

	}

	private void beanToEntity(RegistrationBean registrationBean, RegistrationForm registrationEntity) {

		registrationEntity.setId(registrationBean.getId());
		registrationEntity.setFirstName(registrationBean.getFirstName());
		registrationEntity.setLastName(registrationBean.getLastName());
		registrationEntity.setEmail(registrationBean.getEmail());
		registrationEntity.setGender(registrationBean.getGender());
		registrationEntity.setDateOfBirth(registrationBean.getDateOfBirth());
		registrationEntity.setPassword(registrationBean.getPassword());
		registrationEntity.setServiceType(registrationBean.getServiceType());
		registrationEntity.setPhoneNumber(registrationBean.getPhoneNumber());

	}

	@Override
	public RegistrationBean getById(int id) {
		RegistrationForm registrationEntity = registrationRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("No Record Found with given id"));
		RegistrationBean registrationBean = new RegistrationBean();
		entityToBean(registrationEntity, registrationBean);
		return registrationBean;
	}

	private void entityToBean(RegistrationForm registrationEntity, RegistrationBean registrationBean) {
		registrationBean.setId(registrationEntity.getId());
		registrationBean.setFirstName(registrationEntity.getFirstName());
		registrationBean.setLastName(registrationEntity.getLastName());
		registrationBean.setEmail(registrationEntity.getEmail());
		registrationBean.setGender(registrationEntity.getGender());
		registrationBean.setDateOfBirth(registrationEntity.getDateOfBirth());
		registrationBean.setPassword(registrationEntity.getPassword());
		registrationBean.setPhoneNumber(registrationEntity.getPhoneNumber());
		registrationBean.setServiceType(registrationEntity.getServiceType());
		System.out.println(registrationBean.getPhoneNumber());

	}

	@Override
	public List<RegistrationBean> getAll() {
		log.info("getting the registration details");
		List<RegistrationForm> entityList = registrationRepository.findAll();
		List<RegistrationBean> beanList = new ArrayList<>();
		entityToBean(entityList, beanList);
		log.info("get the registration details sucessfully");
		return beanList;
	}

	private void entityToBean(List<RegistrationForm> entityList, List<RegistrationBean> beanList) {
		for (RegistrationForm registrationEntity : entityList) {
			RegistrationBean registrationBean = new RegistrationBean();
			entityToBean(registrationEntity, registrationBean);
			beanList.add(registrationBean);

		}

	}

	@Override
	public void delete(int id) {
		RegistrationForm RegistrationForm = registrationRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("No Record Found with given id"));
		log.info("deteled by using id");
		registrationRepository.delete(RegistrationForm);
	}

	@Override
	public void update(RegistrationBean registration) {
		RegistrationForm RegistrationForm = registrationRepository.findById(registration.getId())
				.orElseThrow(() -> new RecordNotFoundException("No Record Found with given id"));
		beanToEntity(registration, RegistrationForm);
		registrationRepository.save(RegistrationForm);
	}

	@Override
	public RegistrationForm validateLogin(LoginBean loginBean) {
		RegistrationForm user = registrationRepository.findByEmail(loginBean.getEmail());
		log.info("login by using email and password");
		try {
			;
			if (user != null) {
				log.info("email is valid");

				if (user.getPassword().equals(loginBean.getPassword())) {
					log.info("login sucessfull");
					return user;
				} else {
					try {
						log.info("login faild");
						throw new PasswordMismatchException("Password is wrong");
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			} else {
				throw new EmailNotFoundException("Email not found");
			}
			return null;
		} catch (EmailNotFoundException e) {
			throw new EmailNotFoundException("Email not found");
		}

	}

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public String generateOtp() {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		return String.valueOf(otp);
	}

	@Override
	public void sendOtpEmail(String toEmail, String otp) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(toEmail);
		message.setSubject("Your OTP");
		message.setText("Your OTP is: " + otp);

		javaMailSender.send(message);
	}

	@Override
	public RegistrationForm forgetPassword(String email) {
		log.info("checking email is present or not");

		RegistrationForm user = registrationRepository.findByEmail(email);

		if (user != null) {
			log.info(" email is valid");
			String otp = generateOtp();

			Timestamp expirationTime = Timestamp.from(Instant.now().plus(Duration.ofMinutes(5)));

			sendOtpEmail(email, otp);
			saveOtp(email, otp, expirationTime);
			return user;
		} else {
			log.info("email is not not valid");
			throw new EmailNotFoundException("Email not found");

		}

	}

	@Override
	public void saveOtp(String email, String otp, Timestamp expirationTime) {
		Optional<OTPEntity> existingOtp = otpRepository.findByEmail(email);

		if (existingOtp.isPresent()) {
			existingOtp.get().setOtp(otp);
			existingOtp.get().setExpirationTime(expirationTime);
			otpRepository.save(existingOtp.get());
		} else {
			OTPEntity newOtp = new OTPEntity();
			newOtp.setEmail(email);
			newOtp.setOtp(otp);
			newOtp.setExpirationTime(expirationTime);
			otpRepository.save(newOtp);
		}
	}

//	@Override
//	public boolean verifyOtp(String email, String enteredOtp) {
//		OTPEntity otpEntity = otpRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("OTP not found"));
//
//		if (otpEntity.getExpirationTime().isBefore(LocalDateTime.now())) {
//			throw new RuntimeException("OTP has expired");
//		}
//
//		// Check if the entered OTP matches the one stored in the database
//		if (!enteredOtp.equals(otpEntity.getOtp())) {
//			throw new InvalidOtpException("Invalid OTP");
//		}
//
//		// Clear the OTP after successful verification (optional)
//		//otpRepository.delete(otpEntity);
//
//		return true;
//	}
	@Override
	public boolean verifyOtp(String email, String enteredOtp) {

		OTPEntity otpEntity = otpRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("OTP not found"));

		// Get the expiration time as a Timestamp
		Timestamp expirationTime = otpEntity.getExpirationTime();

		// Convert the Timestamp to Instant and then to LocalDateTime
		LocalDateTime expirationLocalDateTime = expirationTime.toInstant().atZone(ZoneId.systemDefault())
				.toLocalDateTime();

		if (expirationLocalDateTime.isBefore(LocalDateTime.now())) {
			// throw new RuntimeException("OTP has expired");
			return false;
		}

		// Check if the entered OTP matches the one stored in the database
		else if (!enteredOtp.equals(otpEntity.getOtp())) {
			throw new InvalidOtpException("Entered Correct otp");
		} else {
			return true;
		}
	}

	@Override

	public void updatePassword(String email, String password) {
		RegistrationForm result = registrationRepository.findByEmail(email);
		if (result != null) {

			result.setPassword(password);
			registrationRepository.save(result);

		}
	}

	@Scheduled(fixedRate = 600000) // 5 minutes in milliseconds
	public void cleanupExpiredOtps() {
		try {
			System.out.println("scheduled start");
			
			otpRepository.deleteExpiredOtps();
			// logger.info("Expired OTPs cleaned up successfully.");
			System.out.println("scheduled end");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// logger.error("Error cleaning up expired OTPs: " + e.getMessage(), e);
		}
	}
}

//		RegistrationForm registrationEntity=new RegistrationForm();
//		RegistrationBean bean=new RegistrationBean();
//		Optional<RegistrationForm> details = registrationRepository.findByEmailAndPassword(email, password);
//		String mail=details.get().getEmail();
//		String pass=details.get().getPassword();
//		entityToBean(registrationEntity, bean);
//		String mailId=bean.getEmail();
//		String password1=bean.getPassword();
//		if(mail.equalsIgnoreCase(mailId)&&pass.equalsIgnoreCase(password1))
//		{
//			System.out.println("Login Sucessfully");
//		}
//		else
//		{
//			System.out.println("login faild");
//		}
