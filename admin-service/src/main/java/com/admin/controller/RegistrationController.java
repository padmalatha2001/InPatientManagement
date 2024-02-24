package com.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.bean.LoginBean;
import com.admin.bean.OtpBean;
import com.admin.bean.RegistrationBean;
import com.admin.entity.RegistrationForm;
import com.admin.exception.EmailAlreadyExistsException;
import com.admin.exception.EmailNotFoundException;
import com.admin.exception.RecordNotFoundException;
import com.admin.service.RegistrationService;

@RestController
@RequestMapping("register")
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController {

	@Autowired
	RegistrationService registrationService;
	private static Logger log = LoggerFactory.getLogger(RegistrationController.class.getSimpleName());

	@PostMapping("/save")
	public ResponseEntity<RegistrationBean> save(@RequestBody RegistrationBean registrationBean) {
		log.info("Saving Registration entity");
		try {
			registrationService.save(registrationBean);
			ResponseEntity<RegistrationBean> responseEntity = new ResponseEntity<>(registrationBean,
					HttpStatus.CREATED);
			log.info("Saving Registration entity is done");
			return responseEntity;
		} catch (EmailAlreadyExistsException e) {
			log.error("error handled");
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<RegistrationBean> getById(@PathVariable int id) {
		log.info("Fetching Department by Id");

		RegistrationBean registrationBean = registrationService.getById(id);
		log.info("Fetching Department by Id is done");
		return ResponseEntity.status(HttpStatus.OK).body(registrationBean);

	}

	@GetMapping("/getAll")
	public ResponseEntity<List<RegistrationBean>> getAll() {
		log.info("Fetching All Department details");

		List<RegistrationBean> list = registrationService.getAll();
		ResponseEntity<List<RegistrationBean>> responseEntity = new ResponseEntity<>(list, HttpStatus.OK);
		log.info("Fetching All Department details is done");
		return responseEntity;

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable int id) {
		log.info("Deleting Department by ID");
		try {
			registrationService.delete(id);
			ResponseEntity<String> responseEntity = new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
			log.info("Deleting Registration by ID is done");
			return responseEntity;
		} catch (RecordNotFoundException e) {
			log.error("error handled");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping()
	public ResponseEntity<String> update(@RequestBody RegistrationBean registrationBean) {

		log.info("Updating Department");
		registrationService.update(registrationBean);
		ResponseEntity<String> responseEntity = new ResponseEntity<>("Department updated Successfully", HttpStatus.OK);
		log.info("Updating Department is done");
		return responseEntity;
	}
	@PostMapping("/login")
	public ResponseEntity<RegistrationForm> login(@RequestBody LoginBean loginBean) {

		RegistrationForm user = registrationService.validateLogin(loginBean);

		if (user != null) {
			System.out.println(user + "login successfull");
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}

	@GetMapping("/generateOtp")
	public ResponseEntity<RegistrationForm> generateOtpAndSendEmail(@RequestParam("email") String email) {

		try {
			RegistrationForm user = registrationService.forgetPassword(email);
			if (user != null) {

				return new ResponseEntity<RegistrationForm>(user, HttpStatus.OK);
			} else {
				return new ResponseEntity<RegistrationForm>(HttpStatus.UNAUTHORIZED);
			}
		} catch (EmailNotFoundException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<RegistrationForm>(HttpStatus.NOT_FOUND);

		}
	}

	@PostMapping("/verify")
	public ResponseEntity verifyOtp(@RequestParam String email, @RequestParam String enteredOtp) {
		try {
			if (registrationService.verifyOtp(email, enteredOtp)) {
				String jsonString = "{\"message\":\"Verified Successfully\"}";

		        // Set Content-Type header to application/json
		        return ResponseEntity.status(HttpStatus.OK)
		                .header("Content-Type", "application/json")
		                .body(jsonString);
             // System.out.println("successfull");
				//return  new ResponseEntity(HttpStatus.OK);
			} else {
				String jsonString = "{\"message\":\"Invalid OTP\"}";

		        // Set Content-Type header to application/json
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		                .header("Content-Type", "application/json")
		                .body(jsonString);
				//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP");
			}
		} catch (RuntimeException e) {
			// Handle exceptions thrown during OTP verification
			//System.out.println("error occured");
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			String jsonString = "{\"message\":\"error occured\"}";

	        // Set Content-Type header to application/json
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .header("Content-Type", "application/json")
	                .body(jsonString);

		}
	}
}
//	@PostMapping("/getdetails")
//	public ResponseEntity<String>getDetails(@RequestBody String email,String password)
//	{
//		boolean validateLogin = registrationService.validateLogin(email, password);
//		return new ResponseEntity<String>("Login successfull",HttpStatus.OK);
//		
//	}
//	

//	@GetMapping("/getDetails")
//	public ResponseEntity<String> getDetails(@RequestBody String email ,@RequestBody String password){
//
////		log.info("Updating Department");
////		registrationService.getDetails(email, password);
////			ResponseEntity<String> responseEntity = new ResponseEntity<>("Login Successfully", HttpStatus.OK);
////			log.info("Updating Department is done");
////			return responseEntity;
//		boolean loginSuccessful = registrationService.getDetails(email, password);
//
//        if (loginSuccessful) {
//            return ResponseEntity.ok("Login Successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
//        }
//    }
