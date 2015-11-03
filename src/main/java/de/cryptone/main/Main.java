package de.cryptone.main;

import java.util.Map;

import org.bouncycastle.util.encoders.Base64;

import com.google.gson.Gson;

import de.cryptone.crypto.RSAPBECrypto;
import de.cryptone.models.KeyPair;

public class Main {


	public static void main(String[] args) throws Exception {
	//CryptFactor factor = new CryptFactor();
	//AbstCrypto rsa =  factor.getInstance( CryptFactor.CRYPT_ASYM_RSA_PBE );

	RSAPBECrypto rsa = new RSAPBECrypto();

	KeyPair keypair =  rsa.genertateKey("passphrase");


	String enc =  rsa.encrypt(keypair.getPubkey(), "message");

	System.out.println( enc );
	
	System.out.println(  rsa.decrypt( keypair.getPrikey() , "passphrase",  keypair.getSalt(), enc)   );


//	String message_enc = rsa.encrypt(pair.getPubkey(), "message" );
//	String message_dec = rsa.decrypt(pair.getPrikey(), message_enc);
//
//	System.out.println( message_enc );
//	System.out.println( message_dec );
//	

	}

	 

}
