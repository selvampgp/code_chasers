package com.kgisl.premium.models;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
	
	    private Integer id; 
	
		private String firstName;
		
		private String lastName;
		
		private String emailId;
		
		public String phoneNumber;
		
		private Gender gender;
		
		private Integer age;
		
		private String dateOfBirth;
		
		private Set<HealthIssues> healthIssues;
		
		private Set<Habbits> habbits;
		
		private String cardNumber;
		
		private String cvv;
		
		public String expiryMonth;
		
		public String expiryYear;

	}


