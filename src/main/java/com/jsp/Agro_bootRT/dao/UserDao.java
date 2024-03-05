package com.jsp.Agro_bootRT.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Repository;

import com.jsp.Agro_bootRT.entity.User;
import com.jsp.Agro_bootRT.repo.UserRepo;
@Repository
public class UserDao {
	@Autowired
	private UserRepo repo;
//	save
	public User saveUser(User user){
		return repo.save(user);
	}
//	fetch
	public User FethcByid( int id) {
		Optional<User> db = repo.findById(id);
		if(db.isPresent()) {
			return db.get();
		}
		else
			return null;
	}
//fetchAll
	public List<User> fetchAll() {
		return repo.findAll();
	}
//	delete
	public User DeletedByid( int id) {
		Optional<User> db = repo.findById(id);
		if(db.isPresent()) {
			repo.deleteById(id);
			return db.get();
		}
		else {
			return null;
		}
	}
//update
	public User UpdateUser(User user) {
		Optional<User> db = repo.findById(user.getId());
		User m = db.get();
		if(db.isPresent()) {
			if((user).getFirstName()==null) {
				user.setFirstName(m.getFirstName());
			}
			if(user.getLastName()==null) {
				user.setLastName(m.getLastName());
			}
			if(user.getEmail()==null) {
				user.setEmail(m.getEmail());
			}
			if(user.getPassword()==null) {
				user.setPassword(m.getPassword());
			}
			if(user.getPhone()==0) {
				user.setPhone(m.getPhone());
			}
			if(user.getAddress()==null) {
				user.setAddress(m.getAddress());
			}
			if(user.getGender()==null) {
				user.setGender(m.getGender());
			}
			return repo.save(user);
		}
		else {
			return null;
	
		}
	}
//login
	public String fetchByEmail(String email, String password) {
	     User userEmail = repo.fetchEmail(email);
	     System.out.println(userEmail);
	     if(userEmail!=null) {
	        if(userEmail.getPassword().equals(password)) {
	          return "true";
	        }
	        else {
	          return "false";
	        }
	     }
	    return null;
	  }
	
//send OTP
	public boolean sendOtp(String email) {
		User db = repo.fetchEmail(email);
		if(db!=null) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
