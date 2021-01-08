package com.codechasers.aspiringminds.core.services.impl;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.POIXMLProperties;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty;

import com.codechasers.aspiringminds.core.service.TemplateParameter;

public class DocxTemplateParameterImpl implements TemplateParameter{

	
	public List<String> extractParameter(byte[] templateFileStream) {
		List<String> paramList = new ArrayList<String>();
		try{
			XWPFDocument document = new XWPFDocument(new ByteArrayInputStream(templateFileStream));
		    POIXMLProperties props = document.getProperties();
		    org.apache.poi.POIXMLProperties.CustomProperties cp = props.getCustomProperties();
		    
		    if (cp != null) {
		        List<CTProperty> ctProperties = cp.getUnderlyingProperties()
		                .getPropertyList();
		        for (CTProperty ctp : ctProperties) {
		        
		        	paramList.add(ctp.getName());
		         

		        }
		    }
		   props.commit();
		    
		   return paramList;
		   
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}
