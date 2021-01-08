package com.codechasers.license.core.evaluation;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.codechasers.license.core.configuration.SystemParameterKey;
import com.codechasers.license.core.session.SessionAuthenticationStrategy;
import com.codechasers.license.core.util.ActiveUserRepo;

@Component
public class ProductKeyEval implements InitializingBean {

	@Autowired
	Environment env;

	@Autowired
	ProductKeyDecryptor productKeyDecryptor;
	
	//NOSONAR
	
	private static final Logger logger = LoggerFactory
			.getLogger(ProductKeyEval.class);

	static SimpleDateFormat simpleDateFormat= new SimpleDateFormat("MM/dd/yyyy");

	private static Date licenseExpireDate = new Date();


	private void decryptProductKey(String productKey) {

		try {

			JSONObject decryptedKey = productKeyDecryptor.decryptProductKey(productKey);
					
			if(decryptedKey==null){
				throw new Exception("");
			}
			
			if (!decryptedKey.isNull(ProductKeySpec.constraintLimit.toString()) && !decryptedKey.isNull(ProductKeySpec.licenseExpireDate.toString()) ) {
				ActiveUserRepo.getInstance().setAllowedUserCount(decryptedKey.getInt(ProductKeySpec.constraintLimit.toString()));
				licenseExpireDate = simpleDateFormat.parse
						(decryptedKey.get(ProductKeySpec.licenseExpireDate.toString()).toString());
				
			}
			else{
				throw new Exception("Decrypted key is not valid");
			}
			
			SessionAuthenticationStrategy.setExceptionIfMaximumSessionExceeded(true);
			
			if(!decryptedKey.isNull(ProductKeySpec.maximumSessionPerUser.toString())){
				SessionAuthenticationStrategy.setMaximumSessionPerUser(decryptedKey.getInt(ProductKeySpec.maximumSessionPerUser.toString()));
			}
			

		} catch (Exception e) {

			logger.error("Product key provided in context file is not valid");
			System.exit(0);

		}

	}

	@Override
	public void afterPropertiesSet() throws Exception {

		decryptProductKey(env
				.getProperty(SystemParameterKey.PRODUCT_KEY.getValue()));
				
		validateKey();
	}


	/**
	 * @return the productValidTill
	 */
	public static Date getProductValidTill() {
		return licenseExpireDate;
	}



	public static void validateKey() {

		if (licenseExpireDate != null
				&& licenseExpireDate.compareTo(new Date()) >= 0) {
			
			logger.info("Product key is valid");
		} else {

			logger.error("Artrail validity expired, reach out artrail support team for further assistance");
			System.exit(0);
		}

	}



}
