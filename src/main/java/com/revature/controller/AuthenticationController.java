package com.revature.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.cloud.client.discovery.EnableDiscoveryClient;*/
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entity.Email;
import com.revature.entity.dto.PatientDto;
import com.revature.service.LoginService;
import com.revature.service.RegisterService;
import com.revature.serviceImpl.EmailService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
//@EnableDiscoveryClient
@CrossOrigin(origins="*") 
@RequestMapping("/api/v1")
public class AuthenticationController {
	
	@Autowired
	private  EmailService emailService;
	
	@Autowired
	private RegisterService registerService;
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/patient/register")
	public ResponseEntity<PatientDto> registerPatient(@RequestBody @Valid PatientDto patientDto){
		PatientDto patient=null;
		try {
			patient=registerService.registerService(patientDto);
		}
		catch (NullPointerException  e) {
			return new ResponseEntity<PatientDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch (NoSuchElementException e) {
			return new ResponseEntity<PatientDto>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<PatientDto>(patient,HttpStatus.OK);
	}
	@PostMapping("/patient/login")
	public ResponseEntity<PatientDto> loginPatient(@RequestParam("email") String email,
			@RequestParam("password") String password){
		System.out.println(email+" "+password);
		PatientDto dto=null;
		try {
		dto = loginService.loginService(email,password);
		}
		catch (NullPointerException e) {
			return new ResponseEntity<PatientDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch (NoSuchElementException e) {
			return new ResponseEntity<PatientDto>(HttpStatus.NO_CONTENT);
		}
		System.out.println(dto.getEmail()+" "+dto.getPassword());
		return new ResponseEntity<PatientDto>(dto,HttpStatus.OK);		
	}

	@PutMapping("/patient/{email}/{password}")
	@Transactional
	public void updateStatusById(@PathVariable @Valid String email,@PathVariable @Valid String password) {
		System.out.println(email+" "+password);
		loginService.updatepssword(email,password);
	}
	
	@PostMapping(value = "/sendemail")
	public ResponseEntity<?> sendEmail(@RequestBody Email email){
		System.out.println(email);
		boolean result =this.emailService.sendEmail(email.getSubject(), email.getMessage(),email.getToMail());
		if(result) {
			return ResponseEntity.ok("Email is sent successfully.");
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email Not send");
		}
	}
}
