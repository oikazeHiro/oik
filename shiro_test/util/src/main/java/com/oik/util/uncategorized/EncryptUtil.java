package com.oik.util.uncategorized;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;

public class EncryptUtil {
    private static final int HASH_BYTE_SIZE = 256;
    private static final int PBKDF2_ITERATIONS = 1000;
    private static final int SALT_LEN = 16;

    /**
     * @MethodName encode
     * @Description 密码加密
     * @Param password 密码
     * @Return java.lang.String
     * @Author Roc
     * @Date 2022/4/1 10:42
     **/
    public static String encode(String password) {
        try {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = skf.generateSecret(spec).getEncoded();
            byte[] bt3 = new byte[1 + salt.length + hash.length];
            System.arraycopy(salt, 0, bt3, 1, salt.length);
            System.arraycopy(hash, 0, bt3, 1 + salt.length, hash.length);
            return Base64.encodeBase64String(bt3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @MethodName matches
     * @Description 密码校验
     * @Param password 用户输入的密码
     * @Param pwHash    数据库存入的密码
     * @Return boolean  true密码正确，false密码错误
     * @Author Roc
     * @Date 2022/4/1 10:42
     **/
    public static boolean matches(String password, String pwHash) {
        try {
            byte[] salt = new byte[16];
            byte[] hashedPasswordBytes;

            try {
                hashedPasswordBytes = java.util.Base64.getDecoder().decode(pwHash);
            } catch (IllegalArgumentException e) {
                return false;
            }

            if (hashedPasswordBytes.length != (1 + SALT_LEN + HASH_BYTE_SIZE / 8) || hashedPasswordBytes[0] != 0x00) {
                return false;
            }

            System.arraycopy(hashedPasswordBytes, 1, salt, 0, salt.length);
            byte[] storedSubKey = new byte[HASH_BYTE_SIZE / 8];
            System.arraycopy(hashedPasswordBytes, 1 + salt.length, storedSubKey, 0, HASH_BYTE_SIZE / 8);

            byte[] generatedSubKey;
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            generatedSubKey = skf.generateSecret(spec).getEncoded();
            return Arrays.equals(storedSubKey, generatedSubKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
