package com.codechasers.license.core.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The User class contain basic details of the user like user name,user
 * 
 */
@Entity
@Table(name = "event_user")
public class User implements Serializable {

	/** The Constant serialVersionUID. */
   private static final long serialVersionUID = 5535828929263097950L;

	/** The user id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usr_user_id")
	private Integer userId;

	/** The username. */
	@Column(name = "usr_user_name", unique = true)
	private String username;

	/** The display name. */
	@Column(name = "usr_display_name")
	private String displayName;


	/** The password. */
	@Column(name = "usr_password")
	private String password;

	/** The email. */
	@Column(name = "usr_email")
	private String email;

		/** The lock status. */
	@Column(name = "usr_lock_status")
	private String lockStatus;

	/** The active. */
	@Column(name = "usr_active")
	private String active;

	
	/** The first name. */
	@Column(name = "usr_first_name")
	private String firstName;

	/** The last name. */
	@Column(name = " usr_last_name")
	private String lastName;

	

	/** The gender. */
	@Column(name = "usr_gender")
	private String gender;

	
	/** The dob. */
	@Column(name = "usr_dob")
	private Date dob;

	/** The created date. */
	@Column(name = "usr_create_date")
	private Date createdDate;

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public User setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the lockStatus
	 */
	public String getLockStatus() {
		return lockStatus;
	}

	/**
	 * @param lockStatus the lockStatus to set
	 */
	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}

	/**
	 * @return the active
	 */
	public String getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(String active) {
		this.active = active;
	}

	

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	}


