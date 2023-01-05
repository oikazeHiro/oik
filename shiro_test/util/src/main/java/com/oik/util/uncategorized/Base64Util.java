package com.oik.util.uncategorized;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


/**
 * base64跑龙套
 *
 * @author LEAF
 * @date 2022-09-07
 */
public class Base64Util {

    /**
     * 加密JDK1.8
     */
    public static String encode(String str) {
        byte[] encodeBytes = Base64.getEncoder().encode(str.getBytes(StandardCharsets.UTF_8));
        return new String(encodeBytes);
    }

    public static String encode(byte[] str) {
        byte[] encodeBytes = Base64.getEncoder().encode(str);
        return new String(encodeBytes);
    }

    /**
     * 解密JDK1.8
     */
    public static String decode(String str) {
        byte[] decodeBytes = Base64.getDecoder().decode(str.getBytes(StandardCharsets.UTF_8));
        return new String(decodeBytes);
    }

    /**
     * 加密JDK1.8
     */
    public static String encodeThrowsException(String str) throws UnsupportedEncodingException {
        byte[] encodeBytes = Base64.getEncoder().encode(str.getBytes(StandardCharsets.UTF_8));
        return new String(encodeBytes);
    }

    /**
     * 解密JDK1.8
     */
    public static String decodeThrowsException(String str) throws UnsupportedEncodingException {
        byte[] decodeBytes = Base64.getDecoder().decode(str.getBytes(StandardCharsets.UTF_8));
        return new String(decodeBytes);
    }

}
