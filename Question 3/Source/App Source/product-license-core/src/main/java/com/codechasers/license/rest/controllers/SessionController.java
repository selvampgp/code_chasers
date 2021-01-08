package com.codechasers.license.rest.controllers;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codechasers.license.core.models.CustomUser;
import com.codechasers.license.core.models.UserSessionLog;
import com.codechasers.license.core.services.UserSessionLogService;
import com.codechasers.license.core.session.InvalidateSession;
import com.codechasers.license.core.util.ApplicationContextProvider;

@Controller
@RequestMapping("/")
public class SessionController {

	
	SessionRegistry sessionRegistry;
	
	@Autowired
	UserSessionLogService userSessionLogService;
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@RequestMapping(value="sessionRegistry",method=RequestMethod.GET)
	public String sessionRegistry(ModelMap modelMap){
		
		
		return "sessionRegistry";
	}
	
	ApplicationContextProvider appContext = new ApplicationContextProvider();
	
	@RequestMapping(value="/api/sessionRegistry",method=RequestMethod.GET)
	public @ResponseBody List<UserSessionLog> sessionRegistryData(){
		

		sessionRegistry = (SessionRegistry) appContext.getApplicationContext()
				.getBean("sessionRegistry");

		
		List<UserSessionLog>sessionLogs = userSessionLogService.getAllActiveSessions();
		
		List<CustomUser> principals = sessionRegistry.getAllPrincipals().stream().map(principal->(CustomUser)principal).collect(Collectors.toList());
		
		for (CustomUser principal : principals) {
			List<SessionInformation> sessionInformations = sessionRegistry.getAllSessions(principal, false);
			
			for (SessionInformation sessionInformation : sessionInformations) {
				
				sessionLogs.stream().filter(usl->usl.getAutoSessionId()!=null && usl.getAutoSessionId().equals(sessionInformation.getSessionId()))
									.forEach(usl->
										usl.setLastRequest(simpleDateFormat.format(sessionInformation.getLastRequest()))
										);
			}
		}
				
		
		return sessionLogs;
		
	}
	
	@RequestMapping(value="/api/sessionRegistry/{sessionId}",method=RequestMethod.DELETE)
	public @ResponseBody void invalidateSelectedSession(@PathVariable("sessionId") String sessionId){
		System.out.println("Delete");
		InvalidateSession.getInstance().invalidateSessionById(sessionId);
	}
}
