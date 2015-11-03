package de.cryptone.crypto;

public class CryptFactor {
	
		public final static String 
						CRYPT_ASYM_RSA = "assymetric aka RSA";
		public final static String 
						CRYPT_ASYM_RSA_PBE = "assymetric aka RSA PBE";
		public final static String 
						CRYPT_SYM_AES = "symetric aka AES";
		public final static String 
						CRYPT_SYM_DES = "symetric aka DES";
		public final static String 
						CRYPT_SIGNATURE = "signature";
		public final static String 
						CRYPT_HASH ="hash";
		public final static String 
						CRYPT_AUTH_SRP_CLIENT = "authentification SRP:CLIENT";
		public final static String 
						CRYPT_AUTH_SRP_SERVER = "authentification SRP:SERVER";
		public final static String 
						CRYPT_AUTH_SIMPLE = "authentication password based";
		public final static String 
						CRYPT_AUTH_2FA = "authentication multifactor based";


	public static AbstCrypto getInstance(String cryptographietyp ){
		
		if( cryptographietyp == null ) return null;
		if( cryptographietyp.equalsIgnoreCase(CRYPT_ASYM_RSA)){
			return new RSACrypto();
		}
		else if( cryptographietyp.equalsIgnoreCase(CRYPT_ASYM_RSA_PBE) ){
			return new RSAPBECrypto();
		}
		else if( cryptographietyp.equalsIgnoreCase(CRYPT_SYM_AES) ){
			return new AESCrypto();
		}
		else if( cryptographietyp.equalsIgnoreCase(CRYPT_SYM_DES) ){
			return null; // not implements for now
		}
		else if( cryptographietyp.equalsIgnoreCase(CRYPT_SIGNATURE) ){
			return new Signature();
		}
		else if( cryptographietyp.equalsIgnoreCase(CRYPT_HASH) ){
			return null; // not implements for now
		}
		else if( cryptographietyp.equalsIgnoreCase(CRYPT_AUTH_SRP_CLIENT)){
		//	return new AuthClientSRP();
		}
		else if(  cryptographietyp.equalsIgnoreCase(CRYPT_AUTH_SIMPLE)){
			return null; //not implements for now
		}
		else if( cryptographietyp.equalsIgnoreCase(CRYPT_AUTH_2FA) ){
			return null; //not implements for now
		}
		return null;
	}

}
