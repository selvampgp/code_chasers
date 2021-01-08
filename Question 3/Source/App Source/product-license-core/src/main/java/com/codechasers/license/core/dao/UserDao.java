package com.codechasers.license.core.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.codechasers.license.core.models.User;

/**
 * The User class contain basic details of the user like user name,user
 * role,user Id, permissions etc.
 * 
 * 
 */
@SuppressWarnings("unchecked")
@Repository
public class UserDao extends BaseAbstractDao<User> {
	
	
	@Autowired
	public UserDao(SessionFactory sessionFactory){
		super(sessionFactory);
	}
}
