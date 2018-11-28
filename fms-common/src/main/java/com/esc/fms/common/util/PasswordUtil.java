package com.esc.fms.common.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Created by tangjie on 2016/11/28.
 */
public class PasswordUtil {

    public static String  execMD5Encode(String str)
    {
        return new Md5Hash(str,"Apple@772").toString();
    }

}
