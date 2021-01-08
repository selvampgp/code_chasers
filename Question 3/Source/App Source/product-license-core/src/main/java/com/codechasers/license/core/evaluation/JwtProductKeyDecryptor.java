package com.codechasers.license.core.evaluation;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.codechasers.license.core.util.ExceptionLogger;

@Component
public class JwtProductKeyDecryptor implements ProductKeyDecryptor{

	@Override
	public JSONObject decryptProductKey(String jwtToken) {
		
		try {

			String[] splitString = jwtToken.split("\\.");
			
			/** Directly using jwt body 
			 * 
			 */
			String base64EncodedBody = splitString[1];

			Base64 base64Url = new Base64(true);
			String body = new String(base64Url.decode(base64EncodedBody));

			JSONObject decryptedKey = new JSONObject(body);

			for (ProductKeySpec keySpec : ProductKeySpec.values()) {
				
				if(!decryptedKey.has(keySpec.toString())){
					throw new Exception("Required field in product key is not available");
				}
			}
			
			
			return decryptedKey;

		} catch (Exception e) {

			new ExceptionLogger(e).logException();
			System.exit(0);

		}
		return null;

		
	}

}
