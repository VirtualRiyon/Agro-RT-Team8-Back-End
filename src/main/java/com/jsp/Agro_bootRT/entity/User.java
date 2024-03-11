package com.jsp.Agro_bootRT.entity;

import java.util.List;

import com.jsp.Agro_bootRT.enums.UserType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	  @NotBlank(message="you must write a First Name")
	  @NotNull(message = "First name is mandatory")
	  private String firstName;
	  
	  @NotBlank(message="you must write a Last Name")
	  @NotNull(message = "Last name is mandatory")
	  private String lastName;
	@Column(unique = true)
	private String email;
	private long phone; 
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message = "Password must be at least 8 characters long and contain at least one digit, one lowercase letter, one uppercase letter, one special character, and no whitespace")
	private String password;
	private String gender;
	private int age;
//	enum
	@Enumerated(EnumType.STRING)
	private UserType type;
//	ref
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Image image;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Post> post;
}
