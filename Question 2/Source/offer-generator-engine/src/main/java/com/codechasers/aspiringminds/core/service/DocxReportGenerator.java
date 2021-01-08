package com.codechasers.aspiringminds.core.service;

import java.util.List;

import com.codechasers.aspiringminds.core.models.OfferTemplate;

/**
 * 
 * @author selvam.m
 *
 */
public interface DocxReportGenerator {

	/** To generate the word document with the the custom data.
	 * @param offerTemplate The template details to be generated
	 * @return The document in the byte array format
	 * @throws Exception
	 */
	public byte[] generate( OfferTemplate offerTemplate) throws Exception;
	
	/** To extract the customized fields used in the template.
	 * @param offerTemplate The selected Offer template.
	 * @return The list of custom fields.
	 * @throws Exception
	 */
	public List<String> extractFields( OfferTemplate offerTemplate) throws Exception;
}
