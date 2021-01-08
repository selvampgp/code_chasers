
package com.codechasers.license.core.services;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codechasers.license.core.dao.UserDao;
import com.codechasers.license.core.dao.UserSessionLogDao;
import com.codechasers.license.core.util.ExceptionLogger;

/**
 * The Class LimitLoginAuthenticationProvider will restrict the user if he had
 * continued wrong attempts.
 * 
 * 
 * @see UserSessionLogDao
 * @see UserDao
 * @see UserService
 */
@Service
@Transactional(noRollbackFor = { BadCredentialsException.class })

public class LimitLoginAuthenticationProvider extends DaoAuthenticationProvider {

	
	private static final Logger log = LoggerFactory.getLogger(LimitLoginAuthenticationProvider.class);
	
	/** The user session log dao. */
	private UserSessionLogDao userSessionLogDao;



	@Autowired
	HttpSession httpSessio;

	/**
	 * Instantiates a new limit login authentication provider.
	 *
	 * @param userSessionLog
	 *            the user session log
	 * @param userDao
	 *            the user dao
	 */
	@Autowired
	public LimitLoginAuthenticationProvider(UserSessionLogDao userSessionLog, UserDao userDao) {
		this.userSessionLogDao = userSessionLog;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.authentication.dao.DaoAuthenticationProvider
	 * #setUserDetailsService(org.springframework.security.core.userdetails.
	 * UserDetailsService)
	 */
	@Autowired
	@Qualifier("userLoginService")
	@Override
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		super.setUserDetailsService(userDetailsService);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.dao.
	 * AbstractUserDetailsAuthenticationProvider#authenticate(org.
	 * springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication authentication)  {

		String remoteAddress ="";
		boolean login = true;
		
		try {

			Authentication auth = super.authenticate(authentication);
			WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
			remoteAddress = details.getRemoteAddress();
			
			httpSessio.setAttribute("j_username", authentication.getName());
			
			if (remoteAddress.equals("127.0.0.1")) {
				try {
					remoteAddress = InetAddress.getLocalHost().toString();
				} catch (UnknownHostException e) {
					new ExceptionLogger(e).logException();
				}
			}
			login = true;
			return auth;

		}

		catch (BadCredentialsException e) {
			login = false;
			throw e;
		}

		catch (CredentialsExpiredException e) {
			login = false;
			throw new CredentialsExpiredException((String) authentication.getPrincipal() + "$U$N$A$M$E$");

		}
		catch(Exception e){
			login = false;		
			log.error( "{} ip address {}",e.getMessage(), remoteAddress);
			throw e;
		}

		finally {
			
			if (!login) {
					
				//userSessionLogDao.updateLoginFailureAttempts(authentication.getName());
			}

		}

	}


}
