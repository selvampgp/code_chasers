package com.codechasers.license.core.models;

import java.util.Collections;

/**
 * The Class CustomUser has the details about the default user and roles and
 * permissions required for that user.
 * 
 * @author tamilarasan.ramamoorthy
 */
public class CustomUser extends org.springframework.security.core.userdetails.User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3661329151662786326L;

	/**
	 * Instantiates a new custom user.
	 *
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @param enabled
	 *            the enabled
	 * @param accountNonExpired
	 *            the account non expired
	 * @param credentialsNonExpired
	 *            the credentials non expired
	 * @param accountNonLocked
	 *            the account non locked
	 
	 * @param userId
	 *            the user id
	 */
	public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, 
			Integer userId) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, Collections.emptyList());
		this.userId = userId;
		
	}

	/** The user id. */
	private Integer userId;

	
	
	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId
	 *            the new user id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	

}
