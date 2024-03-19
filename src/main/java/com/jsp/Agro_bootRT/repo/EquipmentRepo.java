package com.jsp.Agro_bootRT.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.Agro_bootRT.entity.Equipment;

public interface EquipmentRepo  extends JpaRepository<Equipment, Integer>{
	
	@Query("select a from  Equipment a  where a.user_id=?1")
	Equipment fetchbyuserid(int user_id);
	
	@Query("select a from Equipment a where a.equipmentname=:equipmentname")
	Equipment fetchByname(String equipmentname);
	
}
