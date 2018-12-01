package com.blockchain.tools;

import org.junit.Test;

import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

public class AddressUtilsTest {

//    public static final byte[] SATOSHI_KEY = Utils.HEX.decode("04fc9702847840aaf195de8442ebecedf5b095cdbb9bc716bda9110971b28a49e0ead8564ff0db22209e0374782c093bb899692d524e9d6a6956e7c5ecbcd68284");

    @Test
    public void testGenAddress() {

        AddressData data = AddressUtils.generateBitcoinAddress();

        System.out.println("Bitcoin Address : "+data.bitcoinAddress);
        System.out.println("Public Key : "+data.publicKey);
        System.out.println("Private Key : "+data.privateKey);
        System.out.println("Private Key WIF : "+data.privateKeyWIF);
    }

    @Test
    public void testDecodeWIF() {
        ECPrivateKey key = KeyUtils.privateKeyFromWIF("5JyhHphaWwPcQMnZ14Vi4HkiRdhTt2wTA28vSDKEzFxQSn61qAW");

        ECPublicKey publicKey = KeyUtils.getPublicKeyFromPrivate(key);

        System.out.println("key = "+key);
    }
}