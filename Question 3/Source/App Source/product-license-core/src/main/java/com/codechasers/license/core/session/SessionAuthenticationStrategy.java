package com.codechasers.license.core.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;

import com.codechasers.license.core.models.CustomUser;

/**
 * 
 * @author selvam.m
 *
 */
public class SessionAuthenticationStrategy extends
		ConcurrentSessionControlAuthenticationStrategy {

	private static int maximumSessionPerUser = 1;
	private static boolean exceptionIfMaxSessionExceeded = false;
	private static String supportUser=null;

	private final SessionRegistry sessionRegistry;
	private static final Logger logger = LoggerFactory.getLogger(SessionAuthenticationStrategy.class);
	
	public SessionAuthenticationStrategy(SessionRegistry sessionRegistry) {
		super(sessionRegistry);
		this.sessionRegistry=sessionRegistry;
	}

	// NOSONAR
	@Override
	public void onAuthentication(Authentication authentication,
			HttpServletRequest request, HttpServletResponse response) {
		
		if(request.getParameter("close_previous")!=null && request.getParameter("close_previous").equals("true")){
			super.setExceptionIfMaximumExceeded(false);
		}
		else{
			super.setExceptionIfMaximumExceeded(true);
		}
		
		super.onAuthentication(authentication, request, response);
				
	}

	@Override
	protected int getMaximumSessionsForThisUser(Authentication authentication) {


		int sessionCount = sessionRegistry.getAllSessions(
				authentication.getPrincipal(), false).size();
		
		if (supportUser!=null && ((CustomUser) authentication.getPrincipal()).getUsername()
				.equalsIgnoreCase(supportUser)) {
			
			logger.debug("{} session active session for support user",sessionCount);
			return -1;
		}
		
		
		else if(sessionCount>=maximumSessionPerUser){
			try{
				logger.debug("Active concurrent session of {} is {},it exceeds allowed limit ",((CustomUser) authentication.getPrincipal()).getUsername(),sessionCount);
			}
			catch(Exception e){
				/**Skipping log*/
			}
		}
		
		return maximumSessionPerUser;
	}

	// NOSONAR
	@Override
	public void setExceptionIfMaximumExceeded(boolean exceptionIfMaximumExceeded) {

		exceptionIfMaximumExceeded = SessionAuthenticationStrategy.exceptionIfMaxSessionExceeded;
		super.setExceptionIfMaximumExceeded(exceptionIfMaximumExceeded);
	}

	public static void setExceptionIfMaximumSessionExceeded(
			boolean exceptionIfMaxSessionExceeded) {
		SessionAuthenticationStrategy.exceptionIfMaxSessionExceeded = exceptionIfMaxSessionExceeded;
	}

	public static void setMaximumSessionPerUser(int maximumSessionPerUser) {
		SessionAuthenticationStrategy.maximumSessionPerUser = maximumSessionPerUser;
	}

	

}