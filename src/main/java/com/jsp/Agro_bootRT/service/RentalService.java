package com.jsp.Agro_bootRT.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.Agro_bootRT.dao.EuipmentDao;
import com.jsp.Agro_bootRT.dao.RentalDao;
import com.jsp.Agro_bootRT.entity.Equipment;
import com.jsp.Agro_bootRT.entity.Rental;
import com.jsp.Agro_bootRT.entity.TransactionHistory;
import com.jsp.Agro_bootRT.exception.DateExceptionn1;
import com.jsp.Agro_bootRT.exception.UserIdNotFound;
import com.jsp.Agro_bootRT.util.ResponseStructure;

import ch.qos.logback.core.util.Duration;

@Service
public class RentalService {
	@Autowired
	private RentalDao rentalDao;
	@Autowired
	private EuipmentDao equipmentDao;
	
	public ResponseEntity<ResponseStructure<Rental>> saveRental(int e_id,Rental rental){
		Equipment db = equipmentDao.fetchByIdEqp(e_id);
		if(db!=null) {
			rental.setEquipment(db);
			TransactionHistory pay=new TransactionHistory();
		    int startTime = rental.getStarttime().getHour();
		    int endTime = rental.getEndtime().getHour();
		    java.time.Duration time = java.time.Duration.between(rental.getStarttime(), rental.getEndtime());
//		    System.out.println(time.toHours());
		    if(time.toHours()>=0 ) {
				long hours = time.toHours();
				int min = time.toMinutesPart();
//				System.out.println(hours);
				hours=hours*60;
				int price = db.getCph();
				long minute = (min+hours);
//				System.out.println(min+hours);
				double amount = (price/60)*minute;
				pay.setAmount(amount);
				rental.setTransactionHistory(pay);
				
		    }		
		    else {
		    	throw new  DateExceptionn1();
		    }
		    Rental db1 = rentalDao.saveRental(rental);
		    ResponseStructure<Rental> m=new ResponseStructure<Rental>();
			m.setData(db1);
			m.setMsg("rental Saved successfully");
			m.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Rental>>(m,HttpStatus.CREATED);
		}
		else {
			throw new UserIdNotFound();
		}
	}
	
}
