package de.cryptone.crypto;

public abstract class AbstCrypto {

	abstract String encrypt( final String key, final String message );
	abstract String decrypt( final String key, final String message );
	abstract String genertateKey();

}
