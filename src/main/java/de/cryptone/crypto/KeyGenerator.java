package de.cryptone.crypto;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

public class KeyGenerator {
	
	public static KeyPair generateKeyPair(){
	    KeyPairGenerator keygen; 
	    KeyPair keys;

	    try{
	        keygen = KeyPairGenerator.getInstance("RSA");
	        keygen.initialize( 1024 , new SecureRandom());

	        keys = keygen.generateKeyPair();

	        return keys;

	    }catch( Exception e ){
	        System.out.println( e.toString());
	        return null;
	    }
	}
}
