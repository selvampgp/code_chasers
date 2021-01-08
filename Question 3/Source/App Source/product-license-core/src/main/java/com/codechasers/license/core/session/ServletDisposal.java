package com.codechasers.license.core.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codechasers.license.core.services.UserSessionLogService;

/** <h1> Bean listener invoked when the system beans are destroyed or application is stopping </h1>
 * 
 * @author selvam.m
 *
 */
@Component
public class ServletDisposal implements DisposableBean {
 
	
	@Autowired
	UserSessionLogService userSessionLogService;
	
	private static final Logger logger = LoggerFactory.getLogger(ServletDisposal.class);
	
    @Override
    public void destroy() throws Exception {
    	
    	System.out.println("Triggered application disposable. please wait for application saving session details..!");
    	logger.info("Triggered application disposable. please wait for application saving session details..!");
    	
    	System.out.println("If it takes longer than usual due to database queue , please kill the process..");
    	
    	logger.debug("Logged in user status updated - {} ", userSessionLogService.updateLogoutStatusOfAllActiveUsers()); 
    	    	
    }
}