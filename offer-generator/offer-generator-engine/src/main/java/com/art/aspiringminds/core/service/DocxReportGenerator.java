package com.art.aspiringminds.core.service;

import java.util.List;

import com.art.aspiringminds.core.models.OfferTemplate;

/**
 * 
 * @author selvam.m
 *
 */
public interface DocxReportGenerator {

	public byte[] generate( OfferTemplate offerTemplate) throws Exception;
	public List<String> extractFields( OfferTemplate offerTemplate) throws Exception;
}
