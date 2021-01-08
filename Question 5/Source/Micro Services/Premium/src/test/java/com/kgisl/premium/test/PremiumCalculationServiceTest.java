package com.kgisl.premium.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kgisl.premium.models.Gender;
import com.kgisl.premium.models.Person;
import com.kgisl.premium.service.PremiumCalculationService;

import junit.framework.Assert;

public class PremiumCalculationServiceTest {
	
	@InjectMocks
	private PremiumCalculationService premiumCalculationService;
	
	/* To test negative cases like exceptions */
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	private Person person;
	
	private double premiumAmount = 0;

	public PremiumCalculationServiceTest(){
		MockitoAnnotations.initMocks(this);
	}

	
    public Person buildPerson() throws FileNotFoundException, IOException, ParseException {
		
		JSONParser parser = new JSONParser();
		Object data = parser.parse(new FileReader("src/test/resources/JsonData/Person.json"));
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(data.toString(), Person.class);
	}
   
    public void setPersonDetails() throws FileNotFoundException, IOException, ParseException {

    	person = buildPerson();
	}


	public void setCalculationInfo() {

		person.setAge(1);
		person.setHabbits(new HashSet<>());
	}


	@SuppressWarnings("deprecation")
	public void testPremiumAmountRightValue() {
		
		BigDecimal premiumAmount = premiumCalculationService.calculatePremium(person);
		Assert.assertNotNull(premiumAmount);
	}


	public void setPersonDetails(Integer age, Gender gender) throws FileNotFoundException, IOException, ParseException {
		
		this.setPersonDetails();
		
		person.setAge(age);
		person.setGender(gender);
	}


	public void testPremiumAmountInvalidValue() {
		
	    exceptionRule.expect(NullPointerException.class);
		//premiumCalculationService.calculatePremium(person);
	}


	public void setPremiumAmount(double premiumAmount) {

		this.premiumAmount = premiumAmount;
	}


	@SuppressWarnings({ "static-access", "deprecation" })
	public void testCalculatorForHabits() {

		double premiumAmountNew = premiumCalculationService.applyHabitsBasedCalculations(person, premiumAmount);
		
		Assert.assertNotNull(premiumAmountNew);
	}


	@SuppressWarnings({ "static-access", "deprecation" })
	public void testCalculatorForHealth() {
		
        double premiumAmountNew = premiumCalculationService.applyHealthConditionBasedCalculations(person, premiumAmount);
		Assert.assertNotNull(premiumAmountNew);
	}


	@SuppressWarnings({ "static-access", "deprecation" })
	public void testCalculatorForGender() {
		
		 double premiumAmountNew = premiumCalculationService.applyGenderBasedCalculations(person, premiumAmount);
		 Assert.assertNotNull(premiumAmountNew);
	}


	@SuppressWarnings({ "static-access", "deprecation" })
	public void testCalculatorForAge() {

		double premiumAmountNew = premiumCalculationService.applyAgeBasedCalculations(person, premiumAmount);
		Assert.assertNotNull(premiumAmountNew);
	}
	
	
}
