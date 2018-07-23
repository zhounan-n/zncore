package com.zn.encryption;

import com.zn.encryption.gm.SM2Utils;
import com.zn.encryption.gm.Util;
import org.apache.commons.lang3.StringUtils;

/**
 * 国密加密解密测试类
 * Created by zhoun on 2018/7/23.
 **/
public class GmTest {

    public String gmEncrypt(String publicKey, String data) {
        if (StringUtils.isBlank(publicKey)) {
            return "公钥为空";
        }
        if (StringUtils.isBlank(data)) {
            return "加密数据为空";
        }
        String msg = "";
        try {
            msg = SM2Utils.encrypt(Util.hexToByte(publicKey), data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    public String gmDecrypt(String privateKey, String encryptData) {
        if (StringUtils.isBlank(privateKey)) {
            return "私钥为空";
        }
        if (StringUtils.isBlank(encryptData)) {
            return "加密数据为空";
        }
        String d = "";
        try {
            d = new String(SM2Utils.decrypt(Util.hexToByte(privateKey), Util.hexToByte(encryptData)), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }
}
