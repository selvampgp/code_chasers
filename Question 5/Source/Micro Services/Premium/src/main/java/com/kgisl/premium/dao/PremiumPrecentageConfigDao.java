package com.kgisl.premium.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kgisl.premium.models.Insurance;


public class PremiumPrecentageConfigDao {
	
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

}
