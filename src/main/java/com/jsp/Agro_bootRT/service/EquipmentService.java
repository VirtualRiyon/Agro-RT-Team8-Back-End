package com.jsp.Agro_bootRT.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.Agro_bootRT.dao.EuipmentDao;
import com.jsp.Agro_bootRT.dao.UserDao;
import com.jsp.Agro_bootRT.entity.Equipment;
import com.jsp.Agro_bootRT.entity.User;
import com.jsp.Agro_bootRT.exception.EquipmentNameNotFound;
import com.jsp.Agro_bootRT.exception.UserIdNotFound;
import com.jsp.Agro_bootRT.util.ResponseStructure;

@Service
public class EquipmentService {
	@Autowired
	private EuipmentDao dao;
	@Autowired
	private UserDao userDao;
//	save
	public ResponseEntity<ResponseStructure<Equipment>> saveEquipment(int u_id,Equipment equipment) {
		User db = userDao.FethcByid(u_id);
		if(db!=null) {
			equipment.setUser(db);
			dao.saveEquipment(equipment);
			ResponseStructure<Equipment> m=new ResponseStructure<Equipment>();
			m.setData(equipment);
			m.setMsg("equipment saved successfully");
			m.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Equipment>>(m,HttpStatus.CREATED);
		}
		else {
			throw new UserIdNotFound();
		}
	}
//	update 
	public ResponseEntity<ResponseStructure<Equipment>> updateEquipment(Equipment equipment){
		Equipment db = dao.updateEquipment(equipment);
		if(db!=null) {
			ResponseStructure<Equipment> m=new ResponseStructure<Equipment>();
			m.setData(db);
			m.setMsg("updated successfully");
			m.setStatus(HttpStatus.CREATED.value());
			 return new ResponseEntity<ResponseStructure<Equipment>>(m,HttpStatus.CREATED);
		}
		else {
			throw new UserIdNotFound();
		}
	}
//	delete
	public ResponseEntity<ResponseStructure<Equipment>> deleteEquipment(int id){
		Equipment db = dao.deleteEquipment(id);
		if(db!=null) {
			ResponseStructure<Equipment> m=new ResponseStructure<Equipment>();
			m.setData(db);
			m.setMsg("deleted successfully");
			m.setStatus(HttpStatus.GONE.value());
			
			return new ResponseEntity<ResponseStructure<Equipment>>(m,HttpStatus.GONE);
		}
		else {
			throw new UserIdNotFound();
		}
	}
//	findbyid
	public ResponseEntity<ResponseStructure<Equipment>> fetchByIdEqp(int id){
		Equipment db = dao.fetchByIdEqp(id);
		if(db!=null) {
			ResponseStructure<Equipment> m=new ResponseStructure<Equipment>();
			m.setData(db);
			m.setMsg("fetched successfully");
			m.setStatus(HttpStatus.FOUND.value());
			 return new ResponseEntity<ResponseStructure<Equipment>>(m,HttpStatus.FOUND);
		}
		else {
			return null;
		}
	}
//	fethall
	public ResponseEntity<ResponseStructure<Equipment>> fetchAllEqp(){
		List<Equipment> db = dao.fetchAllEqp();
		if(db!=null) {
			ResponseStructure<Equipment> m=new ResponseStructure<Equipment>();
			m.setListdata(db);
			m.setMsg("all equipments fetched Successfully");
			m.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<Equipment>>(m,HttpStatus.FOUND);
		}
		else {
			throw new UserIdNotFound();
		}
	}
//fetchByname
	public ResponseEntity<ResponseStructure<Equipment>> fetchByName(String equipmentname){
		Equipment db = dao.fetchByName(equipmentname);
		if(db!=null) {
			ResponseStructure<Equipment> m=new ResponseStructure<>();
			m.setData(db);
			m.setMsg("fetched Equipments Based on name");
			m.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<Equipment>>(m,HttpStatus.FOUND);
		}
		else {
			throw new EquipmentNameNotFound();
		}
	}
//	fetchByuserId
	public ResponseEntity<ResponseStructure<List<Equipment>>> fetcByUserId(int user_id){
		User db = userDao.FethcByid(user_id);
		if(db!=null) {
			List<Equipment> db1 = dao.fetchByUserId(db);
			ResponseStructure<List<Equipment>> m=new ResponseStructure<>();
			m.setData(db1);
			m.setMsg("data feteched successfully based on user_id");
			m.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<List<Equipment>>>(m,HttpStatus.FOUND);
		}
		else {
			throw new UserIdNotFound();
		}
	}
}
