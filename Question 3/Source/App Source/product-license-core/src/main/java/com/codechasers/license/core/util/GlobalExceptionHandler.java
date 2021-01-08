
package com.codechasers.license.core.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import eu.bitwalker.useragentutils.UserAgent;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	private static final Logger logger = LoggerFactory
			.getLogger(GlobalExceptionHandler.class);	
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public void handleNoHandlerException(HttpServletRequest httpServletRequest,HttpServletResponse response){	
		response.setStatus(404);
	}


	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(value=HttpStatus.FORBIDDEN)
	public ModelAndView handleAccessDeniedException(HttpServletRequest httpServletRequest,HttpServletResponse response){	
		response.setStatus(HttpStatus.FORBIDDEN.value());
		
		
		 ModelAndView model = new ModelAndView();

		
		System.out.println(httpServletRequest.getRequestURI());
		
		if(httpServletRequest.getParameter("inQueue")!=null ){
			
			if(httpServletRequest.getRequestURI().contains("legal")){
				
				model.addObject("isLmsQueue",true);
			}
			else{
				model.addObject("isCollectorQueue",true);
			}
		}
		
		
	    model.setViewName("access-restricted");
		
		return model;
	}
	
	@ExceptionHandler(CustomRestrictedAccessException.class)
	@ResponseStatus(value=HttpStatus.FORBIDDEN)
	public ModelAndView handleCustomRestrictedAccessException(HttpServletRequest httpServletRequest,
			CustomRestrictedAccessException exception ,HttpServletResponse response){	
		
		 ModelAndView model = new ModelAndView();

		
		 if(exception.getModel()!=null){
			 model=exception.getModel();
		 }
		 
		 if(exception.getResponseStatus()!=null){
			 response.setStatus(exception.getResponseStatus());
		 }
		 else{
			 response.setStatus(HttpStatus.FORBIDDEN.value());
		 }

		 model.setViewName("access-restricted");
		 
		
         return model;
	}
	
	@ExceptionHandler(SQLException.class)
	@ResponseBody
	public ResponseEntity<Object> handleSQLException(HttpServletRequest request,
			Exception exception, HttpServletResponse response) {
		response.setStatus(500);
		logger.error("SQLException Occured:: URL=" + request.getRequestURL());
		
		Map<String, Object> map = createException(request, exception,null);
		
		return new ResponseEntity<Object>(map,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException occured")
	@ExceptionHandler(IOException.class)
	public void handleIOException(HttpServletResponse response) {
		logger.error("An IOException has occurred.");
		response.setStatus(500);
	}

	@ExceptionHandler({RuntimeException.class, NullPointerException.class})
	@ResponseBody
	public ResponseEntity<Object> handleError2(HttpServletRequest request,
			Exception exception, HttpServletResponse response) {
		response.setStatus(500);
		Map<String, Object> map = createException(request, exception,null);

		return new ResponseEntity<Object>(map,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<Object> handleError(HttpServletRequest request,
			Exception exception, HttpServletResponse response) {
		response.setStatus(500);
		Map<String, Object> map = createException(request, exception,null);
		
		return  new ResponseEntity<Object>(map,	HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(HibernateException.class)
	@ResponseBody
	public ResponseEntity<Object> handleError1(HttpServletRequest request,
			HibernateException exception, HttpServletResponse response) {
		response.setStatus(500);
		Map<String, Object> map =  createException(request, exception,null);
		
		return new ResponseEntity<Object>(map,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	

	@ExceptionHandler(ExceptionLogger.class)
	@ResponseBody
	public ResponseEntity<Object> exceptionLogger(ExceptionLogger exception){
		
		RequestAttributes reqAttr = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = null;
		if(Objects.nonNull(reqAttr)) {
			request = ((ServletRequestAttributes) reqAttr).getRequest();
		}
		
		this.createException(request, exception.getException(),exception.getClientMessage());
		
		ResponseEntity<Object> responseEntity;
		
		if(exception.getClientMessage()!=null)
			responseEntity = new ResponseEntity<Object>(exception.getClientMessage(),
				HttpStatus.OK);
		else
			responseEntity = new ResponseEntity<Object>("Internal Exception",
					HttpStatus.INTERNAL_SERVER_ERROR);
		
		
		return responseEntity;
	}
	
	public void exceptionLogger(Exception exception,Object clientMessage){
		RequestAttributes reqAttr = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = null;
		if(Objects.nonNull(reqAttr)) {
			request = ((ServletRequestAttributes) reqAttr).getRequest();
		}
		this.createException(request, exception,clientMessage);
		
	}
	
	public Map<String, Object> createException(HttpServletRequest request,
			Exception exception,Object clientMessage) {
		
		Map<String, Object> errorMsg = new HashMap<>();
		
		String className = "";
		String methodName = "";
		String trace = "";
		String localizedExceptionMessage = "";
		String exceptionMessage = "";
		String exceptionCauseMessage = "";
		String referenceUrl = "";
		String browserDetails = "";
		String remoteAddress = "";
		Integer lineNumber = 0;
		StackTraceElement[] stackTraceElement = null;
		Throwable throwable=null;

		/*Get exception details*/
		if(exception!=null){
			/*Get Error Trace details*/
    		StringWriter err = new StringWriter();
    		
    		exception.printStackTrace(new PrintWriter(err));
    		
    		trace=err.toString();
			
    		/*Get classname,methodname,line number*/
    		stackTraceElement=exception.getStackTrace();
    		for (StackTraceElement st : stackTraceElement) {
    
    			if (st.getClassName().indexOf("kgfsl") != -1 ) {
    
    				className = st.getClassName();
    				methodName = st.getMethodName();
    				lineNumber = st.getLineNumber();
    				break;
    			}
    			
    		}
            /*Get exception message*/
    		localizedExceptionMessage = exception.getLocalizedMessage();
    		exceptionMessage =exception.getMessage();
    		if(exception.getCause()!=null){
    			exceptionCauseMessage=exception.getCause().getMessage();
    		}
    		if(Objects.nonNull(exceptionCauseMessage) && !exceptionCauseMessage.isEmpty()) {
    			exceptionCauseMessage = exception.toString();
    		}
    		throwable=exception.getCause();
		}
		
		/*Get request detail*/
		if(request!=null){
			remoteAddress=request.getRemoteAddr();
    		referenceUrl = request.getRequestURL().toString();
    
    		UserAgent userAgent = UserAgent
    				.parseUserAgentString(request.getHeader("User-Agent"));
            if(userAgent!=null){
        		browserDetails = userAgent.getBrowser().getName() + "/"
        				+ userAgent.getBrowserVersion();
            }
		}
		
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat(
				"E MM/dd/yyyy 'at' HH:mm:ss zzz");
        /*Get user name*/
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String name = (auth!=null? auth.getName():"");
		errorMsg.put("userName", name);
		errorMsg.put("exceptionMessage", localizedExceptionMessage);
		errorMsg.put("referenceUrl", referenceUrl);
		errorMsg.put("className", className);
		errorMsg.put("methodName", methodName);
		errorMsg.put("lineNumber", lineNumber);
		errorMsg.put("dateandtime", ft.format(dNow));
		errorMsg.put("browserDetails", browserDetails);
		errorMsg.put("ip", remoteAddress);
		errorMsg.put("stackTrace",trace);
		errorMsg.put("ErrorMessage", exceptionMessage);
		errorMsg.put("Cause", exceptionCauseMessage);
	
		
	
		/*logger.error("\nException: {}"
				+ "\n Log Reference Number : {}"
				+ "\n exceptionMessage : {}"
				+ "\n clientMessage : {}"
				+ "\n referenceUrl : {}" 
				+ "\n className : {}"  
				+ "\n methodName : {}"
				+ "\n lineNumber : {}"  
				+ "\n Exception : {}"
				+ "\n Stack Trace : \n{}", 
					exceptionCauseMessage,
					((errorMsg.get("logReference") != null)? errorMsg.get("logReference"): 0),
					exceptionMessage,
					clientMessage,
					referenceUrl,
					className,
					methodName,
					lineNumber,
					throwable,
					trace
					);*/

		errorMsg.put("stackTrace","");
		errorMsg.put("className", "");
		return errorMsg;
	}

	
	

	private Integer insertIntoH2(Map<String, Object> parameters) {
		return null;
	}

	

}
