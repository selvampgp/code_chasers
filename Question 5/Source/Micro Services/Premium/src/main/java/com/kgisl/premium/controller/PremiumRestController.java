package com.kgisl.premium.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.kgisl.premium.models.Insurance;
import com.kgisl.premium.models.Person;
import com.kgisl.premium.service.InsuranceService;
import com.kgisl.premium.service.PremiumCalculationService;

@RestController
public class PremiumRestController {

	/* Rest template to communicate with other services */
	@Autowired
	RestTemplate restTemplate;
	
	/* Insurance service */
	@Autowired
	InsuranceService insuranceService;
	
	/* Premium calculation service */
	@Autowired
	PremiumCalculationService premiumCalculationService;
	
	/**
	 * Used to calculate premium amount based on person age, habits, health and gender.
	 * 
	 * @param person - person object person details like name, email id, phone number.
	 * @return calculated premium amount 
	 * @throws ParseException(Date parsing exception)
	 */
	@PostMapping(value = { "/insurance/premium" })
	public BigDecimal getPremiumAmount(@RequestBody Person person) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date date = formatter.parse(person.getDateOfBirth());
		// Converting obtained Date object to LocalDate object
		Instant instant = date.toInstant();
		ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
		LocalDate givenDate = zone.toLocalDate();
		// Calculating the difference between given date to current date.
		Period period = Period.between(givenDate, LocalDate.now());
		person.setAge(period.getYears());

		return premiumCalculationService.calculatePremium(person);
	}
	
	/**
	 * Used to create insurance policy.
	 * 
	 * It will calculate premium amount based on person age, habits, health and gender.
	 * 
	 * Using payment service, system will create insurance policy for the given user.
	 * 
	 * @param person - person object person details like name, email id, phone number.
	 * @return policy creation status
	 * @throws ParseException(Date parsing exception)
	 */
	@PostMapping(value = { "/insurance/policy" })
	public ResponseEntity<Boolean> createPolicyWithPremimum(@RequestBody Person person) throws ParseException{
		
		BigDecimal premiumAmount = getPremiumAmount(person);
		
		Insurance insurance = new Insurance();
		
		insurance.setInsEmailId(person.getEmailId());
		if(Objects.nonNull(person.getGender()))
	         insurance.setInsGender(person.getGender().name());
		insurance.setInsHolderFirstName(person.getFirstName());
		insurance.setInsHolderLastName(person.getLastName());
		insurance.setInsPhoneNumber(person.getPhoneNumber());
		
		insurance = insuranceService.createInsuranceWithPremium(insurance);
		
		Boolean response = restTemplate.postForObject("http://Payment:8014//premium/" + insurance.getInsId(), premiumAmount, Boolean.class);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
