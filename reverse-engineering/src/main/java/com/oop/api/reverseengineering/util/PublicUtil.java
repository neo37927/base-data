package com.oop.api.reverseengineering.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class PublicUtil {
    public PublicUtil() {
    }

    public static String getPorjectPath() {
        String nowpath = "";
        nowpath = System.getProperty("user.dir") + "/";
        return nowpath;
    }

    public static String getIp() {
        String ip = "";

        try {
            InetAddress inet = InetAddress.getLocalHost();
            ip = inet.getHostAddress();
        } catch (UnknownHostException var2) {
            var2.printStackTrace();
        }

        return ip;
    }
}
