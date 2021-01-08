package com.kgisl.security.service;

import java.awt.Color;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.InitializingBean;
import nl.captcha.Captcha;
import nl.captcha.backgrounds.BackgroundProducer;
import nl.captcha.backgrounds.FlatColorBackgroundProducer;
import nl.captcha.text.producer.DefaultTextProducer;
import nl.captcha.text.producer.TextProducer;
import nl.captcha.text.renderer.DefaultWordRenderer;
import nl.captcha.text.renderer.WordRenderer;

/**
 * Implementation of InitializingBean to create simple captcha, with the ability to generate image.
 * SimpleCaptcha is an open source software under the BSD License.
 */
public class CaptchaGenerator implements InitializingBean{
	private BackgroundProducer backgroundProducer;
	private TextProducer textProducer;
	private WordRenderer wordRenderer;
    Color backgroundColor = Color.white  ;

	boolean isBorder;

	public Captcha createCaptcha(Integer width, Integer height) {
		Captcha.Builder builder = new Captcha.Builder(width, height)
				.addBackground(backgroundProducer)
				.addText(textProducer, wordRenderer);


		if (isBorder) {
			builder.addBorder();
		}

		return builder.build();
	}

	/* To set captcha background properties */
	public void setBackgroundProducer(BackgroundProducer backgroundProducer) {
		this.backgroundProducer = backgroundProducer;
	}

	/* To set text captcha */
	public void setTextProducer(TextProducer textProducer) {
		this.textProducer = textProducer;
	}

	/* To set word renderer properties */
	public void setWordRenderer(WordRenderer wordRenderer) {
		this.wordRenderer = wordRenderer;
	}

	/* To set border status */
	public void setBorder(boolean isBorder) {
		this.isBorder = isBorder;
	}

	/**
	 * Used to set image properties like size, font, color, etc. after generating captcha 
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		if (this.backgroundProducer == null) {
			this.backgroundProducer = new FlatColorBackgroundProducer(backgroundColor);
		}

		if (this.textProducer == null) {
			this.textProducer = new DefaultTextProducer();
		}

		if (this.wordRenderer == null) {
			Color color = Color.gray;
			   List<Font> fonts = new ArrayList<Font>();
		        fonts.add(new Font("arial", Font.PLAIN, 35));
			this.wordRenderer = new DefaultWordRenderer(color, fonts);
		}
	}

	/* To encode or convert captcha jpg image as byte codes */
	public  String encodeBase64(Captcha captcha) throws InstantiationException {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(captcha.getImage(), "jpg", bos);
			return DatatypeConverter.printBase64Binary(bos.toByteArray());
		} catch (IOException e) {
			return "";
		}
	}
}
