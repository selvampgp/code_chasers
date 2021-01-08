package com.codechasers.license.rest.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;

@RestController
public class RandomDataRestController {

	
	Faker faker = new Faker();
	
	@RequestMapping(value="/api/address",method=RequestMethod.GET)
	public Map<String,Object> getRandomAddress(){
		
		Map<String,Object> address= new HashMap<String, Object>();
		
		
		Address fAddress = faker.address();
		
		address.put("buildingNumber", fAddress.buildingNumber());
		address.put("city", fAddress.city());
		address.put("cityName", fAddress.cityName());
		address.put("secondaryAddress", fAddress.secondaryAddress());
		address.put("fullAddress", fAddress.fullAddress());
		address.put("firstName", fAddress.firstName());
		address.put("lastName", fAddress.lastName());
		address.put("state", fAddress.state());
		address.put("country", fAddress.country());
		address.put("streetName", fAddress.streetName());
		address.put("timeZone", fAddress.timeZone());
		
		
		
		return address;
	}
}
