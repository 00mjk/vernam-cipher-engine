package org.enjekt.cipher.vernam.engine.api;

public class StringWrapper {

    private final String encryptedText;
    private final int[] encryptionKeys;

    public StringWrapper(String encryptedText, int[] keychars) {
        this.encryptedText = encryptedText;
        this.encryptionKeys = keychars;
    }

    public String getEncryptedText() {
        return encryptedText;
    }

    public int[] getEncryptionKeys() {
        return encryptionKeys;
    }
}
