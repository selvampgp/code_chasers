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
package com.codechasers.license.core.services;

import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codechasers.license.core.dao.UserDao;
import com.codechasers.license.core.models.User;

/**
 * The User class contain basic details of the user like user name,user
 * role,user Id, permissions etc.
 * 
 * 
 * 
 * @see UserDao
 * @see RoleRefferenceDao
 * @see UserPermissionDao
 * @see EmployeePhoneNoService
 * @see ServletContext
 */
@Service
@Transactional
public class UserService {

	/** The dao. */
	 UserDao dao;

	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	/**
	 * Instantiates a new user service.
	 *
	 * @param dao
	 *            the dao
	 */
	@Autowired
	public UserService(UserDao dao) {
		this.dao = dao;
	}

	
	public User create(User user){
		return dao.persist(user);
	}
	
	public List<User> fetchAll(){
		return dao.findAll();
	}
	
}
