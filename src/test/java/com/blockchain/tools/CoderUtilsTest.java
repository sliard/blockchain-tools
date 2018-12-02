package com.blockchain.tools;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CoderUtilsTest {

    @Test
    public void base58EncodeTest() {
        String hexVal = "00CEFCAED8D31E00239C1B48F33439301BEEB3EC3D1EC32099";
        String result = CoderUtils.base58Encode(StringUtils.hexStringToByteArray(hexVal));
        String target = "1KsSuEtQBqULx2tKp2ZKEZqhfqD7WArPRz";
        assertEquals(target, result);
    }

    @Test
    public void base58EncodeZeroStartTest() {
        String hexVal = "0000004504F291C3719B94E25DCD18A5460D164ECFB6E0256E";
        String result = CoderUtils.base58Encode(StringUtils.hexStringToByteArray(hexVal));
        String target = "111KjVV1qtrQjN1mS5X2XoCLWin164Rss";
        assertEquals(target, result);
    }

    @Test
    public void base58EncodeNaughtTest() {
        String result = CoderUtils.base58Encode(new byte[0]);
        assertEquals("", result);
    }

    @Test
    public void base58DecodeTest() {
        byte[] result = CoderUtils.base58Decode("137mYAGngiweCWzWsxXS9wyBjSvcutyaYj");
        String hexVal = "001737ECD6D7066DA42E2FBB11C986B5F95CB64549C507DC84";
        assertArrayEquals(StringUtils.hexStringToByteArray(hexVal), result);
    }

    @Test
    public void base58DecodeNaughtTest() {
        byte[] result = CoderUtils.base58Decode("");
        assertArrayEquals(new byte[0], result);
    }

    @Test
    public void base58EncodeDecodeTest() {
        String input = "137mYAGngiweCWzWsxXS9wyBjSvcutyaYj";
        byte[] resultDecode = CoderUtils.base58Decode(input);
        String result = CoderUtils.base58Encode(resultDecode);
        assertEquals(input, result);
    }
}
