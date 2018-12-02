package com.blockchain.tools;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class AddressUtilsTest {

    @Test
    public void generateBitcoinAddressTest() {
        // Just test exception
        AddressData data = AddressUtils.generateBitcoinAddress();
    }

    @Test
    public void getBitcoinAddressFromPublicKeyTest() {
        String publicKey = "0480160E8FD1AB420BA104D2991D1F4C71B834E0FA0C083BE0CA2FD5B481E48886DA4F75F13115280872F26DBEA79D4AB7C5EC2016FA1CEF115F7749A9FFBEED04";
        String address = AddressUtils.getBitcoinAddressFromPublicKey(publicKey, 0);
        assertEquals("17kDicvpQrXiYaxweP7FwTnqtTbbNQL4n5", address);

        publicKey = "0462DDBFBF5081B63FC45B412B14E6D830842CF29DB6849C2420A75E6CF385F79E0D47EB50C8E2E10BB816F279D7CAB86A8B5173956489663B2CC9B6A2C4DEAF51";
        address = AddressUtils.getBitcoinAddressFromPublicKey(publicKey, 0);
        assertEquals("1MFbpzkKgRt7Kgbpk5JPi1dKxyW2MyA3Kh", address);

        publicKey = "044536A1DB63C9995D1A4A8443F1FB1EF1208F40DC17A17329052A52397394186EDF9262206512B2D6861C196203D26F57431FF67F5EC34CB4B4DE87F80FDC7523";
        address = AddressUtils.getBitcoinAddressFromPublicKey(publicKey, 0);
        assertEquals("1C1LLmjp9p2m1XHU8P12xHQ2CrHMsKx8br", address);

        publicKey = "0490AAE673F5B33684AC5B648A531EAE0C9B86B3B7C40F3A3B67B69BF195ACB5110BA127D7031D0384CEEABDFC62D3DE69828F3AAE4FE699811467F8254BA9AC8E";
        address = AddressUtils.getBitcoinAddressFromPublicKey(publicKey, 0);
        assertEquals("1Jv5n71r1ncEVRqP8GupA3NbJpAFtQYSL4", address);

        publicKey = "04FC9702847840AAF195DE8442EBECEDF5B095CDBB9BC716BDA9110971B28A49E0EAD8564FF0DB22209E0374782C093BB899692D524E9D6A6956E7C5ECBCD68284";
        address = AddressUtils.getBitcoinAddressFromPublicKey(publicKey, 0);
        assertEquals("1AGRxqDa5WjUKBwHB9XYEjmkv1ucoUUy1s", address);
    }

    @Test
    public void getBitcoinAddressFromPrivateKeyTest() {
        String privateKey = "44B617CF980B9B9EAEA2D5D2EDDECB70109010951FD5A3583CD54C1158759321";
        String address = AddressUtils.getBitcoinAddressFromPrivateKey(privateKey, 0);
        assertEquals("1Jv5n71r1ncEVRqP8GupA3NbJpAFtQYSL4", address);

        privateKey = "5DFE20ED23E22149132D11BFF1298D67FBC965F54D0A526081D22A005DCE7A37";
        address = AddressUtils.getBitcoinAddressFromPrivateKey(privateKey, 0);
        assertEquals("1G3kcadeCSAdNbH8AQoWvn2XpTCvR8Kmpg", address);

        privateKey = "0582C6C844900BDA44FE9C4E6CE01F44B23F118C679CE11AB98A78562F1258CC";
        address = AddressUtils.getBitcoinAddressFromPrivateKey(privateKey, 0);
        assertEquals("124ec7ZKomz3ae9GfvUZDtZ7p9QScYPWMY", address);

        privateKey = "F4FFEBDF2EE6C84C27E89C9D48443822619C1A99EA45517E97B2652FD6B2F875";
        address = AddressUtils.getBitcoinAddressFromPrivateKey(privateKey, 0);
        assertEquals("1DdvU7YoAaoFYWHiKZ232exuGfpSntCHhK", address);
    }

    @Test
    public void checkBitcoinAddressTest() {
        assertFalse(AddressUtils.checkBitcoinAddress("BZbvjr"));
        assertFalse(AddressUtils.checkBitcoinAddress("i55"));
        assertFalse(AddressUtils.checkBitcoinAddress("124ec7ZKomz3ae9GfvUZDtZ7p9QScY"));
        assertFalse(AddressUtils.checkBitcoinAddress("124ec7ZKomz3ae9GfvUZDtZ7p9QScYP"));
        assertFalse(AddressUtils.checkBitcoinAddress("124ec7ZKomz3ae9GfvUZDtZ7p9QScYPW"));
        assertFalse(AddressUtils.checkBitcoinAddress("1Q1pE5vPGEEMqRcVRMbtBK842Y6Pzo6nJ9"));
        assertFalse(AddressUtils.checkBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62I"));
        assertTrue(AddressUtils.checkBitcoinAddress("3R2MPpTNQLCNs13qnHz89Rm82jQ27bAwft"));
        assertTrue(AddressUtils.checkBitcoinAddress("34QjytsE8GVRbUBvYNheftqJ5CHfDHvQRD"));
        assertTrue(AddressUtils.checkBitcoinAddress("3GsAUrD4dnCqtaTTUzzsQWoymHNkEFrgGF"));
        assertTrue(AddressUtils.checkBitcoinAddress("3GsAUrD4dnCqtaTTUzzsQWoymHNkEFrgGF"));
    }
}