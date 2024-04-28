package com.jsp.Agro_bootRT.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.Agro_bootRT.dao.UserDao;
import com.jsp.Agro_bootRT.entity.User;
import com.jsp.Agro_bootRT.exception.UserIdNotFound;
import com.jsp.Agro_bootRT.exception.UserPwdMissMatch;
import com.jsp.Agro_bootRT.exception.UseremailNotExist;
import com.jsp.Agro_bootRT.util.ResponseStructure;

import jakarta.persistence.criteria.CriteriaBuilder.In;

@Service
public class UserService {
	@Autowired
	private UserDao dao;

	@Autowired
	private JavaMailSender javaMailSender;

	public String sendSimpleMail(String email, String msg, String sub) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("agrotechteam8@gmail.com");
		mailMessage.setTo(email);
		mailMessage.setText(msg);
		mailMessage.setSubject(sub);
		javaMailSender.send(mailMessage);
		return "mail sent successfully";
	}

//save	
	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		User db = dao.saveUser(user);
		ResponseStructure<User> s = new ResponseStructure<User>();
		s.setData(db);
		s.setMsg("User Saved Successfully..");
		s.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<User>>(s, HttpStatus.CREATED);
	}

//fetch
	public ResponseEntity<ResponseStructure<User>> UserfetchByid(int id) {
		User db = dao.FethcByid(id);
		if (db != null) {
			ResponseStructure<User> m = new ResponseStructure<User>();
			m.setData(db);
			m.setMsg("Found successfully");
			m.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(m, HttpStatus.FOUND);
		} else {
			throw new UserIdNotFound();
		}
	}

//update
	public ResponseEntity<ResponseStructure<User>> UpdateUser(User user) {
		User db = dao.UpdateUser(user);
		if (db != null) {
			ResponseStructure<User> m = new ResponseStructure<User>();
			m.setData(db);
			m.setMsg("Updated Successfully..");
			m.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<User>>(m, HttpStatus.CREATED);
		} else {
			throw new UserIdNotFound();
		}
	}

//	delete
	public ResponseEntity<ResponseStructure<User>> deleteStudent(int id) {
		User db = dao.DeletedByid(id);
		if (db != null) {
			ResponseStructure<User> m = new ResponseStructure<User>();
			m.setData(db);
			m.setMsg("Deleted Successfully..");
			m.setStatus(HttpStatus.GONE.value());
			return new ResponseEntity<ResponseStructure<User>>(m, HttpStatus.GONE);
		} else {
			throw new UserIdNotFound();
		}
	}

//login
	public ResponseEntity<ResponseStructure<User>> login(String email, String password) {
		String login = dao.fetchByEmail(email, password);
		if (login == "true") {
			ResponseStructure<User> m = new ResponseStructure<User>();
			m.setMsg("Login Successfull");
			m.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(m, HttpStatus.OK);
		} else if (login == null) {
			throw new UseremailNotExist(email + "Email Not Exist");
		} else {
			throw new UserPwdMissMatch("password is incorrect");
		}
	}

//fetchAll
	public ResponseEntity<ResponseStructure<List<User>>> UserfetchAll() {
		List<User> db = dao.fetchAll();
		ResponseStructure<List<User>> m = new ResponseStructure<List<User>>();
		m.setData(db);
		m.setMsg("Fetched SuccessFull");
		m.setStatus(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<List<User>>>(m, HttpStatus.FOUND);
	}

//Send OTP
	public ResponseEntity<ResponseStructure<Integer>> SendOtp(String email) {
		boolean db = dao.sendOtp(email);
		if (db == true) {
			Random random = new Random();
			int value = random.nextInt(999999);
			String email1 = email;
			String emailtext = "YOUR ONE TIME PASSWORD IS \n \n "+"("+value+")";
			sendSimpleMail(email1, emailtext, "Agro OTP Details");
			ResponseStructure<Integer> m = new ResponseStructure<Integer>();
			m.setData(value);
			m.setMsg("OTP Sent Successfully");
			m.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Integer>>(m, HttpStatus.OK);
		} else {
			throw new UseremailNotExist();
		}
	}
}
