/*******************************************************************************
 * EXCLUSIVE LICENSE
 * THE INFORMATION AND COMPUTER SOURCE CODE CONTAINED WITHIN THIS PROGRAM SCRIPT IS
 * THE EXCLUSIVE PROPERTY OF HAWES TECHNOLOGIES, LLC. USE MUST BE AUTHORIZED UNDER WRITTEN
 * LICENSE OBTAINED FROM HAWES TECHNOLOGIES, LLC. USE AT YOUR OWN RISK. NO WARRANTY EITHER
 * EXPRESSED OR IMPLIED.
 * 
 * UNAUTHORIZED USE, ALTERATION, COPYING, OR REDISTRIBUTION IS STRICTLY PROHIBITED.
 * 
 *  @copyright Copyright (c) 2017 Hawes Technologies, LLC.
 ******************************************************************************/
package com.codechasers.license.ui;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.CacheControl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
@EnableWebMvc
@ComponentScan(value = {"com.codechasers.license.ui","com.codechasers.license.core"}, useDefaultFilters = false, includeFilters = @ComponentScan.Filter(value = org.springframework.stereotype.Controller.class, type = FilterType.ANNOTATION))
@ImportResource("classpath:spring/license-dispatcher-servlet-context.xml")
@EnableAsync
public class WebDispatcherServletConfiguration extends WebMvcConfigurerAdapter
		implements
			ServletContextAware,
			InitializingBean {

	private ServletContext servletContext;
	
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseTrailingSlashMatch(true);
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;

	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 registry.addResourceHandler("/assets/**")
         .addResourceLocations("/assets/")
         .setCacheControl(CacheControl.maxAge(30, TimeUnit.DAYS))
         .resourceChain(false);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.servletContext
				.setAttribute("apiBaseUrl",
						servletContext.getContextPath() + "/api");
	}

	@Bean
	@Description("Spring message resolver")
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("i18n/messages");

		return messageSource;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		return slr;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
	

}
