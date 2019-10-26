package com.hackthon.m100u.CarbonMarketAPI.domain.utils;

import org.springframework.util.DigestUtils;

public class PasswordUtils {

    public String saltPassword(long salt, String password){
        return generateHash(salt+password+salt);
    }

    private static String generateHash(String key) {
        return DigestUtils.md5DigestAsHex(key.getBytes()).toUpperCase();
    }
}
