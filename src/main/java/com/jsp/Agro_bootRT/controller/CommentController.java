package com.jsp.Agro_bootRT.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.Agro_bootRT.entity.Commentss;
import com.jsp.Agro_bootRT.service.CommentsService;
import com.jsp.Agro_bootRT.util.ResponseStructure;

@RestController
public class CommentController {
	@Autowired
	private CommentsService commentsService;
	
	@PostMapping("/savecomment")
	public ResponseEntity<ResponseStructure<Commentss>> saveComment(@RequestParam int P_id,@RequestParam int U_id,@RequestParam String comment) {
		return commentsService.saveComment(P_id, U_id, comment);
	}
	@DeleteMapping("/deletecomm")
	public ResponseEntity<ResponseStructure<Commentss>> deleteComment(@RequestParam int commentId){
		return commentsService.deleteComment(commentId);
	}
}
