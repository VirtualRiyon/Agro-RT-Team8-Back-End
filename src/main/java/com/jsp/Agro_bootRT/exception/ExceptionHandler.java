package com.jsp.Agro_bootRT.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.jsp.Agro_bootRT.util.ResponseStructure;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

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
	@org.springframework.web.bind.annotation.ExceptionHandler(EquipmentNameNotFound.class)
	public ResponseEntity<ResponseStructure<String>>EquipmentNameHandler(EquipmentNameNotFound eqx){
		ResponseStructure<String> m= new ResponseStructure<String>();
		m.setData("Equipment name Not Found");
		m.setStatus(HttpStatus.NOT_FOUND.value());
		m.setMsg(eqx.getMsg());
		return new ResponseEntity<ResponseStructure<String>>(m,HttpStatus.NOT_FOUND);
	}
	
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	    org.springframework.http.HttpHeaders headers, HttpStatusCode status, WebRequest request) {
	     java.util.List<ObjectError> error = ex.getAllErrors();
	    Map<String, String> map = new HashMap<String, String>();
	    ResponseStructure<Object> structure = new ResponseStructure<>();

	    for (ObjectError objectError : error) {
	      String filedName = ((FieldError) objectError).getField();
	      String message = ((FieldError) objectError).getDefaultMessage();
	      map.put(filedName, message);

	    }
	    structure.setMsg("provide valid details");
	    structure.setStatus(HttpStatus.NOT_FOUND.value());
	    structure.setData(map);

	    return new ResponseEntity<Object>(structure, HttpStatus.BAD_REQUEST);
	  }
	
	
	  @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
	  public ResponseEntity<ResponseStructure<Object>> handleConstraintViolationException(ConstraintViolationException ex) {
	    ResponseStructure<Object> structure = new ResponseStructure();
	    Map<String, String> map = new HashMap<String, String>();

	    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
	      String field = violation.getPropertyPath().toString();
	      String message = violation.getMessage();
	      map.put(field, message);

	    }
	    structure.setMsg("provide proper details");
	    structure.setStatus(HttpStatus.NOT_FOUND.value());
	    structure.setData(map);

	    return new ResponseEntity<ResponseStructure<Object>>(structure, HttpStatus.BAD_REQUEST);

	  }

}
