package kgisl.com.version.details;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kgisl.version.stepdefinitions.DriverFactory;

import cucumber.api.DataTable;


public class ApplicationLogin   {

	private WebDriver driver;
	Map<String, String> testData = new HashMap<>();

	static final Logger applicationLoginLogger = Logger.getLogger(ApplicationLogin.class);
	
	public ApplicationLogin(WebDriver driver) {
		super();
		this.driver = driver;
	}

	/*@FindBy(id="get_quote_button")
	private WebElement getPremium;*/
	@FindBy(xpath="//*[@id=\"get_quote_button\"]")
	private WebElement getPremium;

	@FindBy(id="fname")
	private WebElement firstName;

	@FindBy(id="lname")
	private WebElement lastName;
	
	@FindBy(id="dob")
	private WebElement dob;
	
	@FindBy(id="gender")
	private WebElement gender;
	
	@FindBy(id="phoneNumber")
	private WebElement phoneNumber;

	@FindBy(id="email")
	private WebElement emailid;

	@FindBy(css="div.row:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > button:nth-child(1)")
	private WebElement preconditions;
	
	@FindBy(xpath="/html/body/div[1]/div/div[3]/div/div/div/div[1]/div[4]/div[1]/div/div/div/ul/li[2]/label")
	private WebElement hypertension;
	
    @FindBy(xpath="/html/body/div[1]/div/div[3]/div/div/div/div[1]/div[4]/div[1]/div/div/div/ul/li[3]/label")
    private WebElement bloodPressure;
    
    @FindBy(xpath="/html/body/div[1]/div/div[3]/div/div/div/div[1]/div[4]/div[1]/div/div/div/ul/li[4]/label")
    private WebElement sugar;
    
    @FindBy(xpath="/html/body/div[1]/div/div[3]/div/div/div/div[1]/div[4]/div[1]/div/div/div/ul/li[5]/label")
    private WebElement overWeight;
    
    @FindBy(xpath="/html/body/div[1]/div/div[3]/div/div/div/div[1]/div[4]/div[2]/div/div/button")
    private WebElement habit;
    
    @FindBy(xpath="/html/body/div[1]/div/div[3]/div/div/div/div[1]/div[4]/div[2]/div/div/div/ul/li[2]/label")
    private WebElement smoking;
    
    @FindBy(xpath="/html/body/div[1]/div/div[3]/div/div/div/div[1]/div[4]/div[2]/div/div/div/ul/li[3]/label")
    private WebElement alcohol;
    
    @FindBy(xpath="/html/body/div[1]/div/div[3]/div/div/div/div[1]/div[4]/div[2]/div/div/div/ul/li[4]/label")
    private WebElement exercise;
    
    @FindBy(xpath="/html/body/div[1]/div/div[3]/div/div/div/div[1]/div[4]/div[2]/div/div/div/ul/li[5]/label")
    private WebElement drugs;
    
    
	@FindBy(className="btn-danger")
	private WebElement closeButton;

	@FindBy(css="div.modal-footer:nth-child(5) > button:nth-child(2)")
	private WebElement calculatePremium;
	
	@FindBy(id="premiumAMount")
	private WebElement premiumAmount;
	

	@FindBy(css="button.btn-sm:nth-child(3)")
	private WebElement buypremium;
	

	@FindBy(css="div.modal-footer:nth-child(4) > button:nth-child(2)")
	private WebElement backButton;
	

	@FindBy(css="div.modal-footer:nth-child(4) > button:nth-child(1)")
	private WebElement closepremium;
	
	@FindBy(id="cardNumber")
	private WebElement cardNumber;
	
	@FindBy(id="cvv")
	private WebElement cvvNumber;
	
	@FindBy(id="expMonth")
	private WebElement expmonth;
	
	@FindBy(id="expYear")
	private WebElement expYear; 
	
	@FindBy(css="div.modal-footer:nth-child(3) > button:nth-child(2)")
	private WebElement submitPremium;  

	@FindBy(css="div.modal-footer:nth-child(3) > button:nth-child(1)")
	private WebElement closePremium; 
	
	@FindBy(css="#alertmsg > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)")
	private WebElement successMessage;
	
	@FindBy(css=".btn-success")
	private WebElement closeSuccessMessage;
	
	
	public void enterpersondetails(DataTable details) throws InterruptedException
	{
	 testData=returnSingleTestData(details);
	 Thread.sleep(5000);
	 firstName.sendKeys(testData.get("firstname"));
	 lastName.sendKeys(testData.get("lastname"));
	 dob.sendKeys(testData.get("dob"));
	 Select gen= new Select(gender);
	 gen.selectByVisibleText(testData.get("gender"));
	 phoneNumber.sendKeys(testData.get("phone"));
	 emailid.sendKeys(testData.get("email"));
	 
	}

	public static Map<String, String> returnSingleTestData(DataTable dataTable) {
		List<Map<String, String>> mapperdata = dataTable.asMaps(String.class, String.class);
		return mapperdata.get(0);
	}


	public void waitforelementTovisible(WebDriver driver, WebElement element)
	{
		WebDriverWait wait =new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	public String getPremiumamountValue()
	{
		String premium=premiumAmount.getText();
		return premium;
	}

	public void clickgetpremiumcalculation()
	{
		getPremium.click();
		
		
	}
	
	public void clickgetpremiumAmount()
	{
		calculatePremium.click();
		
		
	}
	public void selectBPandSugar()
	{
		preconditions.click();
		bloodPressure.click();
		sugar.click();
	}
	
	public void selectGoodhabit()
	{
		habit.click();
		exercise.click();
		habit.click();
	}
	public void selectshabitsAlcoholandDrugs()
	{
		habit.click();
		alcohol.click();
		drugs.click();
		habit.click();
	}
	
	public void selectOverweight()
	{
		preconditions.click();
		overWeight.click();
	}
	
	public void selectAllPreeistingconditions()
	{
		preconditions.click();
		hypertension.click();
		bloodPressure.click();
		sugar.click();
		overWeight.click();
	}
	
	public void selectAllGoodandBadhabits()
	{
		habit.click();
		smoking.click();
		alcohol.click();
		exercise.click();
		drugs.click();
		habit.click();
	}
	
	public void clickbuypremiumButton()
	{
		buypremium.click();
	}
	
	public void entercardDetails(DataTable data)
	{
		testData=returnSingleTestData(data);
		 cardNumber.sendKeys(testData.get("cardnumber"));
		 cvvNumber.sendKeys(testData.get("CVV"));
		 Select month= new Select(expmonth);
		 month.selectByVisibleText(testData.get("Month"));
		 Select expyear=new Select(expYear);
		 expyear.selectByVisibleText(testData.get("Year"));
		
	}
	
	public String successMessage()
	{

		String success=successMessage.getText();
		return success;
	}
	
	public void submitpayment()
	{
		submitPremium.click();
	}
}

