package com.jsp.Agro_bootRT.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.Agro_bootRT.util.ResponseStructure;

@RestControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(UserIdNotFound.class)
	public ResponseEntity<ResponseStructure<String>> UserIdHandler (UserIdNotFound ex){
		ResponseStructure<String> m= new ResponseStructure<String>();
		m.setData("Data Not Found");
		m.setMsg(ex.getMsg());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(UseremailNotExist.class)
	public ResponseEntity<ResponseStructure<String>> UseremailHandler(UseremailNotExist ex){
		ResponseStructure<String> m= new ResponseStructure<String>();
		m.setData("Email Not Exist");
		m.setMsg(ex.getMsg());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(UserPwdMissMatch.class)
	public ResponseEntity<ResponseStructure<String>> UserpasslHandler(UserPwdMissMatch ex){
		ResponseStructure<String> m= new ResponseStructure<String>();
		m.setData("Password is Miss Matching.. Check It Once");
		m.setMsg(ex.getMsg());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m,HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<ResponseStructure<String>> SqlException(SqlException ex){
		ResponseStructure<String> m= new ResponseStructure<String>();
		m.setData("Email Is Already Exist.. Please Use Another Email");
		m.setMsg(ex.getMsg());
		m.setStatus(HttpStatus.ALREADY_REPORTED.value());
		return new ResponseEntity<ResponseStructure<String>>(m,HttpStatus.ALREADY_REPORTED);
		
		
	}
}
