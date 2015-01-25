package com.neuralnoise.map.service.security.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtils {

	private SecurityUtils() {
	}

	public static String hash(String algorithm, String input) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance(algorithm);
		digest.update(input.getBytes(), 0, input.length());
		final String hash = new BigInteger(1, digest.digest()).toString(16);
		return hash;
	}

}
