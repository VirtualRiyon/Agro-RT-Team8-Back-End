package com.jsp.Agro_bootRT.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.Agro_bootRT.entity.Image;
import com.jsp.Agro_bootRT.entity.User;

public interface ImageRepo  extends JpaRepository<Image, Integer>{

	@Query("select a  from User a where a.image=?1")
	User fetchByImage(Image image);

}
