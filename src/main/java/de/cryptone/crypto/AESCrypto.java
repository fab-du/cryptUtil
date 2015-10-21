package de.cryptone.crypto;


import java.util.Base64;
import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.Key; 
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;



public class AESCrypto {
	
	public static String encrypt( String message, Key secretkey){
	      Cipher cipher = null; 
	      byte[] raw = null;
	      
	      try {
			cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE,secretkey);
		      byte[] stringBytes = message.getBytes();
		      raw = cipher.doFinal(stringBytes);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
	     
	    if( raw == null )
	    	return null;
	    	return Base64.getEncoder().encodeToString(raw); 
	}
	
	public  String decrypt( final String message, final Key secretkey) {
		 Cipher cipher = null;
		 String clearText = null;
		 byte[] stringBytes = null;
		 
		  try {
			  cipher = Cipher.getInstance("AES");
		      cipher.init(Cipher.DECRYPT_MODE, secretkey);
		      byte[] raw = Base64.getDecoder().decode(message);
		      stringBytes = cipher.doFinal(raw);
		      clearText= new String(stringBytes, "UTF8");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
			  e.printStackTrace();
		}

	      if( clearText == null )
	    	  return null;
	      	  return clearText;
	}
}
