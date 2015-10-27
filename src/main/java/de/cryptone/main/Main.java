package de.cryptone.main;

import com.google.gson.Gson;

import de.cryptone.crypto.AbstCrypto;
import de.cryptone.crypto.CryptFactor;

public class Main {


	public static void main(String[] args) throws Exception {
	CryptFactor factor = new CryptFactor();
	AbstCrypto rsa =  factor.getInstance( CryptFactor.CRYPT_ASYM_RSA );
	System.out.println( rsa.genertateKey() );
	}

	 

}
