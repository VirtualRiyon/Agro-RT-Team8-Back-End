package com.jsp.Agro_bootRT.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Agro_bootRT.entity.User;
import com.jsp.Agro_bootRT.service.UserService;
import com.jsp.Agro_bootRT.util.ResponseStructure;

@RestController
public class UserController {
	@Autowired
	private UserService service;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<User>> saveMovie(@RequestBody User user) {
		ResponseEntity<ResponseStructure<User>> db = service.saveUser(user);
		String email=user.getEmail();
		String emailtext="Registration SuccessFull";
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
