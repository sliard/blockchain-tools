package com.blockchain.tools;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class StringUtilsTest {

    @Test
    public void bytesToHexTest() {
        String hexVal = "0123e34f".toUpperCase();
        byte[] bValue = StringUtils.hexStringToByteArray(hexVal);
        String resultValue = StringUtils.bytesToHex(bValue).toUpperCase();
        assertEquals(hexVal, resultValue);

        hexVal = "ffffffff".toUpperCase();
        bValue = StringUtils.hexStringToByteArray(hexVal);
        resultValue = StringUtils.bytesToHex(bValue).toUpperCase();
        assertEquals(hexVal, resultValue);

        hexVal = "00".toUpperCase();
        bValue = StringUtils.hexStringToByteArray(hexVal);
        resultValue = StringUtils.bytesToHex(bValue).toUpperCase();
        assertEquals(hexVal, resultValue);

        hexVal = "0450863AD64A87AE8A2FE83C1AF1A8403CB53F53E486D8511DAD8A04887E5B23522CD470243453A299FA9E77237716103ABC11A1DF38855ED6F2EE187E9C582BA6";
        bValue = StringUtils.hexStringToByteArray(hexVal);
        resultValue = StringUtils.bytesToHex(bValue).toUpperCase();
        assertEquals(hexVal, resultValue);
    }

    @Test
    public void hexStringToByteArrayTest() {
        String hexVal = "0123e34f";
        byte[] resultValue = StringUtils.hexStringToByteArray(hexVal);
        byte[] target = StringUtils.hexStringToByteArray(hexVal);
        assertArrayEquals(target, resultValue);

        hexVal = "00ffffffff";
        resultValue = StringUtils.hexStringToByteArray(hexVal);
        target = StringUtils.hexStringToByteArray(hexVal);
        assertArrayEquals(target, resultValue);

        hexVal = "00";
        resultValue = StringUtils.hexStringToByteArray(hexVal);
        target = StringUtils.hexStringToByteArray(hexVal);
        assertArrayEquals(target, resultValue);

        hexVal = "0450863AD64A87AE8A2FE83C1AF1A8403CB53F53E486D8511DAD8A04887E5B23522CD470243453A299FA9E77237716103ABC11A1DF38855ED6F2EE187E9C582BA6";
        resultValue = StringUtils.hexStringToByteArray(hexVal);
        target = StringUtils.hexStringToByteArray(hexVal);
        assertArrayEquals(target, resultValue);
    }

    @Test
    public void adjustTo64Test() {
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<62; i++) {
            sb.append("1");
        }
        String result = StringUtils.adjustTo64(sb.toString());
        assertEquals("00"+sb.toString(), result);

        sb = new StringBuffer();
        for(int i=0; i<63; i++) {
            sb.append("1");
        }
        result = StringUtils.adjustTo64(sb.toString());
        assertEquals("0"+sb.toString(), result);

        sb = new StringBuffer();
        for(int i=0; i<64; i++) {
            sb.append("1");
        }
        result = StringUtils.adjustTo64(sb.toString());
        assertEquals(sb.toString(), result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void adjustTo64Error1Test() {
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<61; i++) {
            sb.append("1");
        }
        StringUtils.adjustTo64(sb.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void adjustTo64Error2Test() {
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<65; i++) {
            sb.append("1");
        }
        StringUtils.adjustTo64(sb.toString());
    }

}
