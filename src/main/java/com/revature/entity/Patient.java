package com.revature.entity;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "patient_authentication")
public class Patient {
	@Column(name  = "patient_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int patientId;
	
	@Column(name="email",nullable=false,unique=true)
	@jakarta.validation.constraints.Email(message="email is not valid")
	private String email;
	
	@Column(name="title")
	private String title;
	
	@Column(name="first_name")
	@NotBlank(message="first name is required")
	private String firstName;
	
	@Column(name="last_name")
	@NotBlank(message="last name is required")
	private String lastName;
	
	@Column(name="dob")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "Please provide a date.")
	private String dob;
	
	@Column(name="contact_number")
	private String contactNumber;
	
	@Column(name="password")
	private String password;
	
	@Column(name="gender")
	private String gender;

	@NotBlank(message="Address shouldn't be null")
	private String address;
	
	
}
