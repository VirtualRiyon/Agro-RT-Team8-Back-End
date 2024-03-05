package com.jsp.Agro_bootRT.dao;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Agro_bootRT.entity.Image;
import com.jsp.Agro_bootRT.entity.User;
import com.jsp.Agro_bootRT.repo.ImageRepo;
import com.jsp.Agro_bootRT.repo.UserRepo;

@Repository
public class ImageDao {
	@Autowired
	private ImageRepo imageRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private UserDao userDao;
	
//	**************************************************************************
//	save
	public Image SaveImage(Image image) {
		return imageRepo.save(image);
	}
//*****************************************************************************
//	fetch
	public Image fetchImage(int id)
	{
		Optional<Image> db = imageRepo.findById(id);
		if(db!=null) {
			return db.get();
		}
		else {
			return null;
		}
	}
//	update/edit ****************************************************
	public Image updateImage(int id,MultipartFile file) throws IOException {
		Image image=new Image();
		image.setImg(file.getBytes());
		image.setName(file.getOriginalFilename());
		Optional<Image> db = imageRepo.findById(id);
		Image data = db.get();
		if(db.isPresent()) {
			if(image.getId()==0) {
				image.setId(data.getId());
			}
			if(image.getImg()==null) {
				image.setImg(data.getImg());
			}
			if(image.getName()==null) {
				image.setName(data.getName());
			}
			return imageRepo.save(image);
		}
		else {
			return null;
		}
	}
//	delete ******************************************************************
	public Image deleteImage(int id) {
			Optional<User> db1 = userRepo.findById(id);
			if(db1.isPresent()) {
				User db2 = db1.get();
				Image child = db2.getImage();
				if(child!=null) {
					db2.setImage(null);
					userDao.UpdateUser(db2);
					imageRepo.delete(child);
				}
				return child;
			}
			else {
			return null;
		}
	}
	
	public int id(int id) {
		Optional<Image> db = imageRepo.findById(id);
		if(db.isPresent()) {
			return id;
		}
		return 0;
	}
}
