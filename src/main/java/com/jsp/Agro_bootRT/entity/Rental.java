package com.jsp.Agro_bootRT.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDateTime starttime;
	private LocalDateTime endtime;
	@ManyToOne(cascade = CascadeType.ALL)
	private Equipment equipment;
	@OneToOne(cascade = CascadeType.ALL)
	private TransactionHistory transactionHistory;
}
