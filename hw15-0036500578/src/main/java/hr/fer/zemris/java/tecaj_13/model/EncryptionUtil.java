package hr.fer.zemris.java.tecaj_13.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class used to encrypt users password
 * @author Josip Trbuscic
 *
 */
public class EncryptionUtil {
	
	/**
	 * valid hexadecimal digits
	 */
	private static final String validHex = new String("0123456789abcdef");

	/**
	 * Encrypts users password using SHA-1 algorithm
	 * @param s - string to be encrypted
	 * @return encrypted string
	 */
	public static String encrypt(String s) {
		MessageDigest md = null;
	    try {
	        md = MessageDigest.getInstance("SHA-1");
	    }
	    catch(NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    } 
	    return new String(byteToHex(md.digest(s.getBytes())));
	}
	
	
	/**
	 * Converts string of hexadecimal digits to array of bytes
	 * @param keyText - string of hexadecimal digits
	 * @return array of bytes representing given string
	 * @throws IllegalArgumentException if number of digits is not even
	 */
	public static byte[] hexToByte(String keyText) {
		if(!isValidHexString(keyText))
			throw new IllegalArgumentException("String cannot be interpreted as hex number");
		
		byte[] data = new byte[keyText.length() / 2];
		
		for(int i = 0, len = keyText.length(); i<len;i+=2) {
			data[i/2] = (byte) ((Character.digit(keyText.charAt(i), 16) << 4)
								+ Character.digit(keyText.charAt(i+1), 16));
		}
		
		return data;
	}
	
	/**
	 * Converts array of bytes to string of hexadecimal digits
	 * @param byteArray - array of bytes
	 * @return String of hexadecimal digits
	 */
	public static String byteToHex(byte[] byteArray) {
		char[] hex = new char[byteArray.length *2];
		
		for(int i = 0,indexHex = 0; i<byteArray.length; i++) {
			int first = (byteArray[i] & 0xF0) >>> 4;
			int second = byteArray[i] & 0xF;
			
			hex[indexHex] = validHex.charAt(first);
			hex[indexHex+1] = validHex.charAt(second);
			
			indexHex+=2;
		}
		
		return new String(hex);
	}
	
	/**
	 * Checks if given string can be converted to array of bytes and 
	 * if it contains valid hexadecimal digits
	 * @param string - string to be checked
	 * @return true if string is valid representation of hexadecimal 
	 * 			number and can be converted to array of bytes, false otherwise
	 */
	private static boolean isValidHexString(String string) {
		if(string.length() % 2 != 0) return false;
		
		boolean isHex = string.matches("^[0-9A-Fa-f]+$");
		return isHex;
	}
}
