package org.enjekt.cipher.vernam.engine.api;

public class ObjectWrapper {

	byte[] padded; byte[] pad;
	
	public ObjectWrapper() {}

	public ObjectWrapper(byte[] padded, byte[] pad) {
		this.padded=padded;
		this.pad=pad;
	}

	public byte[] getPadded() {
		return padded;
	}
	public void setPadded(byte[] padded) {
		this.padded = padded;
	}
	public byte[] getPad() {
		return pad;
	}
	public void setPad(byte[] pad) {
		this.pad = pad;
	}
}
