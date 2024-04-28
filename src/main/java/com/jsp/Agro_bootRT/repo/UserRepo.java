package com.jsp.Agro_bootRT.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.Agro_bootRT.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	@Query("select a from User a where a.email=?1")
	public abstract User fetchEmail(String email);
	
	
	
}
