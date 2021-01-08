/*******************************************************************************
 * EXCLUSIVE LICENSE
 * THE INFORMATION AND COMPUTER SOURCE CODE CONTAINED WITHIN THIS PROGRAM SCRIPT IS
 * THE EXCLUSIVE PROPERTY OF HAWES TECHNOLOGIES, LLC. USE MUST BE AUTHORIZED UNDER WRITTEN
 * LICENSE OBTAINED FROM HAWES TECHNOLOGIES, LLC. USE AT YOUR OWN RISK. NO WARRANTY EITHER
 * EXPRESSED OR IMPLIED.
 * 
 * UNAUTHORIZED USE, ALTERATION, COPYING, OR REDISTRIBUTION IS STRICTLY PROHIBITED.
 * 
 *  @copyright Copyright (c) 2017 Hawes Technologies, LLC.
 ******************************************************************************/
package com.codechasers.license.ui;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HTTPErrorHandler {

	@RequestMapping(value = "/404",method=RequestMethod.GET)
	public String error404() {
		return "404";
	}

}
