package com.codechasers.license.core.configuration;

/**
 * 
 * Enum class to hold the string property name 
 *
 */
public enum SystemParameterKey {
		
		PRODUCT_KEY("product_key");
					

		
		
		private final String value;

		private SystemParameterKey(final String value) {
			this.value = value;
		}

		public String getValue() {
			return  value;
		}
	
	
}
