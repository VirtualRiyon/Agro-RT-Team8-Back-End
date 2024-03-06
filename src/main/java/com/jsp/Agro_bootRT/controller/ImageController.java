package com.jsp.Agro_bootRT.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Agro_bootRT.dao.ImageDao;
import com.jsp.Agro_bootRT.entity.Image;
import com.jsp.Agro_bootRT.service.ImageService;
import com.jsp.Agro_bootRT.util.ResponseStructure;

@RestController
public class ImageController {
	
	@Autowired
	private ImageService imageService;
	@Autowired
	private ImageDao dao;
	
	
	@PostMapping("/saveimg")
	public ResponseEntity<ResponseStructure<Image>> saveImge(@RequestParam int Uid,@RequestParam String name,@RequestParam MultipartFile img) throws IOException{
		return imageService.saveImage(Uid, name, img);
	}
	
	@GetMapping("/fetchimg")
    public ResponseEntity<byte[]> getImage(@RequestParam int id) {
        return imageService.fetchImgbyid(id);
    }
    
	@PutMapping("/updateimg")
	public ResponseEntity<ResponseStructure<Image>> updateImg(@RequestParam int id, @RequestParam MultipartFile file) throws IOException{
		return imageService.UpdateImage(id,file);
	}
	
	@DeleteMapping("/deleteimg")
	public ResponseEntity<ResponseStructure<Image>> deleteImg(@RequestParam int id){
		return imageService.deleteImg(id);
	}
}
