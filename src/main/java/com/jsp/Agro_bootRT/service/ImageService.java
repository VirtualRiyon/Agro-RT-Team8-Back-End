package com.jsp.Agro_bootRT.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Agro_bootRT.dao.ImageDao;
import com.jsp.Agro_bootRT.dao.UserDao;
import com.jsp.Agro_bootRT.entity.Image;
import com.jsp.Agro_bootRT.entity.User;
import com.jsp.Agro_bootRT.exception.UserIdNotFound;
import com.jsp.Agro_bootRT.util.ResponseStructure;

@Service
public class ImageService {
	@Autowired
	private ImageDao imageDao;
	@Autowired
	private UserDao userDao;
	
//saveimg***************************************	
	public ResponseEntity<ResponseStructure<Image>> saveImage(int Uid,String name,MultipartFile img) throws IOException{
		User db = userDao.FethcByid(Uid);
		if(db!=null) {
			Image image=new Image();
			image.setName(name);
			image.setImg(img.getBytes());
			Image db1 = imageDao.SaveImage(image);
			db.setImage(db1);
			userDao.UpdateUser(db);
			ResponseStructure<Image> m= new ResponseStructure<Image>();
			m.setData(db1);
			m.setMsg("Profile Pic Uploaded Successfully");
			m.setStatus(HttpStatus.CREATED.value());
			 
			return new ResponseEntity<ResponseStructure<Image>>(m,HttpStatus.CREATED);
		}
		else {
			throw new UserIdNotFound();
		}
	}

//fetch*********************
	public ResponseEntity<byte[]> fetchImgbyid(int id){
		Image db = imageDao.fetchImage(id);
		if(db!=null) {
			byte[] imageBytes = imageDao.fetchImage(id).getImg();
	        // Set appropriate content type (e.g., image/jpeg)
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.IMAGE_JPEG);
	        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
		}
		else {
			throw new UserIdNotFound();
		}
	}
//update*****************************************************************
	public ResponseEntity<ResponseStructure<Image>> UpdateImage(int id,MultipartFile file ) throws IOException{
		int id1 = imageDao.id(id);
		if(id1==id) {
			Image db = imageDao.updateImage(id, file);
			ResponseStructure<Image> m= new ResponseStructure<Image>();
			if(db!=null) {
				m.setMsg("image updated successfully");
				m.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<Image>>(m,HttpStatus.OK);
			}
		}
			throw new UserIdNotFound();
	}
//delete**************************************************************
	public ResponseEntity<ResponseStructure<Image>> deleteImg(int id){
		Image db1 = imageDao.fetchImage(id);
		if(db1!=null) {
			Image db = imageDao.deleteImage(id);
			ResponseStructure<Image> m= new ResponseStructure<Image>();
			m.setData(db);
			m.setMsg("deleted Successfully");
			m.setStatus(HttpStatus.GONE.value());
			return new ResponseEntity<ResponseStructure<Image>>(m,HttpStatus.GONE);
		}
			throw new UserIdNotFound();
	}
}
