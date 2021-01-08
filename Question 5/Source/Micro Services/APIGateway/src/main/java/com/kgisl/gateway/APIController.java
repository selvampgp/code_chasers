package com.kgisl.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

	@GetMapping(value = { "paymentFallBack" })
	public String service1FallBack(){
		
		return "service1FallBack";
	}
	
	@GetMapping(value = { "premiumFallBack" })
	public String service2FallBack(){
		
		return "service1FallBack";
	}
}
