package com.kgisl.ocr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OcrController {
	
	@GetMapping("/")
	public String userChat() {
		
		return "user";
	}
	
}
