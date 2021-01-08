package com.kgisl.premium.behaviours;

import com.kgisl.premium.models.Gender;
import com.kgisl.premium.test.PremiumCalculationServiceTest;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PremiumCalculationServiceBehaviour {
	
    private PremiumCalculationServiceTest premiumCalculationServiceTest;
	
	@Before
	public void intializeService(){
		
		premiumCalculationServiceTest = new PremiumCalculationServiceTest();
	}

	@Given("^a person details$")
	public void a_person_details() throws Throwable {

		premiumCalculationServiceTest.setPersonDetails();
	}

	@When("^we need to calculate premium amount for a person$")
	public void we_need_to_calculate_premium_amount_for_a_person() throws Throwable {
	   
		premiumCalculationServiceTest.setCalculationInfo();
	}

	@Then("^it should return the calculated premium amount$")
	public void it_should_return_the_calculated_premium_amount() throws Throwable {
	    
		premiumCalculationServiceTest.testPremiumAmountRightValue();
	}
	
	@When("^we need to calculate premium amount for a person with (.*) and (.*)$")
	public void we_need_to_calculate_premium_amount_for_a_person_with_and_MALE(Integer age, Gender gender) throws Throwable {
		
		
		premiumCalculationServiceTest.setPersonDetails(age, gender);
	}

	@Then("^it should thrown an exception on calculating premium amount$")
	public void it_should_thrown_an_exception_on_calculating_premium_amount() throws Throwable {
	    
		premiumCalculationServiceTest.testPremiumAmountInvalidValue();
	}

	@Given("^an calculated premium amount (\\d+)$")
	public void an_calculated_premium_amount(double premiumAmount) throws Throwable {
	    
		premiumCalculationServiceTest.setPremiumAmount(premiumAmount);
	}

	@When("^we need to calculate premium amount for a person based on habit scenarios$")
	public void we_need_to_calculate_premium_amount_for_a_person_based_on_habit_scenarios() throws Throwable {
	    
		premiumCalculationServiceTest.setPersonDetails();
	}

	@Then("^it should return the calculated premium amount using Habits$")
	public void it_should_return_the_calculated_premium_amount_using_Habits() throws Throwable {
	    
		premiumCalculationServiceTest.testCalculatorForHabits();
	}

	@Then("^it should return the calculated premium amount using health$")
	public void it_should_return_the_calculated_premium_amount_using_health() throws Throwable {
	    
		premiumCalculationServiceTest.testCalculatorForHealth();
	}
	
	@Then("^it should return the calculated premium amount using gender$")
	public void it_should_return_the_calculated_premium_amount_using_gender() throws Throwable {
	    
		premiumCalculationServiceTest.testCalculatorForGender();
	}

	@Then("^it should return the calculated premium amount using age$")
	public void it_should_return_the_calculated_premium_amount_using_age() throws Throwable {
	 
		premiumCalculationServiceTest.testCalculatorForAge();
	}
	
}
