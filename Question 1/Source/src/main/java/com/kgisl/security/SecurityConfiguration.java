package com.kgisl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * To provide authentication, authorization, access-control and other security features.
 */
@Configurable
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private LoginAuthenticationProvider loginAuthenticationProvider;
	
	/* To set or validate additional attributes other than user name and password like captcha. */
	@Autowired
	private CustomWebAuthenticationDetailsSource authenticationDetailsSource;
	
	/**
	 * Used to configure/override authentication provider.
	 * @param auth - default authentication manager.
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	
		auth.authenticationProvider(loginAuthenticationProvider);
	}

	/**
	 * Overriding the default spring boot security.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/* To ignore/permit mentioned URLs to unauthorized requests */
		http.authorizeRequests().antMatchers("/login", "/", "/js/**", "/image/**", "/status").permitAll()
				.antMatchers("/static/**").permitAll();

		http.authorizeRequests()
				/* authenticate all remaining URLS */
				.anyRequest().authenticated()
				/* authentication type */
				.and().formLogin().authenticationDetailsSource(authenticationDetailsSource).loginPage("/login")
				/* default login success and login failure page */
				.defaultSuccessUrl("/home", true).failureUrl("/?error").and().httpBasic().and().logout().and()
				/* Custom filter to block BOT hit */
				.addFilterBefore(new HttpSecurityFilter(), UsernamePasswordAuthenticationFilter.class)
				.sessionManagement()
				/* session state */
				.maximumSessions(1).maxSessionsPreventsLogin(true);

		http.csrf();
	}
	
}
