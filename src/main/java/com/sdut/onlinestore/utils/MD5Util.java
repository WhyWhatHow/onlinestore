package com.sdut.onlinestore.utils;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public  class MD5Util {

    private MD5Util() {
    }


    public static String getEncryption(String originString) throws UnsupportedEncodingException {
        String result = "";
        if (originString != null) {
            try {
// 指定加密的方式为MD5
                MessageDigest md = MessageDigest.getInstance("MD5");
                // 进行加密运算
                byte bytes[] = md.digest(originString.getBytes("ISO8859-1"));
                for (int i = 0; i < bytes.length; i++) {
                    // 将整数转换成十六进制形式的字符串 这里与0xff进行与运算的原因是保证转换结果为32位
                    String str = Integer.toHexString(bytes[i] & 0xFF);
                    if (str.length() == 1) {
                        str += "F";
                    }
                    result += str;
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String setUUID() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    @Test
    public void test() throws UnsupportedEncodingException {
        String encryption = MD5Util.getEncryption("123456789");
        System.out.println(encryption);
        System.out.println(encryption.length());
    }
}
