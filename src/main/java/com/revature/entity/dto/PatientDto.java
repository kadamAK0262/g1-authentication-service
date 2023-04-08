package com.revature.entity.dto;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
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
public class PatientDto {
	private int patientId;
	@Email(message="email is not valid")
	private String email;
	private String title;
	@NotBlank(message="first name is required")
	private String firstName;
	@NotBlank(message="last name is required")
	private String lastName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "Please provide a date.")
	private String dob;
	private String contactNumber;
	private String password;
	private String gender;

	@NotBlank(message="Address shouldn't be null")
	private String address;
	
}
