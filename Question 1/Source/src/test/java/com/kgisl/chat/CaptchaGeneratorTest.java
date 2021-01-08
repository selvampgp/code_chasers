package com.kgisl.chat;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.kgisl.security.service.CaptchaGenerator;

import nl.captcha.Captcha;

/**
 * To test CaptchaGenerator class with positive and negative cases
 */
public class CaptchaGeneratorTest {
	
	/* To test negative cases like exceptions */
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	/**
	 * Used to test the captcha generator with fixed height and width.
	 */
	@Test
	public void createCaptchaTest() throws Exception {
		
		CaptchaGenerator captchaGenerator = new CaptchaGenerator();
		captchaGenerator.afterPropertiesSet();
		Captcha captcha = captchaGenerator.createCaptcha(20, 30);
		
		Assert.assertNotNull(captcha);
	}
	
	/**
	 * Used to test the captcha generator without height and width.
	 * Excepted output: Null pointer exception
	 */
	@Test
	public void createCaptchaTestForException() {
		
		CaptchaGenerator captchaGenerator = new CaptchaGenerator();
		
		exceptionRule.expect(NullPointerException.class);
		
		captchaGenerator.createCaptcha(null, null);
	}
	
	/**
	 *  Used to test the captcha generator with actual captcha with text in it.
	 * Excepted output: Catcha & the encoded string should not be null.
	 */
	@Test
	public void encodeBase64Test() throws Exception {
		
		CaptchaGenerator captchaGenerator = new CaptchaGenerator();
		captchaGenerator.afterPropertiesSet();
		Captcha captcha = captchaGenerator.createCaptcha(20, 30);
		Assert.assertNotNull(captcha);
		
		String encodedString = captchaGenerator.encodeBase64(captcha);
		Assert.assertNotNull(encodedString);
	}
	
	/**
	 * Used to test the captcha base 64 encoder.
	 * Excepted output: Null pointer exception
	 */
	@Test
	public void encodeBase64TestForException() throws Exception {
		
		CaptchaGenerator captchaGenerator = new CaptchaGenerator();
		
		exceptionRule.expect(NullPointerException.class);
		
		captchaGenerator.encodeBase64(null);
	}
	
}
