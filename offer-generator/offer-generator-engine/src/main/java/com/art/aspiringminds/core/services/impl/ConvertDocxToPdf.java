package com.art.aspiringminds.core.services.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.jodconverter.core.document.DefaultDocumentFormatRegistry;
import org.jodconverter.core.document.DocumentFormat;
import org.jodconverter.local.JodConverter;

public class ConvertDocxToPdf {

	public static byte[] processDocxtoPdf(byte[] docxFileStream, String inputfileformat) throws Exception {

		String outputformat = "pdf";

		InputStream ins = new ByteArrayInputStream(docxFileStream);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		byte[] returnArray = new byte[0];

		try {

			final DocumentFormat targetFormat = DefaultDocumentFormatRegistry.PDF;
			JodConverter.convert(ins).to(os).as(targetFormat).execute();
			returnArray = os.toByteArray();

		} catch (Exception e) {
			os.close();
		} finally {
			os.close();
		}

		return returnArray;

	}

}
