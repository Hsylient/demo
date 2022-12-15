package com.novax.ex.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Description: 待整理
 *
 * @author my.miao
 * @date 2021/12/23 10:29
 */
public class AesUtil {
    /*
     * 加密用的Key 可以用26个字母和数字组成 使用AES-128-CBC加密模式，key需要为16位。
     */
    private static final String key="hj3b58H$yuBI0258";
    private static final String iv ="NIfb&36GUY46Gyhn";
    /**
     * @author miracle.qu
     * @Description AES算法加密明文
     * @param data 明文
//     * @param key 密钥，长度16
//     * @param iv 偏移量，长度16
     * @return 密文
     */
    public static String encryptAES(String data) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;

            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());  // CBC模式，需要一个向量iv，可增加加密算法的强度

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return AesUtil.encode(encrypted).trim(); // BASE64做转码。

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @author miracle.qu
     * @Description AES算法解密密文
     * @param data 密文
//     * @param key 密钥，长度16
//     * @param iv 偏移量，长度16
     * @return 明文
     */
    public static String decryptAES(String data) {
        try
        {
            byte[] encrypted1 = AesUtil.decode(data);//先用base64解密

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString.trim();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 编码
     * @param byteArray
     * @return
     */
    public static String encode(byte[] byteArray) {
        return Base64.getEncoder().encodeToString(byteArray);
    }

    /**
     * 解码
     * @param base64EncodedString
     * @return
     */
    public static byte[] decode(String base64EncodedString) {
        return Base64.getDecoder().decode(base64EncodedString);
    }
}
