package com.codechasers.license.core.session;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.util.AntPathMatcher;

import com.codechasers.license.core.models.CustomUser;

/**<h1>Extended class of {@link RegisterSessionAuthenticationStrategy}, used for registering a user session in {@link SessionRegistry} bean.</h1>
 * 
 * <p>In this class,  onAuthentication method is overrided to control session registration based on the request URI.
 * If session is created for API which are listed in skip-session-registry.json file then session will not be registered</p>  
 * 
 * @author selvam.m
 *
 */
public class RegisterSessionAuthenticationStrategyExt
		extends
		org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy {

	private static final Logger logger = LoggerFactory.getLogger(RegisterSessionAuthenticationStrategyExt.class);
	
	public RegisterSessionAuthenticationStrategyExt(SessionRegistry sessionRegistry) {
		super(sessionRegistry);

	}

	@Override
	public void onAuthentication(Authentication authentication,
			HttpServletRequest request, HttpServletResponse response) {

		request.getSession().removeAttribute("j_username");
		String uri = request.getRequestURI();

		if( !uri.contains("api") || 
				isApiExcluedFromSessionRegistry(uri.substring(uri.indexOf("/api"),uri.length()),authentication,request)){
						
			super.onAuthentication(authentication, request, response);
			try{
				logger.debug(" Session id "+request.getSession().getId() +" is registered for user "+((CustomUser) authentication.getPrincipal()).getUsername()+" by "+uri);
			}
			catch(Exception e){
				/**Skipping the log*/
			}
		}

	}
	
	private  boolean isApiExcluedFromSessionRegistry(String uri,Authentication authentication,HttpServletRequest request){
		
		SkipSessionRegistrationUriList sessionRegistrationUriList = SkipSessionRegistrationUriList.getInstance();
		
		for (String uriPattern : sessionRegistrationUriList.getSkipUris()) {
			
			if(new AntPathMatcher().match(uriPattern, uri)){
						
				try{
					logger.debug("uri - "+uri+" is matched with session-less api pattern, logged user "+((CustomUser) authentication.getPrincipal()).getUsername()+" and session id "+request.getSession().getId());
				}
				catch(Exception e){
					/**Skipping the log*/
				}
				return false;
			}
		}
		return true;
	}

}
