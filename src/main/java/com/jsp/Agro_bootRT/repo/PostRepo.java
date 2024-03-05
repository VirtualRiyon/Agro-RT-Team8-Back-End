package com.jsp.Agro_bootRT.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.Agro_bootRT.entity.Post;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
}
