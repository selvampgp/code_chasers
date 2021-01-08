/*
 * Copyright 2002-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codechasers.license.core.models;

import java.security.SecureRandom;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Implementation of PasswordEncoder that uses the BCrypt strong hashing
 * function. Clients can optionally supply a "strength" (a.k.a. log rounds in
 * BCrypt) and a SecureRandom instance. The larger the strength parameter the
 * more work will have to be done (exponentially) to hash the passwords. The
 * default value is 10.
 *
 * @author Dave Syer
 *
 */
public class BCryptPasswordEncoder implements PasswordEncoder {

	/** The strength. */
	private final int strength;

	/** The random. */
	private final SecureRandom random;

	/**
	 * Instantiates a new b crypt password encoder.
	 */
	public BCryptPasswordEncoder() {
		this(-1);
	}

	/**
	 * Instantiates a new b crypt password encoder.
	 *
	 * @param strength
	 *            the log rounds to use
	 */
	public BCryptPasswordEncoder(int strength) {
		this(strength, null);
	}

	/**
	 * Instantiates a new b crypt password encoder.
	 *
	 * @param strength
	 *            the log rounds to use
	 * @param random
	 *            the secure random instance to use
	 */
	public BCryptPasswordEncoder(int strength, SecureRandom random) {
		this.strength = strength;
		this.random = random;
	}

	/*
	 * This method was used to convert the raw password to encrypted one.
	 * 
	 * @param CharSequence rawPassword to encrypt
	 * 
	 * @return Encrypted Password.
	 */
	public String encode(CharSequence rawPassword) {
		String salt;
		if (strength > 0) {
			if (random != null) {
				salt = BCrypt.gensalt(strength, random);
			} else {
				salt = BCrypt.gensalt(strength);
			}
		} else {
			salt = BCrypt.gensalt();
		}
		return BCrypt.hashpw(rawPassword.toString(), salt);
	}

	/*
	 * This Method used to compare the passwords
	 * 
	 * @param CharSequence rawPassword
	 * 
	 * @param String encodedPassword encrypted
	 * 
	 * @return boolean value.
	 */
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
	}

}
