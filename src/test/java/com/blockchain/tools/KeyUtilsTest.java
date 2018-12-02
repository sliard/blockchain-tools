package com.blockchain.tools;

import org.junit.Test;

import java.math.BigInteger;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

import static org.junit.Assert.assertEquals;

public class KeyUtilsTest {

    @Test
    public void generateRandomKeyPairTest() {
        // Just test no exception
        KeyUtils.generateRandomKeyPair();
    }

    @Test
    public void getPrivateKeyTest() {
        String key = "990f54a93d72e9ca0ae36f5e3d80982d85991e2a3d7b7bcedc96419f9f4cc380";
        BigInteger target = new BigInteger(key,16);
        ECPrivateKey result = KeyUtils.getPrivateKey(StringUtils.hexStringToByteArray(key));
        assertEquals(target, result.getS());
    }

    @Test
    public void privateKeyFromWIFTest() {
        ECPrivateKey key = KeyUtils.privateKeyFromWIF("5JyhHphaWwPcQMnZ14Vi4HkiRdhTt2wTA28vSDKEzFxQSn61qAW");
        BigInteger targetPrivateKey = new BigInteger("990f54a93d72e9ca0ae36f5e3d80982d85991e2a3d7b7bcedc96419f9f4cc380",16);
        assertEquals(targetPrivateKey, key.getS());
    }

    @Test
    public void encodePrivateKeyToWIFTest() {
        String key = "990f54a93d72e9ca0ae36f5e3d80982d85991e2a3d7b7bcedc96419f9f4cc380";
        String privateWIF = KeyUtils.encodePrivateKeyToWIF(StringUtils.hexStringToByteArray(key));
        assertEquals("5JyhHphaWwPcQMnZ14Vi4HkiRdhTt2wTA28vSDKEzFxQSn61qAW", privateWIF);
    }

    @Test
    public void getPublicKeyFromPrivateTest() {
        String key = "990f54a93d72e9ca0ae36f5e3d80982d85991e2a3d7b7bcedc96419f9f4cc380";
        ECPrivateKey privateKey = KeyUtils.getPrivateKey(StringUtils.hexStringToByteArray(key));
        ECPublicKey publicKey = KeyUtils.getPublicKeyFromPrivate(privateKey);
        String result = KeyUtils.getPublicKeyHexa(publicKey);
        assertEquals("045714720B7FE0AD759A3A2C300F1CE275AEBA7C9DDFCE661D722C7DD57DD25E4756D88932F8D6A49B35B2E844E0FAB4F230B238A9BD73505D557F631C320F9EBD", result);
    }
}
