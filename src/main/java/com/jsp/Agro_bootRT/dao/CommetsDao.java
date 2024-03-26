package com.jsp.Agro_bootRT.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.xml.stream.events.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Agro_bootRT.entity.Commentss;
import com.jsp.Agro_bootRT.entity.Post;
import com.jsp.Agro_bootRT.entity.User;
import com.jsp.Agro_bootRT.repo.CommentsRepo;
import com.jsp.Agro_bootRT.repo.PostRepo;

@Repository
public class CommetsDao {
	@Autowired
	private CommentsRepo commentsRepo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private PostDao postDao;
//	save
	public Commentss saveComment(Commentss commentss) {
		return commentsRepo.save(commentss);
	}
//	delete
	public Commentss deleteComment(int commentId) {
	    List<Post> posts = postRepo.findAll();
	    for (Post post : posts) {
	        List<Commentss> comments = post.getComments();
	        Iterator<Commentss> iterator = comments.iterator();
	        while (iterator.hasNext()) {
	            Commentss comment = iterator.next();
	            if (comment.getId() == commentId) {
	               iterator.remove();
	                postDao.updatePost(post);
	                comment.setUser(null);
	                commentsRepo.deleteById(commentId);
	                return comment;
	            }
	        }
	    }
	    return null;
	}
	
	public Commentss fetchCommentById(int id) {
		Optional<Commentss> db =commentsRepo.findById(id);
		if(db.isPresent()) {
			return db.get();
		}else
			return null;
	}

		
}
