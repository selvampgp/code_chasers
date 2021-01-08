package com.codechasers.aspiringminds.core.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.util.Assert;

import com.codechasers.aspiringminds.core.models.OfferTemplate;
import com.codechasers.aspiringminds.core.services.impl.XDocxReportGeneratorImpl;

public class DocxReportGeneratorTest extends BaseUnitTest {
	
	@InjectMocks
	XDocxReportGeneratorImpl docxReportGenerator;
	
	@Test
	public void shouldGenerateReport(){
		Map<String, Object> jsonData = new HashMap<>();
		jsonData.put("dt.candidate_Name", "Rajashree");
		jsonData.put("dt.job_position", "Developer");
		jsonData.put("dt.companyname", "XYZ");
		jsonData.put("dt.hrhead", "HR Department");
		byte[] actual = null;
		try{
			Path filePath = Paths.get(getClass().getClassLoader().getResource("JobOfferLetterTemplate"+File.separator+"JOB OFFER LETTER.docx").toURI());
			OfferTemplate offerTemplate = new OfferTemplate();
			offerTemplate.setTemplateFile(Files.readAllBytes(filePath));
			offerTemplate.setJsonData(jsonData);
			actual = docxReportGenerator.generate(offerTemplate);
		} catch(Exception e){
			e.printStackTrace();
		}
		Assert.notNull(actual);
	}
	
	@Test
	public void shouldExtractFields(){
		Map<String, Object> jsonData = new HashMap<>();
		jsonData.put("dt.candidate_Name", "Rajashree");
		jsonData.put("dt.job_position", "Developer");
		jsonData.put("dt.companyname", "XYZ");
		jsonData.put("dt.hrhead", "HR Department");
		
		String expected = "dt.candidate_Name";
		List<String> actual = new ArrayList<>();
		try{
			Path filePath = Paths.get(getClass().getClassLoader().getResource("JobOfferLetterTemplate"+File.separator+"JOB OFFER LETTER.docx").toURI());
			OfferTemplate offerTemplate = new OfferTemplate();
			offerTemplate.setTemplateFile(Files.readAllBytes(filePath));
			offerTemplate.setJsonData(jsonData);
			actual = docxReportGenerator.extractFields(offerTemplate);
		} catch(Exception e){
			e.printStackTrace();
		}
		Assert.notEmpty(actual, expected);
	}
}
