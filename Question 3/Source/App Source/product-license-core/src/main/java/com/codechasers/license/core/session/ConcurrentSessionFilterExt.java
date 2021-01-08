package com.codechasers.license.core.session;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.util.Assert;

/**<h1>Extended class of {@link ConcurrentSessionFilter} to filter and validate request session.</h1> 
 *  
 * <p>Class used to validate the httpsession against the data stored in {@link SessionRegistry}, 
 *  in case of session marked as expired, it will logout the user session and clear the context</p>  
 * 
 * @see ConcurrentSessionFilter
 * @author selvam.m
 *
 */
public class ConcurrentSessionFilterExt extends ConcurrentSessionFilter{

	
	private SessionRegistry sessionRegistry;
	private LogoutHandler[] handlers = new LogoutHandler[] { new SecurityContextLogoutHandler() };
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	
	public ConcurrentSessionFilterExt(SessionRegistry sessionRegistry) {
		super(sessionRegistry);
		this.sessionRegistry=sessionRegistry;
		
	}
	
	/** Method override to change the response status code as 401, by default parent class method return response with status code as 200
	 * 
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		HttpSession session = request.getSession(false);

		if (session != null) {
			SessionInformation info = sessionRegistry.getSessionInformation(session
					.getId());

			if (info != null) {
				if (info.isExpired()) {
					// Expired - abort processing
					doLogoutExt(request, response);

					String targetUrl = determineExpiredUrl(request, info);

					if (targetUrl != null) {
						redirectStrategy.sendRedirect(request, response, targetUrl);

					}
					else {
						response.getWriter().print(
								"This session has been expired (possibly due to multiple concurrent "
										+ "logins being attempted as the same user).");
						response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
						response.flushBuffer();
					}

					return;
				}
				else {
					// Non-expired - update last request date/time
					sessionRegistry.refreshLastRequest(info.getSessionId());
				}
			}
		}

		chain.doFilter(request, response);
	}
	

	void doLogoutExt(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		for (LogoutHandler handler : handlers) {
			handler.logout(request, response, auth);
		}
	}

	@Override
	public void setLogoutHandlers(LogoutHandler[] handlers) {
		Assert.notNull(handlers);
		this.handlers = handlers;
	}
	
	@Override
	protected String determineExpiredUrl(HttpServletRequest request,
			SessionInformation info) {
		if(request.getRequestURI().contains("/api")){
			return null;
		}
		return "/login";
	}
	
	@Override
	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

}
