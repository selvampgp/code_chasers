package com.kgisl.security.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kgisl.security.service.CaptchaGenerator;

import nl.captcha.Captcha;

@Controller
public class FormController {
	
	/**
	 * URL to render login page.
	 * 
	 * It will generate captcha image and convert it into base64 to render the image in view page.
	 * 
	 * It will set the captcha to session attribute to validate it on submitting form.
	 * 
	 * @param session - HTTP session
	 * @param model - Modal view
	 * @return login modal with captcha image as byte code
	 * @throws Exception
	 */
	@GetMapping("/")
	public String getLogin(HttpSession session, Model model) throws Exception {
		
		// Generate captcha
		CaptchaGenerator captchaGenerator=new CaptchaGenerator();
		captchaGenerator.afterPropertiesSet();
		
		// create captcha with 220 width and 40 height
		Captcha captcha = captchaGenerator.createCaptcha(220,40);
		
		// encode the captcha into base64 byte code
		String imageDataString=captchaGenerator.encodeBase64(captcha);
		
		session.setAttribute("captcha", captcha);
		
		model.addAttribute("captcha", imageDataString);
		
		return "login";
	}
	
	/**
	 * To render home after login success.
	 * @return home view page.
	 */
	@GetMapping("/home")
	public String getHome() {
		
		return "home";
	}
}
