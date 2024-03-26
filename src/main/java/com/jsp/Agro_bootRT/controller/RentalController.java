package com.jsp.Agro_bootRT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.Agro_bootRT.entity.Rental;
import com.jsp.Agro_bootRT.service.RentalService;
import com.jsp.Agro_bootRT.util.ResponseStructure;

@RestController
public class RentalController {
	@Autowired
	private RentalService rentalService;
	@PostMapping("/saverental")
	public ResponseEntity<ResponseStructure<Rental>> saveRental(@RequestParam int e_id,@RequestBody Rental rental){
		return rentalService.saveRental(e_id, rental);
	}
}
