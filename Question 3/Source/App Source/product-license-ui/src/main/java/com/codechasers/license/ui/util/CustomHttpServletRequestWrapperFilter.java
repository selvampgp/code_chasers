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
package com.codechasers.license.ui.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomHttpServletRequestWrapperFilter implements Filter
{
	
	private static final Logger logger = LoggerFactory.getLogger(CustomHttpServletRequestWrapperFilter.class);
	
	public void init ( FilterConfig fc ) throws ServletException
	{
	}

	public void doFilter (
			ServletRequest request,
			ServletResponse response,
			FilterChain chain ) throws IOException,	ServletException
	{
		
		
		chain.doFilter(new CustomHttpServletRequestWrapper((HttpServletRequest) request), response);
		
		//String url = ((HttpServletRequest) request).getPathInfo();
		
		//if(url.contains("api"))
		//	logger.debug("Api url requested - "+url);
		
	}

	public void destroy ()
	{
	}
}
