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
package com.codechasers.license.core.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.codechasers.license.core.models.User;


/**
 * The UserPermission class has the details of permissions assigned to the user
 * and its descriptions.
 * 
 * 
 */
@Repository
public class UserLoginDao  {

	@Autowired
	SessionFactory factory;

	
	/**
	 * Find by user name.
	 *
	 * @param username
	 *            the username
	 * @return the user
	 */
	public User findByUserName(String username) {

	return 	 (User) factory.getCurrentSession().createCriteria(User.class)
				.setProjection(Projections.projectionList()
						.add(Projections.property("username").as("username"))
						.add(Projections.property("password").as("password"))
						.add(Projections.property("userId").as("userId"))
						.add(Projections.property("active").as("active"))
						.add(Projections.property("lockStatus").as("lockStatus"))
						
						)
		.add(Restrictions.eq("username", username))
		.setResultTransformer(Transformers.aliasToBean(User.class))
		.uniqueResult();


	}

	
	
}
