package com.blockchain.tools;

import org.bouncycastle.crypto.digests.RIPEMD160Digest;

public class AddressUtils {

    public String getBitcoinAddress(String publicKey, int network) {
        String bcPub = "04" + publicKey;
        byte[] publicEcdsaKey = StringUtils.hexStringToByteArray(bcPub);
        byte[] sha256Public = HashUtils.applySha256(publicEcdsaKey);

        RIPEMD160Digest digest = new RIPEMD160Digest(); // "RIPEMD160"
        digest.update(sha256Public, 0, sha256Public.length);
        byte[] payload = new byte[20];
        digest.doFinal(payload, 0);

        byte[] addressBytes = new byte[1 + payload.length + 4];
        addressBytes[0] = (byte) network;
        System.arraycopy(payload, 0, addressBytes, 1, payload.length);
        byte[] checksum = HashUtils.hashSha256Twice(addressBytes, 0, payload.length + 1);
        System.arraycopy(checksum, 0, addressBytes, payload.length + 1, 4);
        return HashUtils.base58Encode(addressBytes);
    }

    public boolean checkBitcoinAddress(String address) {
        return false;
    }
}
