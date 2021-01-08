package com.codechasers.license.core.dao;

import static org.hibernate.criterion.Restrictions.eq;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.codechasers.license.core.models.User;
import com.codechasers.license.core.models.UserSessionLog;
import com.codechasers.license.core.models.UserSessionLog.LogoutType;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * The Class UserSessionLogDao.
 * 
 * 
 * 
 */
@Repository
public class UserSessionLogDao extends BaseAbstractDao<UserSessionLog> {


	/** The user dao. */
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserLoginDao userLoginDao;

	
	/**
	 * Instantiates a new user session log dao.
	 *
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public UserSessionLogDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}


	/**
	 * this method will Update the logout success attempts.
	 *
	 * @param userid
	 *            the userid
	 * @param sessionId
	 *            the session id
	 */
	public Integer updateLogoutSuccessAttempts(Integer userId) {
		
		
		super.currentSession().createQuery("DELETE FROM ProgressingCollectorTask WHERE collectorId =" + userId)
				.executeUpdate();
		
		return super.currentSession()
		.createQuery("UPDATE UserSessionLog usl SET usllogoutTime =:currentTime ,loginActive =0 WHERE uslUserId = :userId AND uslloginAttempts=1 AND loginActive=1 AND usllogoutTime is null ")
		.setParameter("userId", userId)
		.setTimestamp("currentTime",new Date())
		.executeUpdate();
	}


	/**
	 * used to Get the number of user attempts.
	 *
	 * @param username
	 *            the username
	 * @return the user attempts
	 */
	public int getUserAttempts(String username) {
		List<?> query = super.currentSession()
				.createSQLQuery(
						"select count(*) from usl_user_sessn_log l where l.usl_login_time > (select max(usl_login_time) from usl_user_sessn_log where usl_user_id =(select usr_user_id from usr_user where usr_user_name=:username) and usl_login_flag = 1) and l.usl_user_id =(select usr_user_id from usr_user where usr_user_name=:username  AND usr_is_lock_on_failure_attempts = 1);")
				.setParameter("username", username).list();
		String getcount = query.get(0).toString();
		int count = Integer.parseInt(getcount);

		return count;

	}



	/**
	 * this method Gets the user logout.
	 *
	 * @param userId
	 *            the user id
	 * @return the logout
	 */
	public String getLogout(Integer userId) {

		UserSessionLog userSessionLog = (UserSessionLog) criteria().setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.setProjection(Projections.projectionList().add(Projections.property("uslloginTime").as("uslloginTime")))
				.addOrder(Order.desc("uslloginTime"))
				.setFirstResult(1).setMaxResults(1)
				.add(eq("uslUserId", userId))
				.setResultTransformer(Transformers.aliasToBean(UserSessionLog.class))
				.uniqueResult();

		String loginTime = String.format("%tc",userSessionLog.getUslloginTime());

		loginTime = (loginTime == null || loginTime.equals("null")) ? " " : loginTime;

		return loginTime;
	}

	/**
	 * this method update and save user session.
	 *
	 * @param userId
	 *            the user id
	 * @param activeFlag
	 *            the active flag
	 */
	public Integer updateActiveLoginFlag(Integer userId, Integer activeFlag) {
		return super.currentSession()
				.createQuery("UPDATE UserSessionLog SET loginActive = " + activeFlag + " WHERE uslUserId =" + userId)
				.executeUpdate();
	}

	/**
	 * this method Gets the user login details.
	 *
	 * @param id
	 *            the userid
	 * @return the user login details
	 */
	@SuppressWarnings("unchecked")
	public List<UserSessionLog> getUserLoginDetails(Integer id) {
		List<UserSessionLog> userDetails = new ArrayList<UserSessionLog>();
		List<UserSessionLog> lastSuccesslogin = (List<UserSessionLog>) super.currentSession()
				.createCriteria(UserSessionLog.class)
				.setProjection(Projections.projectionList().add(Projections.property("uslloginTime").as("uslloginTime"))
						.add(Projections.property("systemIp").as("systemIp")))
				.addOrder(Order.desc("uslloginTime")).add(Restrictions.eq("uslUserId", id))
				.add(Restrictions.eq("uslloginAttempts", 1)).setMaxResults(1)
				.setResultTransformer(Transformers.aliasToBean(UserSessionLog.class)).list();
		if (lastSuccesslogin.size() > 0) {
			userDetails.add(lastSuccesslogin.get(0));
		}
		List<UserSessionLog> lastFailurelogin = (List<UserSessionLog>) super.currentSession()
				.createCriteria(UserSessionLog.class)
				.setProjection(Projections.projectionList().add(Projections.property("uslloginTime").as("uslloginTime"))
						.add(Projections.property("systemIp").as("systemIp")))
				.addOrder(Order.desc("uslloginTime")).add(Restrictions.eq("uslUserId", id))
				.add(Restrictions.eq("uslloginAttempts", 0)).setMaxResults(1)
				.setResultTransformer(Transformers.aliasToBean(UserSessionLog.class)).list();
		if (lastFailurelogin.size() > 0) {
			userDetails.add(lastFailurelogin.get(0));
		}
		return userDetails;
	}

