package com.codechasers.aspiringminds.core.rest.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codechasers.aspiringminds.core.models.OfferTemplate;
import com.codechasers.aspiringminds.core.service.DocxReportGenerator;
import com.codechasers.aspiringminds.core.service.OfferTemplateService;
import com.codechasers.aspiringminds.core.services.impl.ConvertDocxToPdf;
import com.codechasers.aspiringminds.core.services.impl.XDocxReportGeneratorImpl;

/**
 * Job Offer Letter engine is used to generate customizable offer letters in PDF format.
 * The offer letter template is allowed to configure custom fields which will be 
 * replaced with the dynamic data given by user at the time of PDF generation.
 * 
 * @author selvam.m
 * 
 * @see OfferTemplateService
 *
 */
@RestController
@RequestMapping(value = "/api")
public class OfferRestController {


	@Autowired
	OfferTemplateService offerTemplateService;

	
	/** To fetch all the offer letter templates.
	 * @return The list of OfferTemplate.
	 */
	@RequestMapping(value="/offer-letter-template",method=RequestMethod.GET)
	public List<OfferTemplate> fetchAllOfferTemplate(){
		return offerTemplateService.findAll();
	}
	
	/** To save the offer letter template in MongoDB.
	 * @param attachedFile The template in DOCX format
	 * @param templateName The name of the Template.
	 * @return The response status code with respective response message.
	 * @throws IOException if any I/O exception occurs.
	 */
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

	/** To update the existing template for teh given id.
	 * @param attachedFile The revised offer letter document.
	 * @param templateId Id to be updated.
	 * @return The response status code with respective response message.
	 * @throws IOException if any I/O exception occurs.
	 */
	@RequestMapping(value = "/offer-letter-template/{id}", method = RequestMethod.POST)
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

	/** To download the Offer letter in PDF format.
	 * @param response the HttpServletResponse
	 * @param offerTemplate the details of selected OfferTemplate
	 * @return The PDF document.
	 * @throws Exception
	 */
	@RequestMapping(value = "/offer-ui-letter", method = RequestMethod.POST,headers ="content-type=application/x-www-form-urlencoded")
	public ResponseEntity<?> generateOfferLetterUiPost(HttpServletResponse response,@ModelAttribute OfferTemplate offerTemplate) throws Exception{
		return generateOfferLetter(offerTemplate,response);
	}
			
	
	/** To generate the offer letter template in PDF format.
	 * @param postOfferTemplate the details of selected OfferTemplate
	 * @param response the HttpServletResponse
	 * @return The response entity.
	 * @throws Exception
	 */
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
			
			byte[] pdfConvertedArray = ConvertDocxToPdf.processDocxtoPdf(reportArray, "PDF");
			
			
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

	/** To extract the custom fields used in the document template.
	 * @param id The id of the selected offer letter template.
	 * @return The list of custom fields.
	 * @throws Exception
	 */
	@RequestMapping(value = "/offer-letter-template/{templateId}/fields", method = RequestMethod.GET)
	public List<String> extractFieldsFromOfferLetter(
			@PathVariable("templateId") String id) throws Exception {

		OfferTemplate offerTemplate = offerTemplateService
				.loadOfferTemplateById(id);

		DocxReportGenerator reportGenerator = new XDocxReportGeneratorImpl();

		return reportGenerator.extractFields(offerTemplate);

	}

}
