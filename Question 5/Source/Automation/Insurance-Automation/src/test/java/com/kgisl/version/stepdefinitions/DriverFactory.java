package com.kgisl.version.stepdefinitions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.log4j.Logger;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.Before;

public class DriverFactory {

	static List<Object> pages;
	static List<Object> classes;
	String workingDir = System.getProperty("user.dir");
	static final Logger logger = Logger.getLogger(DriverFactory.class);
	private WebDriver driver;
	

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


public void shootMail(HashMap<String, String> testData,DataTable data) throws Exception {
	try {
		String mailIds="techsupport@kghawes.com,armtesting@kgisl.com";
		String ccMailIds="tamilarasan.r@kgisl.com";
//		String mailIds="elamaruthu.a@kgisl.com,elamaruthu.a@kgisl.com";
//		String ccMailIds="elamaruthu.a@kgisl.com";
		Properties props = new Properties();
		props.put("mail.smtp.host", "10.100.1.22");
		props.put("mail.smtp.port", "25");
		props.put("mail.debug", "true");
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		message.setHeader("Content-Type", "text/html");
		message.setFrom(new InternetAddress("elamaruthu.a@kgisl.com"));
		InternetAddress [] toMailId= InternetAddress.parse(mailIds);
		InternetAddress [] ccMailId= InternetAddress.parse(ccMailIds);
		message.setRecipients(RecipientType.TO, toMailId);
		message.setRecipients(RecipientType.CC, ccMailId);
		String getHtmls = urlDetails(testData,data);
		message.setSentDate(new Date());
		message.setSubject("Version Details");
		String html= "<head>\r\n" + 
				"<style>\r\n" + 
				"table, th, td {\r\n" + 
				"  border: 1px solid black;\r\n" + 
				"  border-collapse: collapse;\r\n" + 
				"}\r\n" + 
				"</style>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<table style=\"width:100%\">\r\n" + 
				"  <tr>\r\n" + 
				"    <th>INSTANCE</th>\r\n" + 
				"    <th>VERSION</th> \r\n" + 
				"  </tr>" +
				getHtmls;
				
		logger.info("Formed mail body successfully...");
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.addHeader("content-type", "html/text");
		messageBodyPart.setText(html, "utf-8", "html");
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		message.setContent(multipart);
		Transport.send(message);
		logger.info("Mail sent successfully...");
		
	} catch (Exception e) {
		// TODO: handle exception
	}
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
private String urlDetails(HashMap<String, String> testData, DataTable data) {
	// TODO Auto-generated method stub
	try {
		StringBuilder strbody = new StringBuilder();
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://10.100.4.209:3306/artrailauthotfix", "ela", "ela");
		Statement statement = con.createStatement();
		ResultSet resultset = statement.executeQuery("select * from version order by version");
		int i=0,j=0;
		LinkedHashMap<String, String> uatLink = new LinkedHashMap<String,String>();
		LinkedHashMap<String, String> liveLink = new LinkedHashMap<String,String>();
		
		while(resultset.next()) {
			if(resultset.getString("instance").toString().contains("UAT")) {
				uatLink.put(resultset.getString("url"), resultset.getString("version"));
			}
			else {
				liveLink.put(resultset.getString("url"), resultset.getString("version"));
			}
		}
		System.out.println("UAT"+uatLink);
		System.out.println("LIVE"+liveLink);

		for(Map.Entry m:liveLink.entrySet()){
			if(i==0) {
				strbody.append("<th colspan=10; style= text-align:center,font-size: 5px; padding:1px> LIVE INSTANCE </th>");
				}
				strbody.append("<tr>");
				strbody.append("<td>"+m.getKey()+"</td>");
				strbody.append("<td>"+m.getValue()+"</td>");
				strbody.append("</tr>");
				System.out.println("LIVE"+m.getKey()+" "+m.getValue()); 
				i++;
			}
		for(Map.Entry m:uatLink.entrySet()){
			if(j==0) {
				strbody.append("<th colspan=10; style= text-align:center,font-size: 5px; padding:1px> UAT INSTANCE </th>");
				}
				strbody.append("<tr>");
				strbody.append("<td>"+m.getKey()+"</td>");
				strbody.append("<td>"+m.getValue()+"</td>");
				strbody.append("</tr>");
				System.out.println("LIVE"+m.getKey()+" "+m.getValue()); 
				j++;
			}
		return strbody.toString();
	}
	catch (Exception e) {
		// TODO: handle exception
		return null;
	}
	
}
}