	/** Update logout time for all active user while stopping the server
	 * 
	 * @return
	 */
	public Integer updateLogoutStatusOfAllActiveUsers() {

		return super.currentSession()
		.createQuery("UPDATE UserSessionLog SET loginActive = 0 , usllogoutTime=:currentTime , logoutType=:logoutType  WHERE usllogoutTime is null and uslloginAttempts=1 ")
		.setTimestamp("currentTime",new Date())
		.setParameter("logoutType", LogoutType.STOP)
		.executeUpdate();
		
	}


	/**
	 * this method will Update logout on application init.
	 */
	public Integer updateLogoutOnApplicationInit() {
		return  super.currentSession().createQuery("UPDATE  UserSessionLog  SET usllogoutTime =:currentTime ,loginActive=0 , logoutType=:logoutType \r\n" + 
				"WHERE usllogoutTime is null and uslloginAttempts=1 ")
				.setTimestamp("currentTime", new Date())
				.setParameter("logoutType", LogoutType.START)
				.executeUpdate();
	}
	

	/**
	 * this will Update the number of login attempts made by the user.
	 *
	 * @param id
	 *            the id
	 * @param sysid
	 *            the sysid
	 * @param remoteAddress
	 *            the remote address
	 */
	public void updateLoginAttemppt(Integer id, int loginAttempts, String remoteAddress,String sessionId,String autoSessionId,Integer maxInactiveTime) {


		
		UserSessionLog userSessionLog = new UserSessionLog();
		
		userSessionLog.setUsluserId(new User().setUserId(id) );
		userSessionLog.setUslloginAttempts(loginAttempts);
		userSessionLog.setUslloginTime(new Date());
		userSessionLog.setUslsysCode(1);
		userSessionLog.setSystemIp(remoteAddress);
		userSessionLog.setSessionId(sessionId);
		userSessionLog.setAutoSessionId(autoSessionId);
		userSessionLog.setMaxInactiveTime(maxInactiveTime);
		
		if (loginAttempts == 0){
			userSessionLog.setLoginActive(0);
		}
		else{
			userSessionLog.setLoginActive(1);
		}
		
		
		RequestAttributes reqAttr = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = null;
		if(Objects.nonNull(reqAttr)) {
			request = ((ServletRequestAttributes) reqAttr).getRequest();
		}
		
		 if(request!=null){
			 
			 UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
			 userSessionLog.setBrowserDetails(userAgent.getBrowser()+" / "+userAgent.getBrowserVersion());
			 userSessionLog.setDeviceOs(userAgent.getOperatingSystem().getName());
			 userSessionLog.setDeviceType(userAgent.getOperatingSystem().getDeviceType().getName());
		 }
		
		
		 persist(userSessionLog);
	}


	@SuppressWarnings("unchecked")
	public List<UserSessionLog> getAllActiveSessions() {

		return super.currentSession().createCriteria(UserSessionLog.class)
				.createAlias("usluserId", "user")
				.setProjection(Projections.projectionList()
						.add(Projections.property("user.username").as("username"))
						.add(Projections.property("uslloginTimeFormatted").as("uslloginTimeFormatted"))
						.add(Projections.property("browserDetails").as("browserDetails"))
						.add(Projections.property("autoSessionId").as("autoSessionId"))
						.add(Projections.property("systemIp").as("systemIp"))
						.add(Projections.property("deviceType").as("deviceType"))
						.add(Projections.property("deviceOs").as("deviceOs"))
						
						
						)
		.setResultTransformer(Transformers.aliasToBean(UserSessionLog.class))		
		.add(Restrictions.eq("loginActive", 1))
		.list();
	}
	
	
}
