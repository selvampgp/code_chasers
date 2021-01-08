package com.codechasers.license.core.configuration;

import static java.util.Arrays.asList;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableScheduling
@EnableTransactionManagement
@ComponentScan(basePackages={"com.codechasers.license.*"}
			)
/**
 * 
 * Configuration class to create db connection, hibernate transaction,etc
 *
 */
public class CoreConfiguration {

   @Autowired
   private PropertiesServicesConfig propertiesFile;

   private static final String[] HIBERNATE_PACKAGES_TO_SCAN =
         asList("com.codechasers.license.core.models").toArray(new String[0]);


   private static final Logger logger = LoggerFactory.getLogger(CoreConfiguration.class);

   @Bean
   @Inject
   public SessionFactory sessionFactory(DataSource dataSource, @Qualifier(
         value = "hibernateConfiguration") org.hibernate.cfg.Configuration configuration) {
      logger.info("Injecting Hibernate sessionFactory bean");
      LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
      sessionBuilder.scanPackages(HIBERNATE_PACKAGES_TO_SCAN);
      sessionBuilder.addProperties(configuration.getProperties());
      return sessionBuilder.buildSessionFactory();
   }

   @Bean
   @Inject
   public JdbcTemplate jdbcTemplate(DataSource dataSource) {
      logger.info("Injecting JdbcTemplate bean for datasource:{}.",dataSource);
      return new JdbcTemplate(dataSource);
   }


   @Inject
   @Primary
   @Bean(name = "transactionManager")
   public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
      logger.info("Injecting HibernateTransactionManager bean.");
      HibernateTransactionManager hibernateTransactionManager =
            new HibernateTransactionManager(sessionFactory);
      
      hibernateTransactionManager.setNestedTransactionAllowed(true);
      return hibernateTransactionManager;


   }

   

   @Bean
   public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
      logger.info("Creating propertySourcesPlaceholderConfigurer bean.");
      return new PropertySourcesPlaceholderConfigurer();
   }
}
