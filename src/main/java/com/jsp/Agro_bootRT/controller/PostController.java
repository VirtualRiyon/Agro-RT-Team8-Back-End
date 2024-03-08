package com.jsp.Agro_bootRT.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Agro_bootRT.entity.Post;
import com.jsp.Agro_bootRT.service.PostService;
import com.jsp.Agro_bootRT.util.ResponseStructure;

@RestController
public class PostController {
	@Autowired
	private PostService postService;
	@PostMapping("/savepost")
	public ResponseEntity<ResponseStructure<Post>> savePost(@RequestParam int uid,@RequestParam MultipartFile file,@RequestParam String caption,@RequestParam String location) throws IOException{
		return postService.savePost(uid, file, caption,location);
	}
	
	@GetMapping("/fetchpost")
	public ResponseEntity<ResponseStructure<Post>> fetchPost(@RequestParam int id){
		return postService.fetchPost(id);
	}
	@DeleteMapping("/deletepost")
	public ResponseEntity<ResponseStructure<Post>> deletePost(@RequestParam int id){
		return postService.deletePost(id);
	}
}
