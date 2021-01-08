package com.kgisl.version.stepdefinitions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.log4j.Logger;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class DriverFactory {


	String workingDir = System.getProperty("user.dir");
	static final Logger logger = Logger.getLogger(DriverFactory.class);
//	private WebDriver driver;
	
@Before
public void initialize(Scenario scenario) throws Exception {
	try {
	System.setProperty("webdriver.gecko.driver","D:\\Elamaruthu\\d drive\\EclipseWorkSpace\\Artrail_Version\\drivers\\Windows\\firefoxX64\\geckodriver.exe");
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