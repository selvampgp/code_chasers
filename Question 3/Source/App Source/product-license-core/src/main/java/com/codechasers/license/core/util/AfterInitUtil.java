package com.codechasers.license.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.codechasers.license.core.configuration.PropertiesServicesConfig;
import com.codechasers.license.core.services.UserService;
import com.codechasers.license.core.services.UserSessionLogService;

@Component
public class AfterInitUtil implements
		ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	PropertiesServicesConfig properties;

	private Boolean notificationInit = false;

	@Autowired
	UserService userService;

	@Autowired
	UserSessionLogService userSessionLogService;

	static ApplicationContextProvider appContext;

	private static final Logger logger = LoggerFactory
			.getLogger(AfterInitUtil.class);

	public static ApplicationContextProvider getApplicationContextProvider() {
		return appContext;
	}

	public static void setApplicationContextProvider(
			ApplicationContextProvider newAppContext) {
		appContext = newAppContext;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		logger.debug("onApplicationEvent triggered: {}", arg0);

		ApplicationContext context = arg0.getApplicationContext();
		logger.info("Loaded context-" + context.getDisplayName());

		if (!notificationInit) {

			try {
				userSessionLogService.updateLogoutOnApplicationInit();
			} catch (Exception e) {
				new ExceptionLogger(e).logException();
			}
		}
	}
}
