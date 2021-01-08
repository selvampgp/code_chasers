package com.kgisl.premium.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kgisl.premium.models.Insurance;

@Repository
public class InsuranceDao {

	/* Hibernate session factory */
	@Autowired
	SessionFactory sessionFactory;

	/**
	 * Fetch list of insurance details
	 * @return list of insurance details
	 */
	@SuppressWarnings("unchecked")
	public List<Insurance> getInsurance() {

		return sessionFactory.getCurrentSession().createCriteria(Insurance.class).list();
	}
	
	/**
	 * Used to create insurance details in DB.
	 * 
	 * @param insurance with person details
	 * @return Insurance object
	 */
	public Insurance createInsurance(Insurance insurance) {
		sessionFactory.getCurrentSession().persist(insurance);
		return insurance;
	}
}
