package com.kgisl.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * Spring security custom authentication provider.
 * 
 * ALL Authentication request will be processed by AuthenticationProvider and a fully authenticated object with full credentials is returned or an exception will be returned.
 */
@Service
public class LoginAuthenticationProvider extends DaoAuthenticationProvider{
	
	/**
	 * To define password crypt algorithm
	 * 
	 * @return Password encoder object 
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@Autowired
	@Override
	@Qualifier("userDetailsService")
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		super.setUserDetailsService(userDetailsService);
		super.setPasswordEncoder(passwordEncoder());
	}
	
	/**
	 * Used to validate & authenticate user using submitted user name, password and captcha from login page.
	 * 
	 * If the user has right captcha text it will authorize the user, else it will throw an exception with message. 
	 */
	@Override
	public Authentication authenticate(Authentication authentication) {
		
		CustomWebAuthenticationDetails customWebAuthenticationDetails = null;
		
		if(authentication.getDetails() instanceof CustomWebAuthenticationDetails){
			
			customWebAuthenticationDetails = ((CustomWebAuthenticationDetails) authentication.getDetails());
			
			// validating captcha
			if(customWebAuthenticationDetails.getActualCaptcha().equals(customWebAuthenticationDetails.getCaptchaFromUser())){
				String username = authentication.getName();
				String password = authentication.getCredentials().toString();
				
				Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
				
				return new UsernamePasswordAuthenticationToken(username, password, authorities);
			}else{
				throw new BadCredentialsException("Invalid captcha");
			}
		}
		
		throw new BadCredentialsException("Invalid username/password");
	}
	
}
