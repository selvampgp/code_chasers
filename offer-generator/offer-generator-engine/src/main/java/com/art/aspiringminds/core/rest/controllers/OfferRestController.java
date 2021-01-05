package com.art.aspiringminds.core.rest.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.art.aspiringminds.core.models.OfferTemplate;
import com.art.aspiringminds.core.service.DocxReportGenerator;
import com.art.aspiringminds.core.service.OfferTemplateService;
import com.art.aspiringminds.core.services.impl.ConvertDocxToPdf;
import com.art.aspiringminds.core.services.impl.XDocxReportGeneratorImpl;

@RestController
@RequestMapping(value = "/api")
public class OfferRestController {


	@Autowired
	OfferTemplateService offerTemplateService;

	
	@RequestMapping(value="/offer-letter-template",method=RequestMethod.GET)
	public List<OfferTemplate> fetchAllOfferTemplate(){
		return offerTemplateService.findAll();
	}
	
	@RequestMapping(value = "/offer-letter-template", method = RequestMethod.POST)
	public ResponseEntity<?> saveOfferTemplate(
			@RequestParam("offerTemplate") MultipartFile attachedFile,
			@RequestParam(name = "templateName") String templateName)
			throws IOException {

		if (!attachedFile.isEmpty()) {

			OfferTemplate offerTemplate = new OfferTemplate()
					.setTemplateFile(attachedFile.getBytes())
					.setCreatedDate(new Date()).setTemplateName(templateName)
					.build();

			offerTemplateService.saveOfferTemplate(offerTemplate);
			
			return ResponseEntity.ok("Template saved");
		}
		
		return new ResponseEntity<String>("Empty 'OfferTemplate' received",HttpStatus.BAD_REQUEST);

	}

	@RequestMapping(value = "/offer-letter-template/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateOfferTemplate(
			@RequestParam("offerTemplate") MultipartFile attachedFile,
			@PathVariable("id") String templateId) throws IOException {

		if (!attachedFile.isEmpty()) {

			OfferTemplate offerTemplate = new OfferTemplate()
					.setTemplateFile(attachedFile.getBytes()).setId(templateId)
					.build();

			offerTemplateService.updateOfferTemplate(offerTemplate);
			
			return ResponseEntity.ok("Template Updated");
		}
		
		return new ResponseEntity<String>("Empty 'OfferTemplate' received",HttpStatus.BAD_REQUEST);

	}

	@RequestMapping(value = "/offer-letter", method = RequestMethod.POST)
	public ResponseEntity<?> generateOfferLetter(
			@RequestBody OfferTemplate postOfferTemplate ,
			HttpServletResponse response) throws Exception {


		try{
			
			OfferTemplate offerTemplate = offerTemplateService
					.loadOfferTemplateById(postOfferTemplate.getId());
			
			offerTemplate.setJsonData(postOfferTemplate.getJsonData());
			
			DocxReportGenerator reportGenerator = new XDocxReportGeneratorImpl();
			
			byte[] reportArray = reportGenerator.generate(offerTemplate);
			
			try (OutputStream outputStream = new FileOutputStream(new File("d://test.docx"))){
				outputStream.write(reportArray);
			}
			
			
			byte[] pdfConvertedArray = ConvertDocxToPdf.processDocxtoPdf(reportArray, "PDF");
			
			try (OutputStream outputStream = new FileOutputStream(new File("d://test.pdf"))){
				outputStream.write(pdfConvertedArray);
			}
			
			if(pdfConvertedArray.length>0){
				ByteArrayResource byteArrayResource = new ByteArrayResource(pdfConvertedArray);
				
				HttpHeaders headers = new HttpHeaders(); 
				headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=OfferLetter.PDF");
				
				return ResponseEntity.ok().headers(headers)
						.contentLength(pdfConvertedArray.length)
						.contentType(MediaType.APPLICATION_OCTET_STREAM)
						.body(byteArrayResource);
				
			}
			
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("Exception while preparing the offer letter",HttpStatus.INTERNAL_SERVER_ERROR);
		

	}
	
	@RequestMapping(value = "/offer-letter-template/{templateId}", method = RequestMethod.GET)
	public ResponseEntity<?> generateOfferLetter(@PathVariable("templateId") String id,HttpServletResponse response) throws Exception {

		OfferTemplate offerTemplate = offerTemplateService
				.loadOfferTemplateById(id);

		if(offerTemplate.getTemplateFile().length>0){
			
			HttpHeaders headers = new HttpHeaders(); 
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+offerTemplate.getTemplateName()+".DOCX");
			
			ByteArrayResource byteArrayResource = new ByteArrayResource(offerTemplate.getTemplateFile());
			
			return ResponseEntity.ok().headers(headers)
					.contentLength(offerTemplate.getTemplateFile().length)
					.contentType(MediaType.APPLICATION_OCTET_STREAM)
					.body(byteArrayResource);
		}

		return new ResponseEntity<String>("Offer template not found",HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@RequestMapping(value = "/offer-letter-template/{templateId}/fields", method = RequestMethod.GET)
	public List<String> extractFieldsFromOfferLetter(
			@PathVariable("templateId") String id) throws Exception {

		OfferTemplate offerTemplate = offerTemplateService
				.loadOfferTemplateById(id);

		DocxReportGenerator reportGenerator = new XDocxReportGeneratorImpl();

		return reportGenerator.extractFields(offerTemplate);

	}

}
