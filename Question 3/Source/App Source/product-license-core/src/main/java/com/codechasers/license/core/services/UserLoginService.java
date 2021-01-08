
package com.codechasers.license.core.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.codechasers.license.core.dao.UserDao;
import com.codechasers.license.core.dao.UserLoginDao;
import com.codechasers.license.core.models.CustomUser;
import com.codechasers.license.core.models.User;
import com.codechasers.license.core.util.ActiveUserRepo;

/**
 * Service class inherited from UserDetailsService with method definition used by spring authentication to authorize user  .
 * 
 * @see UserDao
 * @see UserPermissionDao
 * @see UserRoleDao
 * @see RoleRefferenceService
 */
@Service
@Transactional
public class UserLoginService implements UserDetailsService{
	
	
	
	/** The user role dao. */
	@Autowired
	 UserLoginDao userLoginDao;
	
	

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		
		
		User user = userLoginDao.findByUserName(username);
		
		Assert.notNull(user, "User Not Found "+username);
		
		Assert.isTrue(ActiveUserRepo.getInstance().isUserActive(user.getUserId()),"Permissable active user limit reached");
					
		boolean accountLockStatus=false;
		boolean accountActiveStatus=false;
		boolean userPasswordChageRequired=true;
			
		if(("N").equals(user.getLockStatus())){
			accountLockStatus=true;
		}
		if(user.getActive().equals("Y")){
			accountActiveStatus=true;
		}
				
		return  new CustomUser(user.getUsername(),	user.getPassword(), accountLockStatus, accountActiveStatus,userPasswordChageRequired , true, user.getUserId());
	
	
		
		
	}

}
