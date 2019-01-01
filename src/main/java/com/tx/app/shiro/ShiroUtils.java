package com.tx.app.shiro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 *  @ClassName ShiroUtils
 *  @Description Shiro工具类
 *  @Author Hardy
 *  @Date 2018年12月06日 14:29
 *  @Version 1.0.0
 *  
 **/
public class ShiroUtils {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(ShiroUtils.class);

    //  定义所有的字符组成的串
    public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //  定义所有的小写字符组成的串（不包括数字）
    public static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //  定义所有的数字字符组成的串
    public static final String numberChar = "0123456789";

    /** des加密key **/
    private static final String DES_KEY = "tianxia88";

    private static ShiroUtils shiroUtils = null;

    static{
        shiroUtils = new ShiroUtils();
    }

    /**
     * 功能描述:
     * DES加密工具类
     * @Author: Hardy
     * @Date: 2018年12月06日 14:31:25
     * @param encryptStr 待加密串
     * @return: java.lang.String
     **/
    public static String desEncrypt(String encryptStr)throws Exception{
        try {
            return shiroUtils.base64Encode(shiroUtils.desEncrypt(encryptStr.getBytes())).replaceAll("\\s*", "");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("DES加密异常:{}",e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 功能描述:
     * DES解密工具类
     * @Author: Hardy
     * @Date: 2018年12月06日 14:32:16
     * @param decryptStr
     * @return: java.lang.String
     **/
    public static String desDecrypt(String decryptStr)throws Exception{
        byte[] result = new byte[0];
        try {
            result = shiroUtils.base64Decode(decryptStr);
            return new String(shiroUtils.desDecrypt(result));
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("DES解密异常:{}",e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 功能描述:
     * DES加密
     * @Author: Hardy
     * @Date: 2018年12月06日 14:36:07
     * @param plainText
     * @return: byte[]
     **/
    private byte[] desEncrypt(byte[] plainText) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(DES_KEY.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key, sr);
        byte data[] = plainText;
        byte encryptedData[] = cipher.doFinal(data);
        return encryptedData;
    }

    /**
     * 功能描述:
     * DES解密
     * @Author: Hardy
     * @Date: 2018年12月06日 14:36:21
     * @param encryptText
     * @return: byte[]
     **/
    private byte[] desDecrypt(byte[] encryptText) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(DES_KEY.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key, sr);
        byte encryptedData[] = encryptText;
        byte decryptedData[] = cipher.doFinal(encryptedData);
        return decryptedData;
    }

    /**
     * 功能描述:
     * Byte数组转String类型
     * @Author: Hardy
     * @Date: 2018年12月06日 14:37:14
     * @param s
     * @return: java.lang.String
     **/
    private String base64Encode(byte[] s) throws Exception{
        if (s == null)
            return null;
        BASE64Encoder b = new sun.misc.BASE64Encoder();
        return b.encode(s);
    }

    /**
     * 功能描述:
     * String类型转Byte数组
     * @Author: Hardy
     * @Date: 2018年12月06日 14:37:31
     * @param s
     * @return: byte[]
     **/
    public byte[] base64Decode(String s) throws IOException {
        if (s == null) {
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(s);
        return b;
    }

    /**
     * 功能描述:
     * MD5加密
     * @Author: Hardy
     * @Date: 2018年12月07日 12:31:20
     * @param str
     * @return: java.lang.String
     **/
    public static String md5Encrypt(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] byteDigest = md.digest();
            int i;
            //字符数组转换成字符串
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < byteDigest.length; offset++) {
                i = byteDigest[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            // 32位加密
            return buf.toString().toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能描述:
     * 生成固定长度的随机串
     * @Author: Hardy
     * @Date: 2018年12月07日 12:35:10
     * @return: java.lang.String
     **/
    public static String generateString() {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            sb.append(allChar.charAt(random.nextInt(allChar.length())));
        }
        return sb.toString();
    }
}
