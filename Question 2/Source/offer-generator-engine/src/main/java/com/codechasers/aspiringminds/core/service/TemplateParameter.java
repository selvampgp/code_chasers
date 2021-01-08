package com.codechasers.aspiringminds.core.service;

import java.util.List;

public interface TemplateParameter {

	/** Extract parameters defined in report parameter
	 * 
	 * @param templateFile
	 * @return
	 */
	 List<String> extractParameter(byte[] templateFile);
}
