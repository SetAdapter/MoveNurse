package com.fy.des;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 加密 解密 工具类
 * Created by Alois on 2017/4/21.
 */
public class Des3 {

    // 密钥
    private static String secretKey = "012345670123456701234567";
    // 向量
    private final static String iv = "01234567";
    // 编码方式
    private final static String encoding = "utf-8";

    // static Des3 des3;
    //
    // private Des3(){}
    //
    // public static Des3 getDes3() {
    // if(null == des3){
    // des3 = new Des3();
    // }
    // return des3;
    // }
    //
    // public void setDecryptKey(String key) {
    // this.secretKey = key;
    // }

    /**
     * 3DES加密
     *
     * @param plainText
     *            普通文本
     * @return
     * @throws Exception
     */
    public static String encode(String plainText) {
        try {
            Key deskey = null;
            DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
            deskey = keyfactory.generateSecret(spec);
            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
            byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
            return Base64.encode(encryptData);
        } catch (Exception e) {
            System.out.println("数据加密异常");
            e.printStackTrace();
            return "";

        }
    }

    /**
     * 3DES解密
     *
     * @param encryptText
     *            加密文本
     * @return
     * @throws Exception
     */
    public static String decode(String encryptText) {
        try {
            Key deskey = null;
            DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
            deskey = keyfactory.generateSecret(spec);
            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
            byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));
            return new String(decryptData, encoding);
        } catch (Exception e) {
            System.out.println("数据解密异常");
            e.printStackTrace();
            return "";

        }

    }
}
