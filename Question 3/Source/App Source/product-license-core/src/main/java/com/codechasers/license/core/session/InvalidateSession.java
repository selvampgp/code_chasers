package com.codechasers.license.core.session;

import java.util.List;

import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;

import com.codechasers.license.core.models.CustomUser;
import com.codechasers.license.core.util.ApplicationContextProvider;

/**<h1>Once the user is deactivated, all the sessions corresponding to the user must be removed or invalidated to ensure no more action allowed will
 *  be performed by that user.</h1>
 * <p>This class used to identify the session of particular user by using {@link SessionRegistry} and mark all active session of the user as expired</p>
 * 
 * @author selvam.m
 *
 */
public class InvalidateSession {

	/** Singleton InvalidateSession object to void creation of mulitple copies
	 *  
	 */
	private static InvalidateSession invalidateSession = null;

	private SessionRegistry registry=null;

	
	private InvalidateSession() {

		ApplicationContextProvider appContext = new ApplicationContextProvider();

		registry = (SessionRegistry) appContext.getApplicationContext()
				.getBean("sessionRegistry");

	}

	/**
	 * @return the invalidateSession object
	 */
	public static InvalidateSession getInstance() {

		if (invalidateSession == null) {
			invalidateSession = new InvalidateSession();
		}

		return invalidateSession;
	}
	
	/** Return the session registry object which hold all active session
	 *  
	 * @return
	 */
	public SessionRegistry getSessionRegistry(){
		return registry;
	}

	/** Used to find the httpsession corresponding to the user from {@link SessionRegistry} and store the session ids in {@link #invalidSessionIds} 
	 *  
	 * @param userName
	 */
	public void invalidateSession(String userName) {

		Object principal = registry.getAllPrincipals().stream().filter(user -> ((CustomUser) user).getUsername().equals(
						userName)).findFirst().orElse(null);

		if (principal != null) {

			List<SessionInformation> sessionInformations = registry.getAllSessions(
					principal, false);

			sessionInformations.stream().forEach(SessionInformation::expireNow);
			

		}

	}
	
	
	/** Used to find the httpsession corresponding to the session id 
	 *  
	 * @param userName
	 */
	public void invalidateSessionById(String sessionId) {

		
		registry.getSessionInformation(sessionId).expireNow();


	}

	public boolean moreActiveSession(String sessionId,Integer userId){
		
			
			Object principal = registry.getAllPrincipals().stream().
					filter(user -> ((CustomUser) user).getUserId().equals(
							userId)).findFirst().orElse(null);
			
			
			if (principal != null) {
				
				List<SessionInformation> sessionInformations = registry.getAllSessions(
						principal, false);
				
				return sessionInformations.stream()
						.filter(sessionInfo->!sessionInfo.getSessionId().equals(sessionId))
						.count()>0;
						
						
			}
		
		
		
		return false;
	}
	

}
