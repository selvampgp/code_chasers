package com.codechasers.license.core.session;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.codechasers.license.core.util.ExceptionLogger;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/** Singleton class, used to read the JSON data from "skip-session-registry.json" once and 
 * expose the static method to check input URI is matched with json data and return 
 * 
 * 
 *
 */
public class SkipSessionRegistrationUriList {

	private static final Logger logger = LoggerFactory.getLogger(SkipSessionRegistrationUriList.class);
	
	private static SkipSessionRegistrationUriList sessionRegistrationUriList;

	private  List<String> skipUris = new ArrayList<String>();
	
	private SkipSessionRegistrationUriList() {
		
		Resource resource = new ClassPathResource("/spring/skip-session-registry.json");

		try (InputStream inputStream = resource.getInputStream()) {
			
			byte[] jsonByteArray = IOUtils.toByteArray(inputStream);
			String jsonString = new String(jsonByteArray);

			ObjectMapper objectMapper = new ObjectMapper();

			setSkipUris(objectMapper.readValue(jsonString,new TypeReference<ArrayList<String>>() {}));

			logger.info("Sessionless api patterns are loaded");
		} catch (IOException e) {
			new ExceptionLogger(e).logException();
		}

	}


	private void setSkipUris(List<String> skipUris) {
		this.skipUris = skipUris;
	}
	
	public List<String> getSkipUris() {
		return this.skipUris;
	}
	
	
	public static SkipSessionRegistrationUriList getInstance() {
		
		if (sessionRegistrationUriList == null) {
			sessionRegistrationUriList = new SkipSessionRegistrationUriList();
		}
		return sessionRegistrationUriList;
	}

	

}
