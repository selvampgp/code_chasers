package com.kgisl.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kgisl.payment.dao.PaymentDao;
import com.kgisl.payment.modal.Insurance;

@Transactional
@Service
public class PaymentService {

	@Autowired
	private PaymentDao paymentDao;

	public Insurance getInsuranceById(Integer id) {

		return paymentDao.getInsuranceById(id);
	}

	public void postPayment(Insurance insurance) {
		
		paymentDao.postPayment(insurance);
	}

}
