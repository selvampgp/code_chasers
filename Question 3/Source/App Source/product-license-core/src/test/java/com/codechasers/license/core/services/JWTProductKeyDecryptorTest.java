package com.codechasers.license.core.services;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import com.codechasers.license.core.evaluation.JwtProductKeyDecryptor;
import com.codechasers.license.core.evaluation.ProductKeySpec;

public class JWTProductKeyDecryptorTest {
	
	
	@Test
	public void shouldDecrypt(){
		
		JwtProductKeyDecryptor decryptor = new JwtProductKeyDecryptor();
		
		JSONObject decryptedKey = decryptor.decryptProductKey("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb25zdHJhaW50TGltaXQiOiIxIiwibWF4aW11bVNlc3Npb25QZXJVc2VyIjoxLCJleGNlcHRpb25JZk1heFNlc3Npb25FeGNlZWRlZCI6dHJ1ZSwibGljZW5zZUV4cGlyZURhdGUiOiIxMi8zMS8yMDIxIn0.hL1qoaPO0M0m7JbARryKsOkcoYXA4xc9F8JiQ7u2mAA");
		
		Assert.assertTrue(decryptedKey.has(ProductKeySpec.constraintLimit.toString()));
		Assert.assertTrue(decryptedKey.has(ProductKeySpec.maximumSessionPerUser.toString()));
		Assert.assertTrue(decryptedKey.has(ProductKeySpec.licenseExpireDate.toString()));
		
	}

}
