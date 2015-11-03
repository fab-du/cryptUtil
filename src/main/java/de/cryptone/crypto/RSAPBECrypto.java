package de.cryptone.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.google.gson.Gson;

import de.cryptone.models.KeyPair;

public class RSAPBECrypto extends AbstCryptoPB{

	RSACrypto rsa = new RSACrypto();
	AESCrypto aes = new AESCrypto();

	@Override
	public String decrypt(String encrpyted_key, String passphrase, String saltarg, String message) {
		byte [] salt = Base64.getDecoder().decode(saltarg);

		String secretkey = this.passphraseToKey(passphrase, salt);
		String key = aes.decrypt(secretkey, encrpyted_key);
		return rsa.decrypt(key, message);
	}

	@Override
	public KeyPair genertateKey(String passphrase) {
		de.cryptone.models.KeyPair pairkey = new Gson().fromJson(rsa.genertateKey(), KeyPair.class);
		byte[] salt = this.generateSalt();
		String secretkey = this.passphraseToKey(passphrase, salt);
		String encrypted_prikey =  aes.encrypt(secretkey, pairkey.getPrikey());

		System.out.println( secretkey );

		pairkey.setPrikey(encrypted_prikey);
		pairkey.setSalt( Base64.getEncoder().encodeToString(salt));
		return pairkey;
	}

	@Override
	public String encrypt(String key, String message) {
		return rsa.encrypt(key, message);
	}

	@Override
	public String decrypt(String key, String message) {
		return rsa.decrypt(key, message);
	}

	@Override
	public String genertateKey() {
		return rsa.genertateKey();
	}

	private String passphraseToKey( String passphrase, byte[] salt ){
		  String algorithm = "PBKDF2WithHmacSHA1";
		  // SHA-1 generates 160 bit hashes, so that's what makes sense here
		  int derivedKeyLength = 256; //160;
		  int iterations = 20000;
		  KeySpec spec = new PBEKeySpec(passphrase.toCharArray(), salt, iterations, derivedKeyLength);
		  SecretKeyFactory f;
		  byte []ret =  null; 

		try {
			f = SecretKeyFactory.getInstance(algorithm);
		    ret = f.generateSecret(spec).getEncoded();
		} catch (Exception e) {
			return null;
		}

		if(ret != null) return Base64.getEncoder().encodeToString(ret);
		return null;
		 
	}

	private byte[] generateSalt() {
		SecureRandom random;
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
			byte[] salt = new byte[8];
			random.nextBytes(salt);
			return salt;
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	
	}

}
