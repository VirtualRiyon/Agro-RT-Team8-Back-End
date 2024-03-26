package com.jsp.Agro_bootRT.dao;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Agro_bootRT.entity.Rental;
import com.jsp.Agro_bootRT.repo.RentalRepo;

@Repository
public class RentalDao {
	@Autowired
	private RentalRepo rentalRepo;
//	save
	public Rental saveRental(Rental rental) {
		return rentalRepo.save(rental);
	}

}
