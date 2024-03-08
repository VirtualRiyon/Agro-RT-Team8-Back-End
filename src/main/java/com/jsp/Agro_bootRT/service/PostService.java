package com.jsp.Agro_bootRT.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Agro_bootRT.dao.PostDao;
import com.jsp.Agro_bootRT.dao.UserDao;
import com.jsp.Agro_bootRT.entity.Image;
import com.jsp.Agro_bootRT.entity.Post;
import com.jsp.Agro_bootRT.entity.User;
import com.jsp.Agro_bootRT.exception.UserIdNotFound;
import com.jsp.Agro_bootRT.util.ResponseStructure;

@Service
public class PostService {
	@Autowired
	private PostDao postDao;
	@Autowired
	private UserDao userDao;
	
	public ResponseEntity<ResponseStructure<Post>> savePost(int uid,MultipartFile file, String caption,String location) throws IOException{
		User db = userDao.FethcByid(uid);
		if(db!=null) {
			Image image=new Image();	
			image.setName(file.getOriginalFilename());
			image.setImg(file.getBytes());
			Post post=new Post();
			post.setImage(image);
			post.setCaption(caption);
			post.setLocation(location);
			post.setDatetime(LocalDateTime.now());
			Post post1 = postDao.savePost(post);
			List<Post> postlist=new ArrayList<Post>();;
			postlist.add(post1);
			postlist.addAll(db.getPost());
			db.setPost(postlist);
			userDao.UpdateUser(db);
			ResponseStructure<Post> m= new ResponseStructure<Post>();
			m.setData(post1);
			m.setMsg("Post Uploaded Successfully");
			m.setStatus(HttpStatus.CREATED.value());
			 
			return new ResponseEntity<ResponseStructure<Post>>(m,HttpStatus.CREATED);
		}
		else {
			throw new UserIdNotFound();
		}
	}
	
	public ResponseEntity<ResponseStructure<Post>> fetchPost(int id){
	     Post db = postDao.fetchPost(id);
	     if(db!=null) {
	    	 ResponseStructure<Post> p=new ResponseStructure<Post>();
	    	 p.setData(db);
	    	 p.setMsg("Data fetched successfully");
	    	 p.setStatus(HttpStatus.FOUND.value());
	    	  return new ResponseEntity<ResponseStructure<Post>>(p,HttpStatus.FOUND);
	     }
	     else {
	    	 throw new UserIdNotFound();
	     }
	}
}
