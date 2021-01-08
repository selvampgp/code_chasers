
package com.codechasers.aspiringminds.core.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document
public class OfferTemplate  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String templateName;
	
	private byte[] templateFile;
	
	private Date createdDate;

	@Transient
	private Map<String,Object> jsonData;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public OfferTemplate setId(String id) {
		this.id = id;
		return this;
	}

	/**
	 * @return the headerName
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * @param headerName the headerName to set
	 */
	public OfferTemplate setTemplateName(String templateName) {
		this.templateName = templateName;
		return this;
	}

	/**
	 * @return the templateFile
	 */
	public byte[] getTemplateFile() {
		return templateFile;
	}

	/**
	 * @param templateFile the templateFile to set
	 */
	public OfferTemplate setTemplateFile(byte[] templateFile) {
		this.templateFile = templateFile;
		return this;
	}

	/**
	 * @return the createdDate
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public OfferTemplate setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
		return this;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	public OfferTemplate build(){
		return this;
	}

	/**
	 * @return the jsonData
	 */
	public Map<String, Object> getJsonData() {
		return jsonData;
	}

	/**
	 * @param jsonData the jsonData to set
	 */
	public void setJsonData(Map<String, Object> jsonData) {
		this.jsonData = jsonData;
	}

	}
