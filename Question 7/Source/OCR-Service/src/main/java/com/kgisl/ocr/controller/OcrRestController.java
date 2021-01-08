package com.kgisl.ocr.controller;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kgisl.ocr.service.OCRExtractorService;

import net.sourceforge.tess4j.TesseractException;



@RestController
public class OcrRestController {

	@Autowired
	OCRExtractorService ocrExtractorService;
	@PostMapping(value = "/OCR/Extraction")
	public Map<String, Object> readImageData(@RequestParam("file") MultipartFile uploadedFile ,
			@RequestParam(value = "type") String type,
			@RequestParam(value = "country", required = false) String country) throws IOException, TesseractException {
		return ocrExtractorService.OcrExtraction(uploadedFile, type, country);
		
	}

	

}
