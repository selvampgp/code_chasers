package com.codechasers.license.core.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomEntryPoint extends LoginUrlAuthenticationEntryPoint {

    private static final String XML_HTTP_REQUEST = "XMLHttpRequest";
    private static final String X_REQUESTED_WITH = "X-Requested-With";

  

    public CustomEntryPoint(String loingForm) {
        super(loingForm);
    }
  
    
    public CustomEntryPoint() {
        super("/login");
    }
    
    
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
    	if(request.getRequestURL().toString().contains("/api/")){
    	    PrintWriter writer = response.getWriter();
    	    writer.write("Unauthorised Access");
    	    response.setStatus(401);
    	    writer.close();
    		return;
    	}else if (XML_HTTP_REQUEST.equals(request.getHeader(X_REQUESTED_WITH))) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            super.commence(request, response, exception);
        }
    	
    	
    }

}
