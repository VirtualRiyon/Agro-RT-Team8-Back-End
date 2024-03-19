package com.jsp.Agro_bootRT.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.Agro_bootRT.entity.Equipment;
import com.jsp.Agro_bootRT.entity.User;

public interface EquipmentRepo  extends JpaRepository<Equipment, Integer>{
	
	@Query("select a from Equipment a where a.equipmentname=:equipmentname")
	Equipment fetchByname(String equipmentname);
	
	@Query("select a from Equipment a where a.user=?1")
	List<Equipment> findByUser(User user);
	
}
