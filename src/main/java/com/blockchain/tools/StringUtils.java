package com.blockchain.tools;

/**
 * All helper to manage String and byte[]
 */
public class StringUtils {

    /**
     * Change a byte array to a String with Hex value
     * @param bytes input array of values
     * @return String value of input in Hex
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b: bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }


    /**
     * Change a String with a hexadecimal value into a byte array
     * @param inputString String containing a hexadecimal value
     * @return a byte array represent the input String hexadecimal values
     */
    public static byte[] hexStringToByteArray(String inputString) {
        int len = inputString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(inputString.charAt(i), 16) << 4)
                    + Character.digit(inputString.charAt(i+1), 16));
        }
        return data;
    }

    public static String adjustTo64(String s) {
        switch(s.length()) {
            case 62: return "00" + s;
            case 63: return "0" + s;
            case 64: return s;
            default:
                throw new IllegalArgumentException("not a valid key: " + s);
        }
    }
}
