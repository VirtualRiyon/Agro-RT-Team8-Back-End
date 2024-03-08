package com.jsp.Agro_bootRT.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.Agro_bootRT.entity.Post;
import com.jsp.Agro_bootRT.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	@Query("select a  from User a where a.post=?1")
	User userfetchByPost(Post post);
	
}
