package com.blockchain.tools;

import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.util.Arrays;

public class AddressUtils {

    /**
     * Get a bitcoin address from a public key (Hexadecimal Format)
     *
     * @param publicKey public key (Hexadecimal Format)
     * @param network 0 for real bitcoin network and 111 for testnet
     * @return
     */
    public static String getBitcoinAddressFromPublicKey(String publicKey, int network) {
        byte[] publicEcdsaKey = StringUtils.hexStringToByteArray(publicKey);
        byte[] sha256Public = HashUtils.applySha256(publicEcdsaKey);
        byte[] payload = HashUtils.applyRipemd160(sha256Public);
        byte[] addressBytes = new byte[1 + payload.length + 4];
        addressBytes[0] = (byte) network;
        System.arraycopy(payload, 0, addressBytes, 1, payload.length);
        byte[] checksum = HashUtils.applySha256Twice(addressBytes, 0, payload.length + 1);
        System.arraycopy(checksum, 0, addressBytes, payload.length + 1, 4);
        return CoderUtils.base58Encode(addressBytes);
    }

    /**
     * Get a bitcoin address from a private key (Hexadecimal Format)
     *
     * @param privateKeyVal private key (Hexadecimal Format)
     * @param network 0 for real bitcoin network and 111 for testnet
     * @return
     */
    public static String getBitcoinAddressFromPrivateKey(String privateKeyVal, int network) {
        BigInteger pVal = new BigInteger(privateKeyVal, 16);
        byte[] bValue = pVal.toByteArray();
        bValue = Arrays.copyOfRange(bValue,bValue.length-(privateKeyVal.length()/2), bValue.length);
        ECPrivateKey privateKey = KeyUtils.getPrivateKey(bValue);
        ECPublicKey publicKey = KeyUtils.getPublicKeyFromPrivate(privateKey);
        String publicKeyHex = KeyUtils.getPublicKeyHexa(publicKey);
        return getBitcoinAddressFromPublicKey(publicKeyHex, network);
    }

    public static boolean checkBitcoinAddress(String address) {
        return false;
    }

    /**
     * Generate a random bitcoin address.
     *
     * @return all address informations with public and private key
     */
    public static AddressData generateBitcoinAddress() {
        KeyPair keyPair = KeyUtils.generateRandomKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        String publicKeyHex = KeyUtils.getPublicKeyHexa((ECPublicKey) publicKey);
        ECPrivateKey epriv = (ECPrivateKey) privateKey;

        AddressData result = new AddressData();
        result.publicKey = publicKeyHex;
        result.privateKey = StringUtils.adjustTo64(epriv.getS().toString(16));
        result.privateKeyWIF = KeyUtils.encodePrivateKeyToWIF(StringUtils.hexStringToByteArray(result.privateKey));
        result.address = getBitcoinAddressFromPublicKey(publicKeyHex, 0);

        return result;
    }

}
