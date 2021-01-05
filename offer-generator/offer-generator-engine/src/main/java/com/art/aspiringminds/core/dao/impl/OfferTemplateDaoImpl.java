package com.art.aspiringminds.core.dao.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.art.aspiringminds.core.dao.OfferTemplateDao;
import com.art.aspiringminds.core.models.OfferTemplate;

@Repository
public class OfferTemplateDaoImpl implements OfferTemplateDao{

	private final String COLLECTION_NAME="Offers";
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public void saveOfferTemplate(OfferTemplate offerTemplate) {

		 mongoTemplate.save(offerTemplate,COLLECTION_NAME);
	}

	@Override
	public OfferTemplate loadOfferTemplateById(String id) {

		return mongoTemplate.findOne(Query.query(Criteria.where("_id").is(new ObjectId(id))), OfferTemplate.class,COLLECTION_NAME);
	}

	@Override
	public void updateOfferTemplate(OfferTemplate offerTemplate) {
	
		mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(new ObjectId(offerTemplate.getId()))), Update.update("templateFile", offerTemplate.getTemplateFile()), OfferTemplate.class,COLLECTION_NAME);
		
	}

	@Override
	public List<OfferTemplate> findAll() {
		
		Query query = new Query();
		
		query.fields().exclude("templateFile");
		
		return mongoTemplate.find(query, OfferTemplate.class, COLLECTION_NAME);
	}
	
	
}