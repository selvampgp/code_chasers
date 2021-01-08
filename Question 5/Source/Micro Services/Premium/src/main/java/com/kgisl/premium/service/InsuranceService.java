package com.kgisl.premium.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kgisl.premium.dao.InsuranceDao;
import com.kgisl.premium.models.Insurance;

@Transactional
@Service
public class InsuranceService {

	@Autowired
	private InsuranceDao insuranceDao;

	/**
	 * Fetch list of insurance details
	 * @return list of insurance details
	 */
	public List<Insurance> getInsurance() {

		return insuranceDao.getInsurance();
	}
	
	/**
	 * Used to create insurance details in DB.
	 * 
	 * @param insurance with person details.
	 * @return Insurance object
	 */
	public Insurance createInsuranceWithPremium(Insurance insurance) {
		return insuranceDao.createInsurance(insurance);
	}
}
