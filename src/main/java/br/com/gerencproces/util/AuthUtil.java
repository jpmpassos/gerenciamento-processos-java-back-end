package br.com.gerencproces.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AuthUtil {

	public static String generateHash(String password) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
		String encodedString = Base64.getEncoder().encodeToString(encodedhash);
		return new String(encodedString);
	}
	
}
