package com.codechasers.license.core.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


/**
 * Provides access to several properties as well as validating the Context Configuration.
 *
 *
 * @author Selvam Murugesan
 * @since 2016-02-23
 */
@SuppressWarnings("javadoc")
@Component
@PropertySource({"classpath:config/application.properties"})
public class PropertiesServicesConfig {

   private static final Logger logger = LoggerFactory.getLogger(PropertiesServicesConfig.class);
	
   @Autowired
	private Environment environment;

	public String getPropByKey(String key){
			
		return this.environment.getProperty(key);
	}
	
	
}
