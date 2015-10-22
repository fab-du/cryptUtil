package de.cryptone.crypto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.google.gson.Gson;

import de.cryptone.models.PairKey;

public class RSACrypto extends AbstCrypto implements ICryptalgo{

	private KeyPair generatePairkey(){
		KeyPairGenerator keyPairGen = null;
		KeyPair keyPair=null;
		try {
			keyPairGen = KeyPairGenerator.getInstance("RSA");
			keyPairGen.initialize(1024, new SecureRandom());
			keyPair = keyPairGen.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return keyPair;
	}

	public String genertateKey(){
		Gson gson = new Gson();
		de.cryptone.models.PairKey pairkey = new PairKey();
		KeyPair keypair = this.generatePairkey();
		String prikey = this.privatekeyToString(keypair.getPrivate());
		String pubkey = this.publickeyToString(keypair.getPublic());
		pairkey.setPrikey(prikey);
		pairkey.setPubkey(pubkey);
		return gson.toJson(pairkey);
	}

	
	public String encrypt(final String key, final String message) {
		return this.encrypt( this.publickeyFromString(key)  , message);
	}

	private String encrypt(PublicKey key, String message) {
	      Cipher cipher = null;
	      byte[] raw = null;

			try {
				cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.ENCRYPT_MODE, key);
				byte[] stringBytes = message.getBytes();
				raw = cipher.doFinal(stringBytes);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}

		if( raw == null ){
			return null;
		}

	    return Base64.getEncoder().encodeToString(raw); 
	}
	
	public String decrypt(final String key , final String message ){
		return this.decrypt( this.privatekeyFromString(key), message);
	}

	private String decrypt(PrivateKey key , String message ){
		 Cipher cipher=null;
		 String clearText = null;
	
		try {

			  cipher = Cipher.getInstance("RSA");
			  cipher.init(Cipher.DECRYPT_MODE, key);
			  byte[] raw = Base64.getDecoder().decode(message);
		      byte[] stringBytes = cipher.doFinal(raw);
		      clearText = new String(stringBytes, "UTF8");
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		if( clearText == null ) 
			return null;
			return clearText;	
	}

	protected String privatekeyToString ( PrivateKey prikey ){
		byte[] encodedprikey = prikey.getEncoded();
		String base64String = null;
		try {
			base64String = new String( Base64.getEncoder().encode(encodedprikey), "UTF8" );
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return base64String;
	}

	protected String publickeyToString( PublicKey pubkey ){
		byte[] encodedpubkey = pubkey.getEncoded();
		String base64String = null;

		try {
			base64String = new String( Base64.getEncoder().encode(encodedpubkey), "UTF8" );
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return base64String;
	}

	protected PrivateKey privatekeyFromString( String prikey ){
		byte prikeybytes[] = Base64.getDecoder().decode( prikey.getBytes() );
		KeyFactory keyFactory = null;
		PrivateKey privatekey = null;
		try {
			keyFactory = KeyFactory.getInstance("RSA");
			KeySpec privateKeySpec = new PKCS8EncodedKeySpec(prikeybytes);
			privatekey = keyFactory.generatePrivate(privateKeySpec);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}

		return privatekey;
	}

	protected PublicKey publickeyFromString( String pubkey ){
		byte[] pubkeyBytes = Base64.getDecoder().decode( pubkey.getBytes());
		X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(pubkeyBytes);
		KeyFactory kf = null;
		PublicKey publickey = null; 
		try {
			kf = KeyFactory.getInstance("RSA");
			publickey = kf.generatePublic(X509publicKey);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}

		return publickey;
	}

}
