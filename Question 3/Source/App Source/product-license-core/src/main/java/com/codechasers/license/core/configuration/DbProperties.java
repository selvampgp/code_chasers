package com.codechasers.license.core.configuration;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 
 * Used to access the values in property file
 *
 */

@Component
@PropertySource(value = "classpath:config/application.properties")
//@Profile(value="local")
public class DbProperties {

	@Value("${driver.class}")
	private String driverClassName;
	@Value("${url}")
	private String url;
	@Value("${usrname}")
	private String username;
	@Value("${password}")
	private String password;
	@Value("${validation.query}")
	private String validationQuery;
	@Value("${hibernate.dialect}")
	private String hibernateDialect;
	@Value("${hibernate.properties.show_sql}")
	private String hibernateShowSql;
	@Value("${maxCount}")
	private Integer maxCount;
	
	
	

	public Map<String, String> getHibernateProperties() {
		Map<String, String> hibernateProperties = new HashMap<String, String>();
		hibernateProperties.put(AvailableSettings.DIALECT, hibernateDialect);
		hibernateProperties.put(AvailableSettings.SHOW_SQL, hibernateShowSql);
		return hibernateProperties;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getValidationQuery() {
		return validationQuery;
	}

	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}

	public String getHibernateDialect() {
		return hibernateDialect;
	}

	public void setHibernateDialect(String hibernateDialect) {
		this.hibernateDialect = hibernateDialect;
	}

	public String getHibernateShowSql() {
		return hibernateShowSql;
	}

	public void setHibernateShowSql(String hibernateShowSql) {
		this.hibernateShowSql = hibernateShowSql;
	}
	
	public Integer getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}
	

}
