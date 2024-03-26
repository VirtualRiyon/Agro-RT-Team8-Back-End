package com.jsp.Agro_bootRT.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Agro_bootRT.entity.Equipment;
import com.jsp.Agro_bootRT.entity.Post;
import com.jsp.Agro_bootRT.entity.User;
import com.jsp.Agro_bootRT.repo.EquipmentRepo;

import jakarta.validation.constraints.AssertFalse.List;

@Repository
public class EuipmentDao {
	@Autowired
	private EquipmentRepo equipmentrepo;
//	save
	public Equipment saveEquipment(Equipment equipment) {
		return equipmentrepo.save(equipment);
	}
//	update
	public Equipment updateEquipment(Equipment equipment){
		Optional<Equipment> db = equipmentrepo.findById(equipment.getId()); 
		if(db.isPresent()) {
			Equipment data = db.get();
			if(equipment.getEquipmentname()==null) {
				equipment.setEquipmentname(data.getEquipmentname());
			}
			if(equipment.getQuantity()==0) {
				equipment.setQuantity(data.getQuantity());;
			}
			if(equipment.getCph()==0) {
				equipment.setCph(data.getCph());;
			}
			return equipmentrepo.save(equipment);
		}
		else {
			return null;
		}
	}
//	delete
	public Equipment deleteEquipment(int id) {
		Optional<Equipment> db = equipmentrepo.findById(id);
		if(db.isPresent()) {
			equipmentrepo.deleteById(id);
			return db.get();
		}
		else {
			return null;
		}
	}
//	fetchByid
	public Equipment fetchByIdEqp(int id) {
		Optional<Equipment> db = equipmentrepo.findById(id);
		if(db!=null) {
			return db.get();
		}
		else {
			return null;
		}
	}
//	fetchAll
	public java.util.List<Equipment> fetchAllEqp(){
		return equipmentrepo.findAll();
	}
//	fetchByname
	public java.util.List<Equipment> fetchByName(String equipmentname) {
		java.util.List<Equipment> db = equipmentrepo.fetchByname(equipmentname);
		if(db.isEmpty()) {
			return null;
		}
		else {
			return db;
		}
	}
//fetchByUserId
	public java.util.List<Equipment> fetchByUserId(User user) {
		java.util.List<Equipment> db = equipmentrepo.findByUser(user);
		if(db.isEmpty()) {
			return null;
		}
		else {
			return db;
		}
	}
	
}
