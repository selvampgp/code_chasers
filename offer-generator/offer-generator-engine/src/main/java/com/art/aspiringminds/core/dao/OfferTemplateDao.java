package com.art.aspiringminds.core.dao;

import java.util.List;

import com.art.aspiringminds.core.models.OfferTemplate;

public interface OfferTemplateDao {

	public void saveOfferTemplate(OfferTemplate offerTemplate);
	
	public void updateOfferTemplate(OfferTemplate offerTemplate);
	
	public OfferTemplate loadOfferTemplateById(String id);
	
	public List<OfferTemplate> findAll();
}
