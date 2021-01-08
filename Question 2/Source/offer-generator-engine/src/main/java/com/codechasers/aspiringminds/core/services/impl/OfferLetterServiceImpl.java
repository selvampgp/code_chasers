package com.codechasers.aspiringminds.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codechasers.aspiringminds.core.dao.OfferTemplateDao;
import com.codechasers.aspiringminds.core.models.OfferTemplate;
import com.codechasers.aspiringminds.core.service.OfferTemplateService;

@Service
public class OfferLetterServiceImpl implements OfferTemplateService {

	@Autowired
	OfferTemplateDao offerTemplateDao;
	
	@Override
	public void saveOfferTemplate(OfferTemplate offerTemplate) {

		offerTemplateDao.saveOfferTemplate(offerTemplate);
	}

	@Override
	public OfferTemplate loadOfferTemplateById(String id) {
		
		return offerTemplateDao.loadOfferTemplateById(id);
	}

	@Override
	public void updateOfferTemplate(OfferTemplate offerTemplate) {
		
		offerTemplateDao.updateOfferTemplate(offerTemplate);
	}

	@Override
	public List<OfferTemplate> findAll() {
		return offerTemplateDao.findAll();
	}

}
