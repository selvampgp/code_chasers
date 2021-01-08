package com.kgisl.version.stepdefinitions;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import kgisl.com.version.details.ApplicationLogin;

public class LoadApplication {
	private WebDriver driver;
	private Map<String, String> testData = new HashMap<>();
	ApplicationLogin application;
	DriverFactory driverFactory;
	static List<Object> pages;
	static List<Object> classes;
	
	@Given("^Load the application$")
	public void load_the_application() throws Exception {
    // Write code here that turns the phrase above into concrete actions
		String url="http://10.100.4.209:8085/artrail/login";
		pages = new ArrayList<Object>();
		classes = new ArrayList<Object>();
		driver= new FirefoxDriver();
		application = getPageFactoryObject(ApplicationLogin.class);
		driverFactory=getPageFactoryObject(DriverFactory.class);
		application.loadApp(url);	
	}

	@Given("^Enter the valid username and password$")
	public void enter_the_valid_username_and_password(DataTable data) throws Exception {
		application.loginDetails(data);
	}

	@When("^Clicking on submit button$")
	public void clicking_on_submit_button() throws Exception {
    // Write code here that turns the phrase above into concrete actions
		application.submit();
	}

	@Then("^System should login sucessfully$")
	public void system_should_login_sucessfully() throws Exception {
    // Write code here that turns the phrase above into concrete actions

	}
	
	@Given("^Enter the in-valid username and password$")
	public void enter_the_in_valid_username_and_password(DataTable data) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		application.loginDetails(data);
	}

	@Then("^System should not login sucessfully$")
	public void system_should_not_login_sucessfully(DataTable data) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		Boolean bool=false;
		testData=returnSingleTestData(data);
		String acutalMessage=application.validationMessage();
		if(acutalMessage.equals(testData.get("ValidationMessage"))) {
			bool=true;
		}
		Assert.assertTrue(bool);
	}

	@When("^Clicking on active session tab$")
	public void clicking_on_active_session_tab() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		Thread.sleep(5000);
		application.clickOnActiveUserTab();
	}

	@Then("^System shows the user device, IP, OS, browser details$")
	public void system_shows_the_user_device_IP_OS_browser_details(DataTable data) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertTrue(application.userDetails(data));
	}
	@Given("^Check on create user button$")
	public void check_on_create_user_button() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		application.clickOnCreate();
	}

	@Given("^Enter the user details$")
	public void enter_the_user_details(DataTable data) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		application.enterUserDetails(data);
	}

	@When("^Click on the save button$")
	public void click_on_the_save_button() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		application.saveButton();
	}

	@Then("^System create the user successfully$")
	public void system_create_the_user_successfully(DataTable data) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertTrue(application.alertMessage(data));
	}
	public static Map<String, String> returnSingleTestData(DataTable dataTable) {
		List<Map<String, String>> mapperdata = dataTable.asMaps(String.class, String.class);
		return mapperdata.get(0);
	}
	
	@When("^Clicking on logout button$")
	public void clicking_on_logout_button() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		application.logout();
	}

	@Then("^System will navigate to login screen$")
	public void system_will_navigate_to_login_screen() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		
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
