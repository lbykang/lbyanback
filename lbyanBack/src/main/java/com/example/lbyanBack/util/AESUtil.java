package com.example.lbyanBack.util;

import java.util.Base64;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * AES 加密方法，是对称的密码算法(加密与解密的密钥一致)，这里使用最大的 256 位的密钥
 */
public class AESUtil {

    /** logger */
    private static final Logger logger = Logger.getAnonymousLogger();

    /**
     * 加密
     * @param content 原文
     * @param key 密钥
     * @param iv 偏移量
     * @return 密文，使用base64编码
     */
    public static String aesCbcEncrypt(String content, String key, String iv) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(DigestUtils.md5(key), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv.getBytes()));
            byte[] result = cipher.doFinal(content.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(result);
        } catch (Exception e) {
            logger.info("exception:" + e.toString());
            return null;
        }
    }

    /**
     * 解密
     * @param content 密文，使用base64编码
     * @param key 密钥
     * @param iv 偏移量
     * @return 原文
     */
    public static String aesCbcDecrypt(String content, String key, String iv) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(DigestUtils.md5(key), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv.getBytes()));
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(content));
            return new String(result);
        } catch (Exception e) {
            logger.info("exception:" + e.toString());
            return null;
        }
    }

    /**
     * 测试方法
     * @param args args
     */
    public static void main(String[] args) {
        String content = "{\"csrq\":\"1980-01-01\",\"xyjb\":\"无现有疾病\",\"yyqk\":\"无用药\",\"dh\":\"01-20190315-2-01-0003\",\"qtzysx\":\"无其他注意事项\",\"xb\":\"1\",\"lzswbbh\":\"084\",\"barylxfs\":\"77889900\",\"mz\":\"1\",\"lzsh\":\"西六04\",\"khdj\":\"3\",\"bar\":\"办案人1\",\"qsrq\":\"2019-03-15\",\"lzsj\":\"2019-03-15\",\"rybh\":\"2-01-01-20190315-03-011\",\"lzjssj\":\"2019-03-15\",\"baryzjh\":\"789456123\",\"badw\":\"01\"}";
        String key = "thunisoft320";
        String iv = "0000000000000000";

        logger.info("加密前：" + content);
        String encrypted = aesCbcEncrypt(content, key, iv);
        logger.info("加密后：" + encrypted);
        String decrypted = aesCbcDecrypt(encrypted, key, iv);
        logger.info("解密后：" + decrypted);
    }
}