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

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;

public class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper
{
	private final byte[] body;

	public CustomHttpServletRequestWrapper ( HttpServletRequest request ) throws IOException
	{
		super(request);
		if (request.getContentType() != null && request.getContentType().contains(MediaType.APPLICATION_JSON.toString()))
		{
			try(InputStream inputStream = request.getInputStream();){
				
				if (inputStream != null)
				{
					body = IOUtils.toByteArray(inputStream);
					return;
				}
			}
			
		}
		body = null;
	}

	@Override
	public ServletInputStream getInputStream () throws IOException
	{
		if (body == null) return super.getInputStream();

		final ByteArrayInputStream stream = new ByteArrayInputStream(body);
		ServletInputStream inputStream = new ServletInputStream()
		{
			@Override
			public int read () throws IOException
			{
				return stream.read();
			}

			public boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}

			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setReadListener(ReadListener readListener) {
				// TODO Auto-generated method stub
				
			}

			
		};
		return inputStream;
	}

	@Override
	public BufferedReader getReader () throws IOException
	{
		if (body == null) return super.getReader();

		return new BufferedReader(new InputStreamReader( new ByteArrayInputStream(body), StandardCharsets.UTF_8));
	}

}
