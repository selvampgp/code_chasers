package com.kgisl.version.stepdefinitions;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.apache.log4j.Logger;
import org.junit.Assert;
import kgisl.com.version.details.ApplicationLogin;

public class LoadApplication {
	private WebDriver driver;
	
	@FindBy(className = "version-txt")
	private WebElement version;
	ApplicationLogin application;
	DriverFactory driverFactory;
	static List<Object> pages;
	static List<Object> classes;
	String workingDir = System.getProperty("user.dir");
	static final Logger logger = Logger.getLogger(DriverFactory.class);

	
	
	@Given("^open calculator$")
	public void open_calculator() throws Exception {
		application = getPageFactoryObject(ApplicationLogin.class);
		application.clickgetpremiumcalculation();
	}

	@Given("^enter firname last name and DOB gender and phone number and email$")
	public void enter_firname_last_name_and_DOB_gender_and_phone_number_and_email(DataTable arg1) throws Exception {
	   application.enterpersondetails(arg1);
	}

	@When("^person age is below (\\d+) and cliking on get premium button$")
	public void person_age_is_below_and_cliking_on_get_premium_button(int arg1) throws Exception {
	 application.clickgetpremiumAmount();
	}

	@Then("^calculated premium value should be based on age and gender$")
	public void calculated_premium_value_should_be_based_on_age_and_gender(DataTable arg1) throws Exception {
	   String actualResult=application.getPremiumamountValue();
	   String expectedResult=application.returnSingleTestData(arg1).get("expectedresult");
	   assertEquals(actualResult, expectedResult);
	   
	}


	@Given("^person age between (\\d+) to (\\d+) and the person gender is male$")
	public void person_age_between_to_and_the_person_gender_is_male(int arg1, int arg2, DataTable arg3) throws Exception {
		application.enterpersondetails(arg3);
	}

	@Given("^preexisting codition are bloodpressure and sugar$")
	public void preexisting_codition_are_bloodpressure_and_sugar() throws Exception {
	    application.selectBPandSugar();
	}

	@Given("^the person having one good habit as daily exercise$")
	public void the_person_having_one_good_habit_as_daily_exercise() throws Exception {
	   application.selectGoodhabit();
	}

	@When("^calculating the premium$")
	public void calculating_the_premium() throws Exception {
	    application.clickgetpremiumAmount();
	}

	@Then("^system should calculate the premium based on the sum of value calculated based on age gender preexistin$")
	public void system_should_calculate_the_premium_based_on_the_sum_of_value_calculated_based_on_age_gender_preexistin(DataTable arg1) throws Exception {
		 String actualResult=application.getPremiumamountValue();
		   String expectedResult=application.returnSingleTestData(arg1).get("expectedresult").toString();
		   assertEquals(actualResult, expectedResult);
	}
	
	@Given("^person age between (\\d+) to (\\d+) and the person gender is female$")
	public void person_age_between_to_and_the_person_gender_is_female(int arg1, int arg2, DataTable arg3) throws Exception {
		application.enterpersondetails(arg3);
	}

	@Given("^the person having one bad habit as drugs and alcohol$")
	public void the_person_having_one_bad_habit_as_drugs_and_alcohol() throws Exception {
	   application.selectshabitsAlcoholandDrugs();
	}

	@Then("^system should calculate the premium based on gender and preexisting condition and bad habits$")
	public void system_should_calculate_the_premium_based_on_gender_and_preexisting_condition_and_bad_habits(DataTable arg1) throws Exception {
		String actualResult=application.getPremiumamountValue();
		   String expectedResult=application.returnSingleTestData(arg1).get("expectedresult").toString();
		   assertEquals(actualResult, expectedResult);
	}
	
	@Given("^preexisting codition is overweight$")
	public void preexisting_codition_is_overweight() throws Exception {
	    application.selectOverweight();
	}

