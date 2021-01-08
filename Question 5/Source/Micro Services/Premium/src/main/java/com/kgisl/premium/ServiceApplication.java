package com.kgisl.premium;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.kgisl.premium"})
public class ServiceApplication {

	/* Mysql Data Source */
	@Autowired
	DataSource dataSource;
	
	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}
	
	/* Hibernate session factory */
	@Bean(name ="sessionFactory")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan("com.kgisl.premium");
		return sessionFactory;
	}

	/* Rest template to communicate with other services */
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		
		return new RestTemplate();
	}
}
