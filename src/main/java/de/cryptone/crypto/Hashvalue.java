package de.cryptone.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashvalue implements ICryptalgo {
	
	public static byte[] calculerValeurDeHachage(String algorithme, String monMessage) {
		byte[] digest = null;
		try {
			MessageDigest sha = MessageDigest.getInstance(algorithme);
			sha.update(monMessage.getBytes());
			digest = sha.digest();
			System.out.println("algorithme : " + algorithme);
			System.out.println(bytesToHex(digest));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return digest;
	}
	
	public static String bytesToHex(byte[] b) {
		char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		StringBuffer buf = new StringBuffer();
		for (int j = 0; j < b.length; j++) {
			buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
			buf.append(hexDigit[b[j] & 0x0f]);
		}
		return buf.toString();
	}
}
