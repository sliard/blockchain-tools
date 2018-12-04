[![CircleCI](https://circleci.com/gh/sliard/blockchain-tools.svg?style=svg)](https://circleci.com/gh/sliard/blockchain-tools)

# Blockchain Tools
When I started work on blockchain technology, I spent a lot of time to understand how bitcoin address are generated. I found a lot of article but not a lot of complete Java tutorial. So I decide to make one and I hope that can help you.

## Steps to create a bitcoin address

Start with a private ECDSA key

    C1274C36322EC0498BD9DCEBEAC5943F60091AFA94FFBDB57CFC94689B9A2723

Get the corresponding public ECDSA Key

    0468F9329163647F5A781000F33EC315E4A0B13463621E8CD22B844D6A92F237BE3A5B225C7E02B4F709381C3FDBC322184EE608FDFF6F8585F0ECF4D657DF4347

Perform SHA-256 hashing

    B785A660544ACF1B69B318C9659CDFDC11115B99AAA060F7BFC81EB441E46CAC

Perform RIPEMD-160 hashing

    A2FB7DAF095918123570ABAFF5E23994F54C3B9E

Adding network bytes (0x00 for main bitcoin network)

    00A2FB7DAF095918123570ABAFF5E23994F54C3B9E

Perform a first SHA-256 hash

    06F0494B973FAAB3406D52BFF1932A8E505384F0D929E792C0A140DE1AE03B3B

Perform a second SHA-256 hash

    0372DA9CAB538EB78626BC01328F7640A2A8F7E0CBB68352A2828FDE841F021D

Take first four bytes to get checksum

    0372DA9C

Add checksum to RIPEMD-160 hashing with network

    00A2FB7DAF095918123570ABAFF5E23994F54C3B9E0372DA9C

Encode with Base58 to get bitcoin address

    1FrmjyWGYBCRhrzTR28ZUHjHK4FfmBhicB

## References

Bitseal is a Bitmessage client for Android where you can find code for Key conversion : https://github.com/JonathanCoe/bitseal
 
Bitcoinj library is a Java implementation of the Bitcoin protocol : https://github.com/bitcoinj/bitcoinj

Documentation about bitcoin protocol : https://en.bitcoin.it/wiki/Protocol_documentation

A simple test page that describe all steps of bitcoin address generation : http://gobittest.appspot.com/Address

A bitcoin address generator : https://www.bitaddress.org
