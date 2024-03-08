package com.jsp.Agro_bootRT.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Agro_bootRT.entity.Post;
import com.jsp.Agro_bootRT.repo.PostRepo;

@Repository
public class PostDao {
	@Autowired
	private PostRepo postRepo;
	
	public Post savePost(Post post) {
		return postRepo.save(post);
	}
	
	public Post fetchPost(int id) {
		Optional<Post> db = postRepo.findById(id);
		if(db.isPresent()) {
			return db.get();
		}
		else {
			return null;
		}
	}
}
