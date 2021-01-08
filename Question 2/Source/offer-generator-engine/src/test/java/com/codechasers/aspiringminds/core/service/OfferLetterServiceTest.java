package com.codechasers.aspiringminds.core.service;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.codechasers.aspiringminds.core.dao.OfferTemplateDao;
import com.codechasers.aspiringminds.core.models.OfferTemplate;
import com.codechasers.aspiringminds.core.services.impl.OfferLetterServiceImpl;


public class OfferLetterServiceTest extends BaseUnitTest{

	@InjectMocks
	OfferLetterServiceImpl offerTemplateService;
	
	@Mock
	OfferTemplateDao offerTemplateDao;
	
	
	@Test
	public void shoulSaveOfferTemplate(){
		OfferTemplate offerTemplate = new OfferTemplate();
		try{
			Path filePath = Paths.get(getClass().getClassLoader().getResource("JobOfferLetterTemplate"+File.separator+"JOB OFFER LETTER.docx").toURI());
			offerTemplate.setTemplateName("QARoleOfferLetter");
			offerTemplate.setCreatedDate(new Date());
			offerTemplate.setTemplateFile(Files.readAllBytes(filePath));
		} catch(Exception e){
			e.printStackTrace();
		}
		
		Mockito.doNothing().when(offerTemplateDao).saveOfferTemplate(Mockito.any(OfferTemplate.class));
		offerTemplateService.saveOfferTemplate(offerTemplate);
		Mockito.verify(offerTemplateDao, times(1)).saveOfferTemplate(offerTemplate);
	}
	
	@Test
	public void shouldUpdateOfferTemplate(){
		OfferTemplate offerTemplate = new OfferTemplate();
		try{
			Path filePath = Paths.get(getClass().getClassLoader().getResource("JobOfferLetterTemplate"+File.separator+"JOB OFFER LETTER.docx").toURI());
			offerTemplate.setId("5ff55200bd8c33ebd37d51aa");
			offerTemplate.setTemplateFile(Files.readAllBytes(filePath));
		} catch(Exception e){
			e.printStackTrace();
		}
		
		Mockito.doNothing().when(offerTemplateDao).updateOfferTemplate(Mockito.any(OfferTemplate.class));
		offerTemplateService.updateOfferTemplate(offerTemplate);
		Mockito.verify(offerTemplateDao, times(1)).updateOfferTemplate(offerTemplate);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void shouldLoadOfferTemplateById(){
		OfferTemplate offerTemplate = new OfferTemplate();
		try{
			Path filePath = Paths.get(getClass().getClassLoader().getResource("JobOfferLetterTemplate"+File.separator+"JOB OFFER LETTER.docx").toURI());
			offerTemplate.setId("5ff55200bd8c33ebd37d51aa"); 
			offerTemplate.setTemplateName("Dev_OfferLetter");
			offerTemplate.setCreatedDate(new Date());
			offerTemplate.setTemplateFile(Files.readAllBytes(filePath));
		} catch(Exception e){
			e.printStackTrace();
		}
		
		when(offerTemplateDao.loadOfferTemplateById("5ff55200bd8c33ebd37d51aa")).thenReturn(offerTemplate);
		OfferTemplate actual = offerTemplateService.loadOfferTemplateById("5ff55200bd8c33ebd37d51aa");
		Assert.assertEquals(offerTemplate.getTemplateName(), actual.getTemplateName());
	}
	
	@Test
	public void shouldfetchAllTemplate(){
		OfferTemplate offerTemplate = new OfferTemplate();
		try{
			Path filePath = Paths.get(getClass().getClassLoader().getResource("JobOfferLetterTemplate"+File.separator+"JOB OFFER LETTER.docx").toURI());
			offerTemplate.setId("5ff55200bd8c33ebd37d51aa"); 
			offerTemplate.setTemplateName("Dev_OfferLetter");
			offerTemplate.setCreatedDate(new Date());
			offerTemplate.setTemplateFile(Files.readAllBytes(filePath));
		} catch(Exception e){
			e.printStackTrace();
		}
		
		when(offerTemplateDao.findAll()).thenReturn(Arrays.asList(offerTemplate));
		List<OfferTemplate> actual = offerTemplateService.findAll();
		Assert.assertNotNull(actual);
		
	}
}
