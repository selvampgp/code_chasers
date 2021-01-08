package com.codechasers.license.core;

import java.util.ArrayList;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.codechasers.license.core.models.CustomUser;

public class BaseUnitTest {

	public CustomUser cUser;
	
	@Before
	public void baseSetUp() {
		MockitoAnnotations.initMocks(this);
		
		ArrayList<GrantedAuthority> authoritys = new ArrayList<GrantedAuthority>();
		this.cUser = new CustomUser("tester", "Tester@123", true, true, true, true, 1);
		Authentication auth = new UsernamePasswordAuthenticationToken(cUser, null);
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
}
