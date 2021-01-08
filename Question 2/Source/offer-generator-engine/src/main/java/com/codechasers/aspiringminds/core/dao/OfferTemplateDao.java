package com.codechasers.aspiringminds.core.dao;

import java.util.List;

import com.codechasers.aspiringminds.core.models.OfferTemplate;

public interface OfferTemplateDao {

	/** To save the OfferLetter Template in MongoDB.
	 * @param offerTemplate The template details to be saved. 
	 */
	public void saveOfferTemplate(OfferTemplate offerTemplate);
	
	/** To update the existing template in MongoDB.
	 * @param offerTemplate The template to be updated.
	 */
	public void updateOfferTemplate(OfferTemplate offerTemplate);
	
	/** To fetch the offer letter template details for the given id.
	 * @param id The id to be searched.
	 * @return The required OfferTemplate.
	 */
	public OfferTemplate loadOfferTemplateById(String id);
	
	/** To list out all the saved offer letter templates.
	 * @return The list of OfferTemplate.
	 */
	public List<OfferTemplate> findAll();
}
