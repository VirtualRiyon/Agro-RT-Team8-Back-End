package com.jsp.Agro_bootRT.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.Agro_bootRT.dao.CommetsDao;
import com.jsp.Agro_bootRT.dao.PostDao;
import com.jsp.Agro_bootRT.dao.UserDao;
import com.jsp.Agro_bootRT.entity.Commentss;
import com.jsp.Agro_bootRT.entity.Post;
import com.jsp.Agro_bootRT.entity.User;
import com.jsp.Agro_bootRT.exception.UserIdNotFound;
import com.jsp.Agro_bootRT.repo.PostRepo;
import com.jsp.Agro_bootRT.util.ResponseStructure;

@Service
public class CommentsService {
	@Autowired
	private CommetsDao commetsDao;
	@Autowired
	private PostDao postDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private PostRepo postRepo;
	
	public ResponseEntity<ResponseStructure<Commentss>> saveComment (int P_id, int U_id,String comment) {
		Optional<Post> pdb = postRepo.findById(P_id);
		if(pdb!=null) {
			User Udb = userDao.FethcByid(U_id);
			if(Udb!=null) {
				Commentss cm=new Commentss();
				cm.setComment(comment);
				cm.setUser(Udb);
				Commentss comment1 = commetsDao.saveComment(cm);
				Post p = pdb.get();
				List<Commentss> pdbl=new ArrayList<Commentss>();
				pdbl.add(comment1);
				pdbl.addAll(p.getComments());
				p.setComments(pdbl);
				postDao.updatePost(p);
				ResponseStructure<Commentss> r=new ResponseStructure<Commentss>();
				r.setData(comment1);
				r.setMsg("comment posted successfully");
				r.setStatus(HttpStatus.CREATED.value());
				
				return new ResponseEntity<ResponseStructure<Commentss>>(r,HttpStatus.CREATED);
			}
			else {
				throw new UserIdNotFound();
			}
		}
		else {
			throw new UserIdNotFound();
		}
	}
	
//	delete the data
	
	public ResponseEntity<ResponseStructure<Commentss>> deleteComment(int commentId){
		Commentss db = commetsDao.deleteComment(commentId);
		if(db!=null) {
			ResponseStructure<Commentss> r= new ResponseStructure<Commentss>();
			r.setData(db);
			r.setMsg("deleted successfully");
			r.setStatus(HttpStatus.GONE.value());
			return new ResponseEntity<ResponseStructure<Commentss>>(r,HttpStatus.GONE);
		}
		else {
			throw new UserIdNotFound();
		}
	}
  }
 