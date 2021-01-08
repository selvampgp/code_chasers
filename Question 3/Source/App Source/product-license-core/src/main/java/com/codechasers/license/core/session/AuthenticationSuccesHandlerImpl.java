package com.codechasers.license.core.session;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.codechasers.license.core.models.CustomUser;
import com.codechasers.license.core.models.UserSessionLog;
import com.codechasers.license.core.services.UserSessionLogService;
import com.codechasers.license.core.util.ActiveUserRepo;



/**<h1> Listener class invoked when authentication of user is successful</h1> 
 * 
 * <p> Used to add the unique session identifier to httpsession object and  store the user login session details {@link UserSessionLog} table</p>
 * 
 * @author selvam.m
 *
 */
@Component
public class AuthenticationSuccesHandlerImpl implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

	
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationSuccesHandlerImpl.class);
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private UserSessionLogService userSessionLogService; 
	
	@Override
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent appEvent)
	{
		
		String sessionId = UUID.randomUUID().toString();
	    Integer userId =((CustomUser)appEvent.getAuthentication().getPrincipal()).getUserId();
		
		httpSession.setAttribute("userSessionId",sessionId); 
		ActiveUserRepo.getInstance().addOrRemoveUser(userId, true);
		 
		userSessionLogService.updateLoginAttemppt(userId, 1, ((WebAuthenticationDetails)appEvent.getAuthentication().getDetails()).getRemoteAddress(), sessionId,httpSession.getId(),httpSession.getMaxInactiveInterval());
		 
		logger.info(" User logged in with session id {} and uuid {} ",httpSession.getId(),sessionId);
	}


}