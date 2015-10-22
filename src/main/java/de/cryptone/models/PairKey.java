package de.cryptone.models;

public class PairKey {

	String pubkey;
	String prikey;
	public String getPubkey() {
		return pubkey;
	}
	public void setPubkey(String pubkey) {
		this.pubkey = pubkey;
	}
	public String getPrikey() {
		return prikey;
	}
	public void setPrikey(String prikey) {
		this.prikey = prikey;
	}

	@Override
	public String toString() {
		return "PairKey [pubkey=" + pubkey + ", prikey=" + prikey + "]";
	}

}
