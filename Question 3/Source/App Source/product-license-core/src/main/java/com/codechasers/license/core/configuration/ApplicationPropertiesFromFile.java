
package com.codechasers.license.core.configuration;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**Used to properties from properties file and create the db related bean object*/
@Configuration
//@Profile(value="local")
public class ApplicationPropertiesFromFile {

	@Autowired
    private DbProperties dbProperties;
	
	@Autowired
	private PropertiesServicesConfig propertiesServicesConfig;
	
	@Bean(name = "dataSource")
	public DataSource generateDatasourceFromProperties(){
		
		BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(dbProperties.getDriverClassName());
        dataSource.setUrl(dbProperties.getUrl());
        dataSource.setUsername(dbProperties.getUsername());
        dataSource.setPassword(dbProperties.getPassword());
        dataSource.setValidationQuery(dbProperties.getValidationQuery());
        dataSource.setMaxActive(dbProperties.getMaxCount());
        return dataSource;
		
	}

	@Bean(name="hibernateConfiguration")
	public org.hibernate.cfg.Configuration getHibernateProperties() {
		final org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
		configuration.setProperty(AvailableSettings.USE_SQL_COMMENTS, "false");
		configuration.setProperty(AvailableSettings.USE_GET_GENERATED_KEYS,
				"true");
		configuration
				.setProperty(AvailableSettings.GENERATE_STATISTICS, "true");
		configuration.setProperty(AvailableSettings.USE_REFLECTION_OPTIMIZER,
				"true");
		configuration.setProperty(AvailableSettings.ORDER_UPDATES, "true");
		configuration.setProperty(AvailableSettings.ORDER_INSERTS, "true");
		configuration.setProperty(
				AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS, "true");
		configuration.setProperty(AvailableSettings.C3P0_MAX_SIZE, dbProperties
				.getMaxCount().toString());
		
		Map<String, String> propertiesFromYml = dbProperties
				.getHibernateProperties();
		for (String key : propertiesFromYml.keySet()) {
			configuration.setProperty(key, propertiesFromYml.get(key));
		}

		return configuration;
  }
	
	
}
