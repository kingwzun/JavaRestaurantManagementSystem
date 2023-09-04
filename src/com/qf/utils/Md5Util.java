package com.qf.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
    /*
     * 用来md5加密
     * 32为的字符串
     */
    public static String md5(String str){
        //生成md5加密摘要
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            //将string类型的数据加密转为int类型  再返回16位字符串
            String string = new BigInteger(1,md5.digest(str.getBytes())).toString(16);
            //BigInteger会省略0,补零补成32位
            return fillMd5(string);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
    public static String fillMd5(String md5){
        return md5.length()==32?md5:fillMd5("0"+md5);
    }

}
