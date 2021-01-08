package com.kgisl.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

	/**
	 * Anonymous API to test BOT hit
	 * 
	 * @return boolean true
	 */
	@GetMapping(value = { "/status" })
	public ResponseEntity<Boolean> getStatus(){
		
		return ResponseEntity.status(HttpStatus.OK).body(true);
	}
}
