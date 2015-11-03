package de.cryptone.crypto;

import de.cryptone.models.KeyPair;

/*
 * AbstCryptoPB : Abstraction for password/passphrase based encryption
 */
public abstract class AbstCryptoPB extends AbstCrypto {
	public abstract String decrypt( final String encrpyted_key, final String passphrase, final String salt, final String message );
	public abstract KeyPair generateKey( String passphrase );
}
