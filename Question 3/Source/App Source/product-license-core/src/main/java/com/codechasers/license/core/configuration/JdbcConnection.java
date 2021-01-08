package com.codechasers.license.core.configuration;

import javax.sql.DataSource;

import com.codechasers.license.core.util.ApplicationContextProvider;

/**
 * 
 * Establish the jdbc connection for the database
 *
 */
public class JdbcConnection {

	private DataSource dataSource;
	
	private JdbcConnection(){
		
		ApplicationContextProvider appContext = new ApplicationContextProvider();
		
		dataSource =appContext.getApplicationContext().getBean("dataSource", DataSource.class);
		
	}
	
	
	private static JdbcConnection connection;
	
	public static JdbcConnection getJdbcConnection(){
		
		if(connection==null){
			connection= new JdbcConnection();
		}
		
		return connection;
	}
	
	public DataSource getDataSource(){
		return this.dataSource;
	}
	
}
