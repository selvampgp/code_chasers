package com.art.aspiringminds.core.services.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.art.aspiringminds.core.models.OfferTemplate;
import com.art.aspiringminds.core.service.DocxReportGenerator;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.core.io.XDocArchive;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.docx.DocxConstants;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.FieldExtractor;
import fr.opensagres.xdocreport.template.FieldsExtractor;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.velocity.VelocityFieldsExtractor;

public class XDocxReportGeneratorImpl implements DocxReportGenerator {

	@Override
	public byte[] generate(OfferTemplate offerTemplate) throws IOException {

		byte[] returnArrayList = null;

		try(InputStream inputStream = new ByteArrayInputStream(
					offerTemplate.getTemplateFile());
			ByteArrayOutputStream out = new ByteArrayOutputStream();) {

			
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(
					inputStream, TemplateEngineKind.Velocity);

			// 2) Create Java model context
			IContext context = report.createContext();
			context.put("dt", offerTemplate.getJsonData());
			
			report.process(context, out);
			returnArrayList = out.toByteArray();

		} catch (XDocReportException e) {

			e.printStackTrace();
		}

		return returnArrayList;
	}

	@Override
	public List<String> extractFields(OfferTemplate offerTemplate)
			throws Exception {
		List<String> fieldsInDocument= new ArrayList<>();
		
		try(InputStream inputStream = new ByteArrayInputStream(
					offerTemplate.getTemplateFile());) {

			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(
					inputStream, TemplateEngineKind.Velocity);

			XDocArchive archive = report.getPreprocessedDocumentArchive();

			Reader reader = archive
					.getEntryReader(DocxConstants.WORD_DOCUMENT_XML_ENTRY);
			StringWriter writer = new StringWriter();
			
			IOUtils.copy(reader, writer);

			StringReader contentAsString = new StringReader(writer.toString());

			FieldsExtractor<FieldExtractor> extractor = FieldsExtractor
					.create();
			VelocityFieldsExtractor.getInstance().extractFields(
					contentAsString, "", extractor);

			for (FieldExtractor fieldExtractor : extractor.getFields()) {
				fieldsInDocument.add(fieldExtractor.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fieldsInDocument;
	}

}
