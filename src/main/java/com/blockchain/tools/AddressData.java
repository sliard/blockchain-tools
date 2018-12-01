package com.blockchain.tools;

public class AddressData {

    /**
     * A bitcoin address
     */
    public String bitcoinAddress;

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
