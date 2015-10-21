package de.cryptone.crypto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSACrypto {

	public KeyPair generatePairkey(){
		KeyPairGenerator keyPairGen = null;
		KeyPair keyPair=null;
		try {
			keyPairGen = KeyPairGenerator.getInstance("RSA");
			keyPairGen.initialize(1024, new SecureRandom());
			keyPair = keyPairGen.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return keyPair;
	}
	
	public String encrypt(PublicKey key, String message) {
	      Cipher cipher = null;
	      byte[] raw = null;
		try {
			cipher = Cipher.getInstance("RSA");
		      byte[] stringBytes = message.getBytes();
		      raw = cipher.doFinal(stringBytes);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		if( raw == null ){
			return null;
		}

	    return Base64.getEncoder().encodeToString(raw); 
	}
	
	public String decrypt(PrivateKey key , String message ){
		 Cipher cipher=null;
		 String clearText = null;
		try {
			cipher = Cipher.getInstance("RSA");
		      cipher.init(Cipher.DECRYPT_MODE, key);
		      byte[] raw = Base64.getDecoder().decode(message);
		      byte[] stringBytes = cipher.doFinal(raw);
		      clearText = new String(stringBytes, "UTF8");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if( clearText == null ) 
			return null;
			return clearText;	
	}
}
