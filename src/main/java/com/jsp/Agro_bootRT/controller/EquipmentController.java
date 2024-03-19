package com.jsp.Agro_bootRT.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.jsp.Agro_bootRT.entity.Equipment;
import com.jsp.Agro_bootRT.entity.User;
import com.jsp.Agro_bootRT.service.EquipmentService;
import com.jsp.Agro_bootRT.util.ResponseStructure;

@RestController
public class EquipmentController {
	@Autowired
	private EquipmentService  equipmentService;
	@PostMapping("/saveEqp")
	public ResponseEntity<ResponseStructure<Equipment>> saveEquipment(@RequestParam int u_id, @RequestBody Equipment equipment){
		return equipmentService.saveEquipment(u_id, equipment);
	}
	
	@PutMapping("/updateEqp")
	public ResponseEntity<ResponseStructure<Equipment>> updateEquipment(@RequestBody Equipment equipment){
		return equipmentService.updateEquipment(equipment);
	}
	@DeleteMapping("/deleteEqp")
	public ResponseEntity<ResponseStructure<Equipment>> deleteEquipment(@RequestParam int id){
		return equipmentService.deleteEquipment(id);
	}
	@GetMapping("/fetchByIdEqp")
	public ResponseEntity<ResponseStructure<Equipment>> fetchByIdEqp(@RequestParam int id){
		return equipmentService.fetchByIdEqp(id);
	}
	
	@GetMapping("/fetchAllEqp")
	public ResponseEntity<ResponseStructure<Equipment>> fetchAllEqp(){
		return equipmentService.fetchAllEqp();
	}
	@GetMapping("/fetchByNameEqp")
	public ResponseEntity<ResponseStructure<Equipment>> fetchByName(@RequestParam String equipmentname){
		return equipmentService.fetchByName(equipmentname);
	}
	@GetMapping("/fetchByuserId")
	public ResponseEntity<ResponseStructure<List<Equipment>>> fetchByUserId(@RequestParam int user_id){
		return equipmentService.fetcByUserId(user_id);
	}
	
}
