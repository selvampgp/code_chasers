package com.codechasers.license.core.util;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Component
/**
 * 
 * Custom exception class used to log the exception in log file
 *
 */
public class ExceptionLogger extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Exception exception;

	private Object clientMessage;

	private static final Logger logger = LoggerFactory
			.getLogger(ExceptionLogger.class);
	

	private GlobalExceptionHandler exceptionHandler;
	
	ApplicationContextProvider appContext = new ApplicationContextProvider();
	/**
	 * This Class used to log and trigger exception mail while catching
	 * exception
	 * 
	 * @param exception
	 *            This is the Exception class object in catch block
	 * @param clientMessage
	 *            This is the customized or return value object which will shown
	 *            in client screen
	 * 
	 */
	public ExceptionLogger(Exception exception, Object clientMessage) {

		this.exception = exception;
		this.clientMessage = clientMessage;
		
		
		this.exceptionHandler = appContext.getApplicationContext().getBean("globalExceptionHandler", GlobalExceptionHandler.class);
	}

	public ExceptionLogger(Exception exception) {

		this.exception=exception;
		if(Objects.nonNull(appContext) && Objects.nonNull(appContext.getApplicationContext())) {
			this.exceptionHandler = appContext.getApplicationContext().getBean("globalExceptionHandler", GlobalExceptionHandler.class);
		}
		
	}
	
	public ExceptionLogger(){
		this.exceptionHandler = appContext.getApplicationContext().getBean("globalExceptionHandler", GlobalExceptionHandler.class);
	}
	
	public Exception getException() {
		return exception;
	}
	
	public Object getClientMessage() {
		return clientMessage;
	}
	
	public void logException(){
		if(Objects.nonNull(exceptionHandler)) {
			exceptionHandler.exceptionLogger(exception,clientMessage);
		}
	}
	
	@Override
	public void printStackTrace() {
		this.logException();
	}

	@Override
	public void printStackTrace(PrintStream arg0) {
		super.printStackTrace(arg0);
	}

	@Override
	public void printStackTrace(PrintWriter arg0) {

		super.printStackTrace(arg0);
		
	}
	
	

}
