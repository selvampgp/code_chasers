package com.art.aspiringminds.core.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource({"classpath:config/application.properties"})
public class PropertiesServicesConfig {


	@Autowired
	private Environment environment;
	
	public String getPropByKey(ParameterKey key){
			
		return this.environment.getProperty(key.getValue());
	}
	


}
enum ParameterKey {
	
	
	MONGODB_DATABASE_NAME("mongo.databasename"),
	
	MONGODB_USERNAME("mongo.username"),
	
	MONGODB_PASSWORD("mongo.password"),
	
	MONGODB_HOST("mongo.host"),
	
	MONGODB_PORT("mongo.port"),
	
	OPEN_OFFICE_HOME_PATH("open.office.path"),
	
	OPEN_OFFICE_PORT_NUMBER("open.office.port")
	
	
	;
	
	private final Object value;

	private ParameterKey(final Object value) {
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	public <E> E getValue() {
		return (E) value;
	}
}


