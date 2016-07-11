package com.admin.util;

/**
 * 加密器
 * 
 * @author hxw
 * 
 */
public interface Encrypter {
    /**
     * 设置密钥
     * 
     * @param secretKey
     *            密钥
     */
    public void setSecretKey(Object secretKey);

    /**
     * 加密
     * 
     * @param source
     *            加密源，明文
     * @return 加密结果，密文
     */
    public String encrypt(Object source);
}
