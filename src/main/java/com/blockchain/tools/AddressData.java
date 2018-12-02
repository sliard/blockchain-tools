package com.blockchain.tools;

public class AddressData {

    /**
     * Address
     */
    public String address;

    /**
     * Public Key (130 characters [0-9A-F])
     */
    public String publicKey;

    /**
     * 51 characters base58, starts with a '5'
     */
    public String privateKeyWIF;

    /**
     * Private Key Hexadecimal Format (64 characters [0-9A-F])
     */
    public String privateKey;
}
