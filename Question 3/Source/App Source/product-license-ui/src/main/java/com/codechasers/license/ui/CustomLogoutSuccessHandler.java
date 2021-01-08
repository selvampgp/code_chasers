package com.codechasers.license.ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codechasers.license.core.dao.UserDao;

/**
 * The Class CustomLogoutSuccessHandler will handle the logout sessions.
 * 
 * 
 */
@Service
@Transactional
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler 
implements LogoutSuccessHandler {


	/** The user dao. */
	private UserDao userDao;
	
	

	/**
	 * Instantiates a new custom logout success handler.
	 *
	 * @param userSessionLog
	 *            the user session log
	 * @param userDao
	 *            the user dao
	 */
	@Autowired
	public CustomLogoutSuccessHandler(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * OnLogout Success Handler
	 * 
	 */
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication)throws IOException, ServletException {
		
		
		
		response.setStatus(HttpServletResponse.SC_OK);
		response.sendRedirect("login?logout");
	}

}
