/*******************************************************************************
 * EXCLUSIVE LICENSE
 * THE INFORMATION AND COMPUTER SOURCE CODE CONTAINED WITHIN THIS PROGRAM SCRIPT IS
 * THE EXCLUSIVE PROPERTY OF HAWES TECHNOLOGIES, LLC. USE MUST BE AUTHORIZED UNDER WRITTEN
 * LICENSE OBTAINED FROM HAWES TECHNOLOGIES, LLC. USE AT YOUR OWN RISK. NO WARRANTY EITHER
 * EXPRESSED OR IMPLIED.
 *
 * UNAUTHORIZED USE, ALTERATION, COPYING, OR REDISTRIBUTION IS STRICTLY PROHIBITED.
 *
 *  @copyright Copyright (c) 2017 Hawes Technologies, LLC.
 ******************************************************************************/
package com.codechasers.license.core.session;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Objects;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.codechasers.license.core.configuration.JdbcConnection;
import com.codechasers.license.core.models.UserSessionLog.LogoutType;
import com.codechasers.license.core.util.ActiveUserRepo;
import com.codechasers.license.core.util.ExceptionLogger;



/** Listener class relay on httpsession event like session creation and destroy. By using those event user logout period will be tracked in database
 * 
 * 
 * @author selvam.m
 *
 */
@WebListener
public class SessionListener implements HttpSessionListener {

	private static final Logger logger = LoggerFactory.getLogger(SessionListener.class);
	
	@Override
	public synchronized void sessionDestroyed(HttpSessionEvent se) {
		
		Boolean autoLogout =true;
		
		RequestAttributes reqAttr = RequestContextHolder.getRequestAttributes();
		if(Objects.nonNull(reqAttr)) {
			HttpServletRequest request = ((ServletRequestAttributes) reqAttr).getRequest();
			
			if(request.getSession() !=null && request.getSession().getAttribute("userSessionId")!=null ){
				autoLogout=false;
			}
		}
		
		if(se.getSession().getAttribute("userSessionId")!=null && se.getSession().getAttribute("userSessionId")!=""){
			
			logger.debug(" Destroyed session id - {}",se.getSession().getAttribute("userSessionId"));
			
			HttpSession httpSession = se.getSession();
			
			if(httpSession.getAttribute("id")!=null){
				int userId = Integer.valueOf(httpSession.getAttribute("id").toString());
				ActiveUserRepo.getInstance().addOrRemoveUser(userId, false);
				InvalidateSession.getInstance().moreActiveSession(httpSession.getId(),userId);
				
			}
			try(Connection connection =  JdbcConnection.getJdbcConnection().getDataSource().getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement("update event_user_sessn_log usl "
							+ "	set usl.usl_logout_time = now() , "
							+ " usl_login_active=0 ,"
							+ " usl_logout_type =? "
							+ " where usl.usl_session_id=? "
							+ " and usl_logout_time is null");	
					){
			
				
			
				preparedStatement.setString(1, autoLogout?LogoutType.AUTO.toString():LogoutType.MANUAL.toString());
				preparedStatement.setString(2, se.getSession().getAttribute("userSessionId").toString());
				preparedStatement.execute();
			}
			catch(Exception e){
				new ExceptionLogger(e).logException();
			}
		}
		
	}
	@Override
	public void sessionCreated(HttpSessionEvent se) {
	}
}
