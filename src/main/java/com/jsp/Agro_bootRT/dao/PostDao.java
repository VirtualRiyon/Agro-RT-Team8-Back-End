package com.jsp.Agro_bootRT.dao;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Agro_bootRT.entity.Image;
import com.jsp.Agro_bootRT.entity.Post;
import com.jsp.Agro_bootRT.entity.User;
import com.jsp.Agro_bootRT.repo.ImageRepo;
import com.jsp.Agro_bootRT.repo.PostRepo;
import com.jsp.Agro_bootRT.repo.UserRepo;

@Repository
public class PostDao {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ImageDao imageDao;
	@Autowired
	private UserRepo userRepo;
//	save
	public Post savePost(Post post) {
		return postRepo.save(post);
	}
//	fetch
	public List<Post> fetchPost(int id) {
		Optional<User> db = userRepo.findById(id);
		if(db!=null) {
			return db.get().getPost();
		}
		else {
			return null;
		}
	}
	
//delete post
	public Post deletePost(int id) {
		 List<User> users = userRepo.findAll();
	        for (User user : users) {
	            List<Post> posts = user.getPost();
	            Iterator<Post> iterator = posts.iterator();
	            while (iterator.hasNext()) {
	                Post post = iterator.next();
	                if (post.getId() == id) {
	                    iterator.remove();
	                    userDao.UpdateUser(user);
	                    postRepo.deleteById(id);
	                    imageDao.deleteImage(id);
	                    return post;
	                }
	            }
	        }
            return null;
		
	}
	
//update
	public Post updatePost(Post post){
		Optional<Post> db = postRepo.findById(post.getId()); 
		if(db.isPresent()) {
			Post data = db.get();
			if(post.getComments()==null) {
				post.setComments(data.getComments());
			}
			if(post.getCaption()==null) {
				post.setCaption(data.getCaption());
			}
			if(post.getLocation()==null) {
				post.setLocation(data.getLocation());
			}
			if(post.getDatetime()==null) {
				post.setDatetime(data.getDatetime());
			}
			return postRepo.save(data);
		}
		else {
			return null;
		}
	}

}
