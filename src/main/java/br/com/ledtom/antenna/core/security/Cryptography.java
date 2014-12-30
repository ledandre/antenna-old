package br.com.ledtom.antenna.core.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cryptography {
	public static String codify(String password) {
		byte messageDigest[] = null;
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			messageDigest = algorithm.digest(password.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		 
		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
		  hexString.append(String.format("%02X", 0xFF & b));
		}
		return hexString.toString();
	}
}
