package com.codechasers.license.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codechasers.license.core.dao.UserSessionLogDao;
import com.codechasers.license.core.models.UserSessionLog;

/**
 * The Login & Logout details session details are maintained in this
 * UserSessionLog class.
 * 
 * 
 * 
 * @see UserSessionLogDao
 */
@Service
@Transactional
public class UserSessionLogService {

	/** The user session log dao. */
	@Autowired
	public UserSessionLogDao userSessionLogDao;

	/**
	 * this method Gets the user logout.
	 *
	 * @param userId
	 *            the user id
	 * @return the logout
	 */
	public String getLogout(Integer userId) {
		return userSessionLogDao.getLogout(userId);
	}

	/**
	 * this method Gets the user login details.
	 *
	 * @param id
	 *            the user id
	 * @return the user login details
	 */
	public List<UserSessionLog> getUserLoginDetails(Integer id) {
		return userSessionLogDao.getUserLoginDetails(id);
	}

	/**
	 * this method update and save user session.
	 *
	 * @param userId
	 *            the user id
	 * @param activeFlag
	 *            the active flag
	 */
	public Integer saveUserSession(Integer userId, Integer activeFlag) {
		return userSessionLogDao.updateActiveLoginFlag(userId, activeFlag);
	}

	/**
	 * this method will Update the logout success attempts.
	 *
	 * @param userid
	 *            the userid
	 */
	public Integer updateLogoutSuccessAttempts(Integer userId) {
		return userSessionLogDao.updateLogoutSuccessAttempts(userId);
	}

	public Integer updateLogoutStatusOfAllActiveUsers() {
		
		return userSessionLogDao.updateLogoutStatusOfAllActiveUsers();
	}

	/**
	 * Update logout on application init.
	 */
	public Integer updateLogoutOnApplicationInit() {
		return userSessionLogDao.updateLogoutOnApplicationInit();
	}
	

	/**
	 * this will Update the number of login attempts made by the user.
	 *
	 * @param userId
	 *            the id
	 * @param loginAttempt
	 *            the sysid
	 * @param remoteAddress
	 *            the remote address
	 */
	public void updateLoginAttemppt(Integer userId, int loginAttempt, String remoteAddress,String sessionId,String autoSessionId,Integer maxInactiveTime) {
		
		userSessionLogDao.updateLoginAttemppt(userId,loginAttempt,remoteAddress,sessionId,autoSessionId,maxInactiveTime);
	}

	public List<UserSessionLog> getAllActiveSessions() {
		return userSessionLogDao.getAllActiveSessions();
	}

}
