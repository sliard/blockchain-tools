package com.blockchain.tools;

import org.bouncycastle.crypto.digests.RIPEMD160Digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {

    /**
     * Get a SHA-256 MessageDigest instance.
     *
     * @return a SHA-256 MessageDigest instance
     */
    private static MessageDigest getSha256Digest() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Calculates the SHA-256 hash of the given byte array.
     *
     * @param input byte array to hash
     * @return the hash
     */
    public static byte[] applySha256(byte[] input){
        MessageDigest digest = getSha256Digest();
        return digest.digest(input);
    }

    /**
     * Calculates the SHA-256 hash of the given byte range.
     *
     * @param input byte array to hash
     * @param offset the offset within the array of the bytes to hash
     * @param length the number of bytes to hash
     * @return the hash
     */
    public static byte[] applySha256Twice(byte[] input, int offset, int length) {
        MessageDigest digest = getSha256Digest();
        digest.update(input, offset, length);
        return digest.digest(digest.digest());
    }

    /**
     * Computes the RIPEMD-160 hash of an array of bytes. Not instantiable.
     */
    public static byte[] applyRipemd160(byte[] input){
        byte[] payload = new byte[20];
        RIPEMD160Digest digest = new RIPEMD160Digest();
        digest.update(input, 0, input.length);
        digest.doFinal(payload, 0);
        return payload;
    }

}
