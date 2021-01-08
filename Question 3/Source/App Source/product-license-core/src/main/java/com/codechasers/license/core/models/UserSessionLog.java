package com.codechasers.license.core.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * The Login & Logout details session details are maintained in this
 * UserSessionLog class.
 * 
 */
@Entity
@Table(name = "event_user_sessn_log")
public class UserSessionLog {

	/** The usl id. */
	@Id
	@Column(name = "usl_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uslId;

	/** The usluser id. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usl_user_id", referencedColumnName = "usr_user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private User usluserId;

	/** The usl user id. */
	@Column(name = "usl_user_id", insertable = false, updatable = false)
	private Integer uslUserId;

	/** The usllogin attempts. */
	@Column(name = "usl_login_flag")
	private Integer uslloginAttempts;

	/** The usllogin time. */
	@Column(name = "usl_login_time")
	private Date uslloginTime;
	
	@Formula("date_format(usl_login_time ,'%m/%d/%Y %H:%i:%s')")
	private String uslloginTimeFormatted;

	/** The usllogout time. */
	@Column(name = "usl_logout_time")
	private Date usllogoutTime;

	/** The uslsys code. */
	@Column(name = "usl_sys_code")
	private Integer uslsysCode;

	/** The system ip. */
	@Column(name = "usl_system_ip")
	private String systemIp;

	/** The login active. */
	@Column(name = "usl_login_active")
	private Integer loginActive;

	/** The session id. */
	@Column(name = "usl_session_id")
	private String sessionId;
	
	@Column(name = "usl_auto_session_id")
	private String autoSessionId;
	
	
	/** The browser details**/
	@Column(name="usl_browser_details")
	private String browserDetails;

	
	/**The logout type**/
	@Column(name="usl_logout_type",columnDefinition="ENUM('MANUAL','AUTO','STOP','START')")
	@Enumerated(EnumType.STRING)
	private LogoutType logoutType;
	
	
	@Column(name="usl_max_inactive_interval")
	private Integer maxInactiveTime;
	
	@Column(name="usl_device_type")
	private String deviceType;
	
	
	@Column(name="usl_device_os")
	private String deviceOs;
	
	
	
	public enum LogoutType{
		MANUAL,AUTO,STOP,START;
	}
	
	@Transient
	private String username;
	
	@Transient
	private String lastRequest;
	
	/**
	 * Gets the system ip.
	 *
	 * @return the system ip
	 */
	public String getSystemIp() {
		return systemIp;
	}

	/**
	 * Sets the system ip.
	 *
	 * @param systemIp
	 *            the new system ip
	 */
	public void setSystemIp(String systemIp) {
		this.systemIp = systemIp;
	}

	/**
	 * Gets the usl id.
	 *
	 * @return the usl id
	 */
	public Integer getUslId() {
		return uslId;
	}

	/**
	 * Sets the usl id.
	 *
	 * @param uslId
	 *            the new usl id
	 */
	public void setUslId(Integer uslId) {
		this.uslId = uslId;
	}

	/**
	 * Gets the usluser id.
	 *
	 * @return the usluser id
	 */
	public User getUsluserId() {
		return usluserId;
	}

	/**
	 * Sets the usluser id.
	 *
	 * @param usluserId
	 *            the new usluser id
	 */
	public void setUsluserId(User usluserId) {
		this.usluserId = usluserId;
	}

	/**
	 * Gets the usl user id.
	 *
	 * @return the usl user id
	 */
	public Integer getUslUserId() {
		return uslUserId;
	}

	/**
	 * Sets the usl user id.
	 *
	 * @param uslUserId
	 *            the new usl user id
	 */
	public void setUslUserId(Integer uslUserId) {
		this.uslUserId = uslUserId;
	}

	/**
	 * Gets the usllogin attempts.
	 *
	 * @return the usllogin attempts
	 */
	public Integer getUslloginAttempts() {
		return uslloginAttempts;
	}

	/**
	 * Sets the usllogin attempts.
	 *
	 * @param uslloginAttempts
	 *            the new usllogin attempts
	 */
	public void setUslloginAttempts(Integer uslloginAttempts) {
		this.uslloginAttempts = uslloginAttempts;
	}

	/**
	 * Gets the usllogin time.
	 *
	 * @return the usllogin time
	 */
	public Date getUslloginTime() {
		return uslloginTime;
	}

	/**
	 * Sets the usllogin time.
	 *
	 * @param uslloginTime
	 *            the new usllogin time
	 */
	public void setUslloginTime(Date uslloginTime) {
		this.uslloginTime = uslloginTime;
	}

	/**
	 * Gets the usllogout time.
	 *
	 * @return the usllogout time
	 */
	public Date getUsllogoutTime() {
		return usllogoutTime;
	}

	/**
	 * Sets the usllogout time.
	 *
	 * @param usllogoutTime
	 *            the new usllogout time
	 */
	public void setUsllogoutTime(Date usllogoutTime) {
		this.usllogoutTime = usllogoutTime;
	}

	/**
	 * Gets the uslsys code.
	 *
	 * @return the uslsys code
	 */
	public Integer getUslsysCode() {
		return uslsysCode;
	}

	/**
	 * Sets the uslsys code.
	 *
	 * @param uslsysCode
	 *            the new uslsys code
	 */
	public void setUslsysCode(Integer uslsysCode) {
		this.uslsysCode = uslsysCode;
	}

	/**
	 * Gets the login active.
	 *
	 * @return the login active
	 */
	public Integer getLoginActive() {
		return loginActive;
	}

	/**
	 * Sets the login active.
	 *
	 * @param loginActive
	 *            the new login active
	 */
	public void setLoginActive(Integer loginActive) {
		this.loginActive = loginActive;
	}

	/**
	 * Gets the session id.
	 *
	 * @return the session id
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * Sets the session id.
	 *
	 * @param sessionId
	 *            the new session id
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @return the browserDetails
	 */
	public String getBrowserDetails() {
		return browserDetails;
	}

	/**
	 * @param browserDetails the browserDetails to set
	 */
	public void setBrowserDetails(String browserDetails) {
		this.browserDetails = browserDetails;
	}

	/**
	 * @return the logoutType
	 */
	public LogoutType getLogoutType() {
		return logoutType;
	}

	/**
	 * @param logoutType the logoutType to set
	 */
	public void setLogoutType(LogoutType logoutType) {
		this.logoutType = logoutType;
	}

	/**
	 * @return the maxInactiveTime
	 */
	public Integer getMaxInactiveTime() {
		return maxInactiveTime;
	}

	/**
	 * @param maxInactiveTime the maxInactiveTime to set
	 */
	public void setMaxInactiveTime(Integer maxInactiveTime) {
		this.maxInactiveTime = maxInactiveTime;
	}

	/**
	 * @return the autoSessionId
	 */
	public String getAutoSessionId() {
		return autoSessionId;
	}

	/**
	 * @param autoSessionId the autoSessionId to set
	 */
	public void setAutoSessionId(String autoSessionId) {
		this.autoSessionId = autoSessionId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUslloginTimeFormatted() {
		return uslloginTimeFormatted;
	}

	public void setUslloginTimeFormatted(String uslloginTimeFormatted) {
		this.uslloginTimeFormatted = uslloginTimeFormatted;
	}

	public String getLastRequest() {
		return lastRequest;
	}

	public void setLastRequest(String lastRequest) {
		this.lastRequest = lastRequest;
	}

	/**
	 * @return the deviceType
	 */
	public String getDeviceType() {
		return deviceType;
	}

	/**
	 * @param deviceType the deviceType to set
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	/**
	 * @return the deviceOs
	 */
	public String getDeviceOs() {
		return deviceOs;
	}

	/**
	 * @param deviceOs the deviceOs to set
	 */
	public void setDeviceOs(String deviceOs) {
		this.deviceOs = deviceOs;
	}

	
}
