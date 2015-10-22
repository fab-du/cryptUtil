package de.cryptone.crypto;

import java.math.BigInteger;
import java.util.HashMap;

import com.nimbusds.srp6.SRP6CryptoParams;
import com.nimbusds.srp6.SRP6VerifierGenerator;

public class AuthClientSRP  implements ICryptalgo {


		public void genertateVerifier( String username, String password ){
			SRP6CryptoParams config = SRP6CryptoParams.getInstance();

			// Create verifier generator
			SRP6VerifierGenerator gen = new SRP6VerifierGenerator(config);

			// Random 16 byte salt 's'
			BigInteger salt = new BigInteger(SRP6VerifierGenerator.generateRandomSalt());

			// Compute verifier 'v'
			BigInteger verifier = gen.generateVerifier(salt, username, password);
			HashMap<String, BigInteger> hm = new HashMap<String, BigInteger >(); 
			hm.put("salt", salt); 
			hm.put("verifier", verifier); 

			// To be continuous
	
		}

}
