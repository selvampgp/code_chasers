package com.art.aspiringminds.core.service;

import java.util.List;

import com.art.aspiringminds.core.models.OfferTemplate;

public interface OfferTemplateService {

	public void saveOfferTemplate(OfferTemplate offerTemplate);
	
	public void updateOfferTemplate(OfferTemplate offerTemplate);
	
	public OfferTemplate loadOfferTemplateById(String id);
	
	public List<OfferTemplate> findAll();
}
