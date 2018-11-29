package com.blockchain.tools;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class KeyUtils {

    /**
     * Generate a random KeyPair
     * @return KeyPair with a private key and a public key
     * @throws NoSuchProviderException if the specified provider is not registered in the security provider list.
     * @throws NoSuchAlgorithmException if the implementation for the specified algorithm is not available from the specified provider.
     * @throws InvalidAlgorithmParameterException if the given parameters are inappropriate for this key pair generator.
     */
    public KeyPair generateRandomKeyPair() throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
        keyGen.initialize(ecSpec, random);
        return keyGen.generateKeyPair();
    }
}
