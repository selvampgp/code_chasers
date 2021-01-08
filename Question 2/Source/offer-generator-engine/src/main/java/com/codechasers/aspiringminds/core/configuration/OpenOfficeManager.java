package com.codechasers.aspiringminds.core.configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.jodconverter.core.office.OfficeException;
import org.jodconverter.core.office.OfficeManager;
import org.jodconverter.local.office.LocalOfficeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OpenOfficeManager {
	@Autowired
	private PropertiesServicesConfig properties;
	
	OfficeManager officeManager;

	@PostConstruct
	public void init() throws OfficeException {
		
		System.out.println("Starting open office");
		try {

			officeManager = LocalOfficeManager.builder().install()
					.officeHome(properties.getPropByKey(ParameterKey.OPEN_OFFICE_HOME_PATH))
					.portNumbers(Integer.parseInt(
							properties.getPropByKey(ParameterKey.OPEN_OFFICE_PORT_NUMBER)))
					.build();

			officeManager.start();
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	@PreDestroy
	public void stop() throws OfficeException {
		officeManager.stop();
	}

}