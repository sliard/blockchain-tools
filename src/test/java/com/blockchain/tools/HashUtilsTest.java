package com.blockchain.tools;

import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class HashUtilsTest {

    @Test
    public void applySha256Test() {
        String hexVal = "0450863AD64A87AE8A2FE83C1AF1A8403CB53F53E486D8511DAD8A04887E5B23522CD470243453A299FA9E77237716103ABC11A1DF38855ED6F2EE187E9C582BA6";
        byte[] result = HashUtils.applySha256(StringUtils.hexStringToByteArray(hexVal));
        String targetHexVal = "600FFE422B4E00731A59557A5CCA46CC183944191006324A447BDB2D98D4B408";
        assertArrayEquals(StringUtils.hexStringToByteArray(targetHexVal), result);

        hexVal = "0403F090D837D891BF89429D718398E619631B85DC2FC8DE4EBADA9AB482D968D135F644412147F85D9BB30690B2F928B79DDAF4C3046FA0E1D4BF7B3CE159C1C4";
        result = HashUtils.applySha256(StringUtils.hexStringToByteArray(hexVal));
        targetHexVal = "7E483FA8D33495FB900500A2E20FB48458A866161065A2D1283A12E1C4871543";
        assertArrayEquals(StringUtils.hexStringToByteArray(targetHexVal), result);
    }

    @Test
    public void applySha256TwiceTest() {
        String hexVal = "00BFF4D6B086A08F2C97A5C159A5DB8CDAB2BA83BD";
        byte[] bValue = StringUtils.hexStringToByteArray(hexVal);
        byte[] result = HashUtils.applySha256Twice(bValue,0, bValue.length);
        String targetHexVal = "137404A399DACA6340BFFFFA1109171FA1CAA35B09F80C02DB18A5536187C492";
        assertArrayEquals(StringUtils.hexStringToByteArray(targetHexVal), result);

        hexVal = "0097BCD4932F871C99BA80829CAB3B224E265EE7F0";
        bValue = StringUtils.hexStringToByteArray(hexVal);
        result = HashUtils.applySha256Twice(bValue,0, bValue.length);
        targetHexVal = "2E338ABEEB05234EA0B9C9BD34F6F97C4F8A5A777C62D45792CD3960ACD163BC";
        assertArrayEquals(StringUtils.hexStringToByteArray(targetHexVal), result);
    }

    @Test
    public void applyRipemd160Test() {
        String hexVal = "106523E57094D76036DD14D43C9528AC4654471F0E96A1569BA8646DF1CF3C0B";
        byte[] bValue = StringUtils.hexStringToByteArray(hexVal);
        byte[] result = HashUtils.applyRipemd160(bValue);
        String targetHexVal = "7172B2ACC41B8A90B78B829585138A4E945333DD";
        assertArrayEquals(StringUtils.hexStringToByteArray(targetHexVal), result);

        hexVal = "D5A5CC15784B741AC39A54C123D9B62898DDDF83C0E82435AA2EFAC2A2F631B7";
        bValue = StringUtils.hexStringToByteArray(hexVal);
        result = HashUtils.applyRipemd160(bValue);
        targetHexVal = "CEFCAED8D31E00239C1B48F33439301BEEB3EC3D";
        assertArrayEquals(StringUtils.hexStringToByteArray(targetHexVal), result);
    }

}
