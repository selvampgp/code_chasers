package com.codechasers.aspiringminds.core.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.util.Assert;

import com.codechasers.aspiringminds.core.services.impl.DocxTemplateParameterImpl;

public class TemplateParameterTest extends BaseUnitTest{

	@InjectMocks
	DocxTemplateParameterImpl docxReportParameter;
	
	@Test
	public void shouldExtractParameter(){
		List<String> actual = new ArrayList<>();
		try{
			Path filePath = Paths.get(getClass().getClassLoader().getResource("JobOfferLetterTemplate"+File.separator+"JOB OFFER LETTER.docx").toURI());
			actual = docxReportParameter.extractParameter(Files.readAllBytes(filePath));
		} catch(Exception e){
			e.printStackTrace();
		}
		Assert.notNull(actual);
	}
}
