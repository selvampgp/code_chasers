package com.kgisl.ocr.service;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;
import net.sourceforge.tess4j.util.LoadLibs;

@Service
public class OCRExtractorService {

	public Map<String, Object> OcrExtraction(MultipartFile uploadedFile,String type,String country) throws IOException, TesseractException{
		BufferedImage resizedImage = convertAndResizeImage(uploadedFile);
		String passportJsonDetails = "";

		if (type.equals("PANCARD"))
			passportJsonDetails = "JsonFiles/panDetails.json";
		else if (type.equals("PASSPORT")) {
			passportJsonDetails = "JsonFiles/" + country + "PassportDetails.json";
		}

		JSONObject jsonSchemeObject = jsonParser(passportJsonDetails, "india");
		System.out.println(jsonSchemeObject);

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> mapOfValues = objectMapper.readValue(objectMapper.writeValueAsString(jsonSchemeObject),
				new TypeReference<Map<String, Object>>() {
				});
		Map<String, Object> returnValues = new HashMap<String, Object>();
		ITesseract tesseract = new Tesseract();
		tesseract.setDatapath(LoadLibs.extractTessResources("tessdata").getAbsolutePath());
		for (Entry<String, Object> entry : mapOfValues.entrySet()) {

			Map<String, Integer> coOrdinates = (Map<String, Integer>) entry.getValue();
			BufferedImage text = resizedImage.getSubimage(coOrdinates.get("x"), coOrdinates.get("y"),
					coOrdinates.get("width"), coOrdinates.get("height"));
			BufferedImage grayScalimage = ImageHelper.convertImageToGrayscale(text);
			String renderedText = tesseract.doOCR(grayScalimage);
			returnValues.put(entry.getKey(), renderedText.trim());
		}

		return returnValues;

	}
	
	
public static BufferedImage convertAndResizeImage(MultipartFile inputFile) throws IOException {

		
		InputStream stream=inputFile.getInputStream();
		int resizeWidth = 1021;
		int resizeHeight = 768;
		String outputImagePath = "D://resizedImage.jpg";
		String fileName=inputFile.getOriginalFilename();
		//File inputFile = (File) inputFilem;
		BufferedImage inputImage = ImageIO.read(stream);
		String extension = FilenameUtils.getExtension(fileName);
		if (extension.equalsIgnoreCase("pdf")) {

			PDDocument document = PDDocument.load(stream);
			PDFRenderer pdfRenderer = new PDFRenderer(document);

			String fileExtension = "jpg";
			int dpiValue = 300;

			File outPutFile = new File("D://pdftestimg.jpg");
			inputImage = pdfRenderer.renderImageWithDPI(0, dpiValue, ImageType.RGB);
			ImageIO.write(inputImage, fileExtension, outPutFile);
			document.close();
		}

		BufferedImage outputImage = new BufferedImage(resizeWidth, resizeHeight, inputImage.getType());

		Graphics2D g2d = outputImage.createGraphics();
		g2d.drawImage(inputImage, 0, 0, resizeWidth, resizeHeight, null);
		g2d.dispose();

		ImageIO.write(outputImage, "jpg", new File(outputImagePath));
		return outputImage;
	}

	protected static JSONObject jsonParser(String jsonFileName, String jsonKey) {
		JSONObject jsonObject;
		JSONParser parser;
		try {
			parser = new JSONParser();
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			File jsonFilePath = new File(new URL(loader.getResource(jsonFileName).toString()).toURI());

			try (FileReader fileReader = new FileReader(jsonFilePath)) {
				jsonObject = (JSONObject) parser.parse(fileReader);
			}
			return jsonObject;
		} catch (Exception ex) {
			return null;
		}
	}
}