	@Given("^the person having one bad habit as drugs and alcohol and good habit is daily exercise$")
	public void the_person_having_one_bad_habit_as_drugs_and_alcohol_and_good_habit_is_daily_exercise() throws Exception {
	    application.selectshabitsAlcoholandDrugs();
	    application.selectGoodhabit();
	}
	@Given("^person age is greater than (\\d+) and gender is male$")
	public void person_age_is_greater_than_and_gender_is_male(int arg1, DataTable arg2) throws Exception {
		application.enterpersondetails(arg2);
	}

	@Given("^the person heving good habit as daily exercise$")
	public void the_person_heving_good_habit_as_daily_exercise() throws Exception {
	    application.selectGoodhabit();
	}
	
	@Given("^person having all preexisting coditions$")
	public void person_having_all_preexisting_coditions() throws Exception {
	    application.selectAllPreeistingconditions();
	}

	@Then("^system should calculate the premium based on gender and preexisting conditions and habits$")
	public void system_should_calculate_the_premium_based_on_gender_and_preexisting_conditions_and_habits(DataTable arg1) throws Exception {
		String actualResult=application.getPremiumamountValue();
		   String expectedResult=application.returnSingleTestData(arg1).get("expectedresult").toString();
		   assertEquals(actualResult, expectedResult);
	}
	
	@Given("^the person having all good habits and bad habits$")
	public void the_person_having_all_good_habits_and_bad_habits() throws Exception {
	    application.selectAllGoodandBadhabits();
	}
	
	@Given("^clicking on buypremium$")
	public void clicking_on_buypremium() throws Exception {
	    application.clickbuypremiumButton();
	}

	@Given("^enter the card details$")
	public void enter_the_card_details(DataTable arg1) throws Exception {
	   application.entercardDetails(arg1);
	}

	@When("^submitting the payment$")
	public void submitting_the_payment() throws Exception {
	    application.submitpayment();
	}

	@Then("^system should display the success message\\.$")
	public void system_should_display_the_success_message(DataTable arg1) throws Exception {
		String actualResult=application.successMessage();
		   String expectedResult=application.returnSingleTestData(arg1).get("expectedresult").toString();
		   assertEquals(actualResult, expectedResult);
	}
	@Then("^system should calculate the premium based on the sum of value calculated based on age gender preexisting conditions$")
	public void system_should_calculate_the_premium_based_on_the_sum_of_value_calculated_based_on_age_gender_preexisting_conditions(DataTable arg1) throws Exception {
		String actualResult=application.getPremiumamountValue();
		   String expectedResult=application.returnSingleTestData(arg1).get("expectedresult").toString();
		   assertEquals(actualResult, expectedResult);
	}
@Before
public void initialize(Scenario scenario) throws Exception {
	try {
	System.setProperty("webdriver.gecko.driver","drivers/Windows/firefoxX64/geckodriver.exe");
	String url="http://10.100.4.178:8024/";
	Proxy proxy = new Proxy();
	//Adding the desired host and port for the http, ssl, and ftp Proxy Servers respectively
	proxy.setHttpProxy("10.100.1.124:3128");
	proxy.setSslProxy("10.100.1.124:3128");
	proxy.setSslProxy("10.100.1.124:3128");
	proxy.setFtpProxy("10.100.1.124:3128");
	FirefoxOptions options = new FirefoxOptions();
	options.setCapability("proxy", proxy);
	//Calling new Firefox profile for test
	 driver = new FirefoxDriver(options);
	driver.manage().window().maximize();
		driver.get(url);
		pages = new ArrayList<Object>();
		classes = new ArrayList<Object>();
	}
	catch(Exception e)
	{
		throw e;
	}
}

@After
public void closeBrowser()
{
	driver.close();
}
@SuppressWarnings("unchecked")
public <T> T getPageFactoryObject(Class<T> clazzname) {
	try {
		for (Object page : pages) {
			if (page.getClass() == clazzname)
				return (T) page;
		}
		T page = PageFactory.initElements(driver, clazzname);
		pages.add(page);
		return page;
	} catch (Exception ex) {
		throw ex;
	}
}

}
