package com.codechasers.aspiringminds.core.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.util.Assert;

import com.codechasers.aspiringminds.core.services.impl.ConvertDocxToPdf;

public class ConvertDocxToPdfTest extends BaseUnitTest{

	@InjectMocks
	ConvertDocxToPdf docxToPdfConverter;
	
	@SuppressWarnings("static-access")
	@Test
	public void shouldProcessDocxtoPdf(){
		byte[] actual = null;
		try{
			Path filePath = Paths.get(getClass().getClassLoader().getResource("JobOfferLetterTemplate"+File.separator+"JOB OFFER LETTER.docx").toURI());
			byte[] inputDocxStream = Files.readAllBytes(filePath);
			actual = docxToPdfConverter.processDocxtoPdf(inputDocxStream, "PDF");
		} catch(Exception e){
			e.printStackTrace();
		}
		Assert.notNull(actual);
	}
}
