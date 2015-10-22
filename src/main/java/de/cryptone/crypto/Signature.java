package de.cryptone.crypto;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;

public class Signature extends RSACrypto implements ICryptalgo{

	private String sign(PrivateKey key, String message ){
		java.security.Signature signature = null;
		byte[] signatureBytes = null;
		
			try {
				signature = java.security.Signature.getInstance("SHA1withRSA");
				signature.initSign(key, new SecureRandom());
				signature.update(message.getBytes());
				signatureBytes = signature.sign();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (SignatureException e) {
				e.printStackTrace();
			}

		if( signature == null )
			return null;
			return bytesToHex(signatureBytes);
	}

	public String sign( String privatekey, String message ){
		return this.sign( this.privatekeyFromString(privatekey) , message);
	}
	
	private boolean verify(PublicKey key, String message,String signatureBytes){
		java.security.Signature signature = null;
		boolean result = false;
		
			try {
				signature = java.security.Signature.getInstance("SHA1withRSA");
				signature.initVerify(key);
				signature.update(message.getBytes());
				result = signature.verify(hexStringToByteArray(signatureBytes));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (SignatureException e) {
				e.printStackTrace();
			}
		
		return result;
	}

	public boolean verify(String publickey, String message,String signatureBytes){
		return this.verify( this.publickeyFromString(publickey) , message, signatureBytes);
	}
	
	private String bytesToHex(byte[] b) {
		char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		StringBuffer buf = new StringBuffer();
		for (int j = 0; j < b.length; j++) {
			buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
			buf.append(hexDigit[b[j] & 0x0f]);
		}
		return buf.toString();
	}
	
	private byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
					.digit(s.charAt(i + 1), 16));
		}
		return data;
	}
}
