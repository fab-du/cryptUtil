package de.cryptoone.auth;

import java.math.BigInteger; 
import java.util.HashMap;

import com.nimbusds.*;
import com.nimbusds.srp6.SRP6CryptoParams;
import com.nimbusds.srp6.SRP6VerifierGenerator;


public class AuthClient {

	private String username;
	private String password;

	public AuthClient( String username , String password) {
		this.username = username; 
		this.password = password; 
	}
	
	public HashMap<String, BigInteger> generate(){
		SRP6CryptoParams config = SRP6CryptoParams.getInstance();

		// Create verifier generator
		SRP6VerifierGenerator gen = new SRP6VerifierGenerator(config);

		// Random 16 byte salt 's'
		BigInteger salt = new BigInteger(SRP6VerifierGenerator.generateRandomSalt());

		// Compute verifier 'v'
		BigInteger verifier = gen.generateVerifier(salt, username, password);
		HashMap<String, BigInteger> hm = new HashMap<>(); 
		hm.put("salt", salt); 
		hm.put("verifier", verifier); 
		return hm; 
	}
	
	
}
