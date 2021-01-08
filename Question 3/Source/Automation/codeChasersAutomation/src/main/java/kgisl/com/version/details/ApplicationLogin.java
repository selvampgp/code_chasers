package kgisl.com.version.details;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import cucumber.api.DataTable;


public class ApplicationLogin  {

	private WebDriver driver;
	private Map<String, String> testData = new HashMap<>();
	static final Logger applicationLoginLogger = Logger.getLogger(ApplicationLogin.class);
	
	public ApplicationLogin(WebDriver driver) {
		super();
		this.driver = driver;
	}

	@FindBy(id = "j_username")
	private WebElement username;
	
	@FindBy(id = "j_password")
	private WebElement password;
	
	@FindBy(xpath="/html/body/form/button")
	private WebElement login;
	
	@FindBy(id = "LoginErrMsh")
	private WebElement validationMessage;
	
	@FindBy(id = "nav-profile-tab")
	private WebElement activeTab;
	
	@FindBy(id = "firstName")
	private WebElement firstName;
	
	@FindBy(id = "lastName")
	private WebElement lastName;
	
	@FindBy(id = "username")
	private WebElement user;
	
	@FindBy(id = "password")
	private WebElement userPassword;
	
	@FindBy(id = "email")
	private WebElement email;
	
	@FindBy(css = "button.btn")
	private WebElement saveButton;
	
	@FindBy(css = "a.btn")
	private WebElement homeButton;
	
	@FindBy(css = "a.btn:nth-child(1)")
	private WebElement createUser;
	
	@FindBy(css = "a.btn:nth-child(2)")
	private WebElement logout;
	
	@FindBy(css = ".alert")
	private WebElement alertMessage;
	
	@FindBy(xpath="/html/body/div/div[2]/div/div[2]/div/table/tbody/tr/td[4]")
	private WebElement IP;
	
	@FindBy(xpath="/html/body/div/div[2]/div/div[2]/div/table/tbody/tr/td[5]")
	private WebElement browser;
	
	@FindBy(xpath="/html/body/div/div[2]/div/div[2]/div/table/tbody/tr/td[6]")
	private WebElement device;
	
	@FindBy(xpath="/html/body/div/div[2]/div/div[2]/div/table/tbody/tr/td[7]")
	private WebElement OS;
	public String loadApp(String url) throws Exception  {
		try{
//				Thread.sleep(5000);
				driver.get(url);
			applicationLoginLogger.info("Application loaded successfully...");
			return url;
		}
		catch(Exception ex){
			applicationLoginLogger.error("Application failed to load...",ex);
			throw ex;
		}
	}
	public Boolean loginDetails(DataTable data) {
		try {
			testData=returnSingleTestData(data);
			username.sendKeys(testData.get("username"));
			password.sendKeys(testData.get("password"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;	
	}
	public void submit() {
		try {
			login.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public String validationMessage() {
		String message=null;
		try {
			message=validationMessage.getText();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return message;
	}
	
	public void clickOnActiveUserTab() {
		activeTab.click();
	}
	
	public void clickOnCreate() {
		createUser.click();
	}
	public void enterUserDetails(DataTable data) {
		testData=returnSingleTestData(data);
		firstName.sendKeys(testData.get("FirstName"));
		lastName.sendKeys(testData.get("LastName"));
		user.sendKeys(testData.get("UserName"));
		userPassword.sendKeys(testData.get("Password"));
		email.sendKeys(testData.get("Mail"));
	}
	
	public void saveButton() throws InterruptedException {
		saveButton.click();
		Thread.sleep(2000);
		homeButton.click();
	}
	
	public void logout() throws InterruptedException {
		Thread.sleep(5000);
		logout.click();
	}
	public Boolean alertMessage(DataTable data) {
		boolean bool=false;
		testData=returnSingleTestData(data);
		if(alertMessage.getText().contains(testData.get("Message"))) {
			bool=true;
		}
		return bool;
	}
	public Boolean userDetails(DataTable data) {
		Boolean bool=false;
		testData=returnSingleTestData(data);
		if(IP.getText().contains(testData.get("ExpectedIP"))){
			if(OS.getText().contains(testData.get("ExpectedOS"))) {
				if(device.getText().contains(testData.get("ExpectedDevice"))) {
					if(browser.getText().contains(testData.get("ExpectedBrowser"))) {
						bool=true;
					}
				}
			}
		}
		return bool;
	}
	public static Map<String, String> returnSingleTestData(DataTable dataTable) {
		List<Map<String, String>> mapperdata = dataTable.asMaps(String.class, String.class);
		return mapperdata.get(0);
	}
	
}

