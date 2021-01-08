package com.kgisl.premium.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PremiumController {
	
	/**
	 * URL to render Insurance page.
	 * @return Insurance modal
	 */
	@GetMapping("/")
	public String userChat() {
		
		return "Insurance";
	}

}
