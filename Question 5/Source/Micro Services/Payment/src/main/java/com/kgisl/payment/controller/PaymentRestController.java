package com.kgisl.payment.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kgisl.payment.modal.Insurance;
import com.kgisl.payment.service.PaymentService;

@RestController
public class PaymentRestController {
	
	@Autowired
	PaymentService paymentService;
	
	@PostMapping(value = { "/premium/{id}" })
	public ResponseEntity<Boolean> postPayment(@RequestBody BigDecimal premiumAmount, @PathVariable("id") Integer id){
		
		Insurance insurance = paymentService.getInsuranceById(id);
		
		if(Objects.nonNull(insurance)){
			insurance.setInsPremium(premiumAmount);
			insurance.setPaid('Y');
			insurance.setPaidDate(new Date());
			
			paymentService.postPayment(insurance);
			
			return ResponseEntity.status(HttpStatus.OK).body(true);
		}
		
		
		return ResponseEntity.status(HttpStatus.OK).body(false);
	}
}
