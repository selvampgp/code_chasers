package com.kgisl.payment.dao;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kgisl.payment.modal.Insurance;

@Repository
public class PaymentDao {

	@Autowired
	SessionFactory sessionFactory;

	public Insurance getInsuranceById(Integer id) {

		return (Insurance) sessionFactory.getCurrentSession().createCriteria(Insurance.class)
				.add(Restrictions.eq("insId", id))
				.uniqueResult();
	}

	public void postPayment(Insurance insurance) {

		sessionFactory.getCurrentSession().saveOrUpdate(insurance);
	}

	
}
