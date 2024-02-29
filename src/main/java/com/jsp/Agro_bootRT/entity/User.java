package com.jsp.Agro_bootRT.entity;

import com.jsp.Agro_bootRT.enums.UserType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String email;
	private long phone;
	private String password;
	private String gender;
	private int age;
//	enum
	@Enumerated(EnumType.STRING)
	private UserType type;
//	ref
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
}
