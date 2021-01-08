
package com.codechasers.license.core.services;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.codechasers.license.core.BaseUnitTest;
import com.codechasers.license.core.dao.UserLoginDao;
import com.codechasers.license.core.models.CustomUser;
import com.codechasers.license.core.models.User;
import com.codechasers.license.core.services.UserLoginService;
import com.codechasers.license.core.util.ActiveUserRepo;


public class UserDetailsServiceTest extends BaseUnitTest{

	
	@Mock
	UserLoginDao loginDao;
	
	@InjectMocks
	UserLoginService loginService;
	
	
	private User buildUser(){
		User user  = new User();
		user.setUsername("test");
		user.setLockStatus("N");
		user.setActive("Y");
		user.setUserId(1);
		user.setPassword("");
		
		return user;
	}
	
	@Test
	public void shouldLoadUser(){
		
		ActiveUserRepo.getInstance().setAllowedUserCount(1);
		
		Mockito.when(loginDao.findByUserName(Mockito.anyString())).thenReturn(buildUser());
		
		CustomUser user= (CustomUser) loginService.loadUserByUsername("");
		
		Assert.assertEquals(user.getUsername(),buildUser().getUsername());
		
	}
	
	
	@Test(expected=IllegalArgumentException.class)

	public void shouldThrowErrorLoadUser(){
		
		ActiveUserRepo.getInstance().setAllowedUserCount(1);
		
		
		
		Mockito.when(loginDao.findByUserName(Mockito.anyString())).thenReturn(null);
		
		CustomUser user= (CustomUser) loginService.loadUserByUsername("");
		
		Assert.assertEquals(user.getUsername(),buildUser().getUsername());
		
	}
	
}
