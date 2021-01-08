package com.kgisl.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.util.WebUtils;

import nl.captcha.Captcha;

/**
 * Extending web authentication to adjust security configuration to accept extra
 * parameter verification token
 */
@SuppressWarnings("serial")
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

	private String actualCaptcha;
	private String captchaFromUser;

	/* To get and set extra parameters like captcha before authentication */
	public CustomWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		Captcha captcha = (Captcha) WebUtils.getSessionAttribute(request, "captcha");
		captchaFromUser = request.getParameter("captcha");
		actualCaptcha = captcha.getAnswer();
	}

	public String getActualCaptcha() {
		return actualCaptcha;
	}

	public String getCaptchaFromUser() {
		return captchaFromUser;
	}

}
