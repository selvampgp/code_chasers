package com.codechasers.license.core.util;

import org.springframework.web.servlet.ModelAndView;

public class CustomRestrictedAccessException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8793788212063292972L;
	private String message;
	private String header;
	private ModelAndView model;
	private Integer responseStatus;
	
	public CustomRestrictedAccessException(String message){
		
		super(message);
		this.message=message;
	}
	
	public CustomRestrictedAccessException(String header,String message){
		
		super(message);
		this.message=message;
		this.header=header;
	}
	
	
	public CustomRestrictedAccessException(ModelAndView model,Integer responseStatus){
		
		this.model=model;
		this.responseStatus=responseStatus;
	}
	
	@Override
	public void printStackTrace() {

		super.printStackTrace();
		
	}

	public String getMessage() {
		return message;
	}

	/**
	 * @return the model
	 */
	public ModelAndView getModel() {
		return model;
	}

	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @return the responseStatus
	 */
	public Integer getResponseStatus() {
		return responseStatus;
	}

	
	
}
