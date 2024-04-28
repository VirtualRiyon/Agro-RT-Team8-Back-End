package com.jsp.Agro_bootRT.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Agro_bootRT.entity.User;
import com.jsp.Agro_bootRT.service.UserService;
import com.jsp.Agro_bootRT.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.POST, RequestMethod.GET,RequestMethod.DELETE, RequestMethod.PUT})

public class UserController {
	@Autowired
	private UserService service;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<User>> saveMovie(@RequestBody User user) {
		ResponseEntity<ResponseStructure<User>> db = service.saveUser(user);
		String email=user.getEmail();
		String emailtext="Dear "+user.getFirstName()+"\r\n"
				+ "\r\n"
				+ "I am delighted to inform you that your registration has been successfully processed and confirmed. You are now officially registered with Agro.\r\n"
				+ "\r\n"
				+ "Your registration details are as follows:\r\n"
				+ "\r\n"
				+ "Name: "+user.getFirstName()+"\r\n"
				+ "Email ."+"Address: " +user.getEmail()+"\r\n"
				+ "Please keep this information safe for your records.\r\n"
				+ "\r\n"
				+ "If you have any queries or require further assistance, please feel free to reach out to us at agrotechteam8@gmail.com\r\n"
				+ "\r\n"
				+ "We look forward to your participation and engagement with us. Thank you for choosing to register with Agro.\r\n"
				+ "\r\n"
				+ "Best regards,\r\n"
				+ "\r\n"
				+ "Team8 Agro\r\n"
				+ "agrotechteam8@gmail.com\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "";
		System.out.println(email);
		if(email!=null) {
			service.sendSimpleMail(email, emailtext, "Agro \n Thanks For Registration With Us \n Team-8");
		}
		return db;
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user){
		return service.UpdateUser(user);
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<ResponseStructure<User>> fetchUserByid(@RequestParam int id){
		return service.UserfetchByid(id);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseStructure<User>> deletUserByid(@RequestParam int id){
		return service.deleteStudent(id);
	}
	  @GetMapping("/login")
	  public ResponseEntity<ResponseStructure<User>> login(@RequestParam String email,@RequestParam String password){
	      return service.login(email,password);
	  }

	@GetMapping("/fetchall")
	public ResponseEntity<ResponseStructure<List<User>>> userFetchAll(){
		return service.UserfetchAll();
	}
	
	@GetMapping("/Otp")
	public ResponseEntity<ResponseStructure<Integer>> sendOtp(@RequestParam String email){
		return service.SendOtp(email);
	}
	
}
