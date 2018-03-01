package ru.sberbank.jschool.homework.andreev.second;

public class CryptoLibrary {
    private int offset;

    public CryptoLibrary(int offset) {
        this.offset = offset;
    }

    public byte[] encrypt(byte[] toEncrypt) {
        byte[] result = new byte[toEncrypt.length];
        for (int i = 0; i < toEncrypt.length; i++) {
            result[i] = (byte) (toEncrypt[i] + offset);
        }
        return result;
    }

    public byte[] decrypt(byte[] toEncrypt) {
        byte[] result = new byte[toEncrypt.length];
        for (int i = 0; i < toEncrypt.length; i++) {
            result[i] = (byte) (toEncrypt[i] - offset);
        }
        return result;
    }
}
