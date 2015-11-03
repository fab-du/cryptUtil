package de.cryptone.crypto;

public abstract class AbstCrypto {

	public abstract String encrypt( final String key, final String message );
	public abstract String decrypt( final String key, final String message );
	public abstract String generateKey();

}
