package com.codechasers.license.core.evaluation;

import org.json.JSONObject;

public interface ProductKeyDecryptor {

	public JSONObject decryptProductKey(String encryptedValue);
}
