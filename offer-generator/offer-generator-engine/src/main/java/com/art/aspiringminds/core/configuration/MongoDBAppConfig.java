package com.art.aspiringminds.core.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

/***
 *This class contains the MongoDB Configuration
 */

@Configuration
@EnableMongoRepositories({"com.art.aspiringminds.core.models"})
public class MongoDBAppConfig {


	@Autowired
	PropertiesServicesConfig propertiesServicesConfig;

	
	
	/**
	 * @return mongo object
	 * used to define mongo template
	 */
  
  @Bean
  public MongoClient mongo() throws Exception {

    List<MongoCredential> auths = new ArrayList<>();
    MongoCredential mongoCred = MongoCredential.createCredential(propertiesServicesConfig.getPropByKey(ParameterKey.MONGODB_USERNAME),
    		propertiesServicesConfig.getPropByKey(ParameterKey.MONGODB_DATABASE_NAME),
    		propertiesServicesConfig.getPropByKey(ParameterKey.MONGODB_PASSWORD).toCharArray());
    auths.add(mongoCred);

    MongoClientOptions mongoClientOptions = MongoClientOptions.builder().connectionsPerHost(500)
        .connectTimeout(1000).socketKeepAlive(false).cursorFinalizerEnabled(true)
        .maxConnectionLifeTime(50)
        .writeConcern(WriteConcern.ACKNOWLEDGED)
        .threadsAllowedToBlockForConnectionMultiplier(10)
        .readPreference(ReadPreference.primary()).build();

    return new MongoClient(new ServerAddress(propertiesServicesConfig.getPropByKey(ParameterKey.MONGODB_HOST),
        Integer.parseInt(propertiesServicesConfig.getPropByKey(ParameterKey.MONGODB_PORT))), auths, mongoClientOptions);
  }

  
  @Bean(name="mongoDbFactory")
	public MongoDbFactory mongoDbFactory( ) throws Exception {
		
		return new SimpleMongoDbFactory(mongo(),propertiesServicesConfig.getPropByKey(ParameterKey.MONGODB_DATABASE_NAME));
	}
  
  @Bean(name="converter")
	public MappingMongoConverter mongoConverter(@Qualifier("mongoDbFactory") MongoDbFactory mongoDbFactory) throws Exception {
	    MongoMappingContext mappingContext = new MongoMappingContext();
	    DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
	    MappingMongoConverter mongoConverter = new MappingMongoConverter(dbRefResolver, mappingContext);
	    
	    return mongoConverter;
	}
  
	/**
	 * used to interact with file system
	 * @returnGridFsTemplate
	 * @throws Exception
	 */
	@Bean(name="mongoBean")
	public GridFsTemplate gridFsTemplate(@Qualifier("mongoDbFactory") MongoDbFactory mongoDbFactory,
			@Qualifier("converter") MappingMongoConverter converter) throws Exception {
		return new GridFsTemplate(mongoDbFactory, converter);
	}

	@Bean
	public MongoTemplate mongoTemplate(@Qualifier("mongoDbFactory") MongoDbFactory mongoDbFactory,
			@Qualifier("converter") MappingMongoConverter converter) throws Exception {
		System.out.println("sss");
		return new MongoTemplate(mongoDbFactory,converter);
	}
	

}
