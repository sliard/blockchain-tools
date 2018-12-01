package com.blockchain.tools;

import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.asymmetric.ec.EC5Util;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;

public class KeyUtils {

    private static final String ALGORITHM = "ECDSA";
    private static final String RANDOM_ALGORITHM = "SHA1PRNG";
    private static final String PROVIDER = "BC"; // bouncy castle
    private static final String CURVE = "secp256k1";

    static {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    /**
     * Generate a random KeyPair
     * @return KeyPair with a private key and a public key
     */
    public static KeyPair generateRandomKeyPair() {
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            SecureRandom random = SecureRandom.getInstance(RANDOM_ALGORITHM);
            ECGenParameterSpec ecSpec = new ECGenParameterSpec(CURVE);
            keyGen.initialize(ecSpec, random);
            return keyGen.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Converts an encoded private key in byte[] into a new ECPrivateKey object.
     *
     * @param encodedPrivateKey encoded private key
     * @return private key
     */
    public static ECPrivateKey getPrivateKey(byte[] encodedPrivateKey) {
        // If the encoded private key has a negative value, this means that it originally
        // began with a zero byte which was stripped off during the encoding process. We
        // must now restore this leading zero byte.
        BigInteger privateKeyDValue = new BigInteger(encodedPrivateKey);
        if (privateKeyDValue.signum() < 1) {
            byte[] privateKeyFinalBytes = new byte[encodedPrivateKey.length+1];
            privateKeyFinalBytes[0] = (byte) 0;
            System.arraycopy(encodedPrivateKey, 0, privateKeyFinalBytes, 1, encodedPrivateKey.length);
            privateKeyDValue = new BigInteger(privateKeyFinalBytes);
        }

        ECPrivateKey ecPrivateKey = null;
        try {
            ECNamedCurveParameterSpec params = ECNamedCurveTable.getParameterSpec(CURVE);
            KeyFactory fact = KeyFactory.getInstance(ALGORITHM, PROVIDER);
            ECCurve curve = params.getCurve();
            java.security.spec.EllipticCurve ellipticCurve = EC5Util.convertCurve(curve, params.getSeed());
            java.security.spec.ECParameterSpec params2 = EC5Util.convertSpec(ellipticCurve, params);
            java.security.spec.ECPrivateKeySpec keySpec = new java.security.spec.ECPrivateKeySpec(privateKeyDValue, params2);
            ecPrivateKey = (ECPrivateKey) fact.generatePrivate(keySpec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ecPrivateKey;
    }


    public static ECPrivateKey privateKeyFromWIF(String wifPrivateKey) {
        byte[] privateKeyBytes = HashUtils.base58Decode(wifPrivateKey);
        byte[] privateKeyWithoutChecksum = Arrays.copyOfRange(privateKeyBytes, 0, privateKeyBytes.length - 4);
        byte[] checksum = Arrays.copyOfRange(privateKeyBytes,  (privateKeyBytes.length - 4), privateKeyBytes.length);
        byte[] hashOfPrivateKey = HashUtils.applySha256Twice(privateKeyWithoutChecksum, 0, privateKeyWithoutChecksum.length);
        byte[] testChecksum = Arrays.copyOfRange(hashOfPrivateKey, 0, 4);

        if (!Arrays.equals(checksum, testChecksum)) {
            throw new IllegalArgumentException("Bad wifPrivateKey checksum");
        }

        // Check that the prepended 128 byte is in place
        if (privateKeyWithoutChecksum[0] != (byte) 128) {
            throw new IllegalArgumentException("Invalid wifPrivateKey checksum");
        }

        // Drop the prepended 128 byte
        byte[] privateKeyFinalBytes = Arrays.copyOfRange(privateKeyWithoutChecksum, 1, privateKeyWithoutChecksum.length);

        // If the decoded private key has a negative value, this means that it originally
        // began with a zero byte which was stripped off during the encodeToWIF process. We
        // must now restore this leading zero byte.
        BigInteger privateKeyBigIntegerValue = new BigInteger(privateKeyFinalBytes);
        if (privateKeyBigIntegerValue.signum() < 1) {
            byte[] newPrivateKeyFinalBytes = new byte[privateKeyFinalBytes.length+1];
            newPrivateKeyFinalBytes[0] = (byte) 0;
            System.arraycopy(privateKeyFinalBytes, 0, newPrivateKeyFinalBytes, 1, privateKeyFinalBytes.length);
        }

        return getPrivateKey(privateKeyFinalBytes);
    }


    /**
     * Converts a private key in byte array form into a Bitcoin Wallet Import Format string.
     *
     * @param privateKey - The private key in byte[] format
     * @return A String representation of the private key in Wallet Import Format
     */
    public static String encodePrivateKeyToWIF (byte[] privateKey) {
        byte[] cleanPrivateKey;
        // If first byte of the private encryption key generated is zero, remove it.
        if (privateKey[0] == 0) {
            cleanPrivateKey = new byte[privateKey.length-1];
            System.arraycopy(privateKey, 1, cleanPrivateKey, 0, cleanPrivateKey.length);
        } else {
            cleanPrivateKey = privateKey;
        }

        // Prepend the decimal value 128 to the private key
        byte[] addressBytes = new byte[1 + cleanPrivateKey.length + 4];
        addressBytes[0] = (byte) 128;
        System.arraycopy(cleanPrivateKey, 0, addressBytes, 1, cleanPrivateKey.length);

        // Calculate the double SHA-256 hash
        byte[] checksum = HashUtils.applySha256Twice(addressBytes, 0, cleanPrivateKey.length + 1);

        // Take the first 4 bytes of that hash as a checksum for the private
        // and add the checksum bytes onto the end of the private key with its extra byte
        System.arraycopy(checksum, 0, addressBytes, cleanPrivateKey.length + 1, 4);
        return HashUtils.base58Encode(addressBytes);
    }

    public static ECPublicKey getPublicKeyFromPrivate(ECPrivateKey privateKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM, PROVIDER);
            ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec(CURVE);

            ECPoint Q = ecSpec.getG().multiply(privateKey.getS());
            byte[] publicDerBytes = Q.getEncoded();

            ECPoint point = ecSpec.getCurve().decodePoint(publicDerBytes);
            ECPublicKeySpec pubSpec = new ECPublicKeySpec(point, ecSpec);
            return (ECPublicKey) keyFactory.generatePublic(pubSpec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPublicKeyHexa(ECPublicKey pubicKey) {
        java.security.spec.ECPoint pt = pubicKey.getW();
        String sx = StringUtils.adjustTo64(pt.getAffineX().toString(16)).toUpperCase();
        String sy = StringUtils.adjustTo64(pt.getAffineY().toString(16)).toUpperCase();
        return "04" + sx + sy;
    }
}
