package com.oop.api.reverseengineering.util;

import java.io.File;

public class PathUtil {
    public PathUtil() {
    }

    public static String getPicturePath(String pathType, String pathCategory) {
        String strResult = "";
        StringBuffer strBuf = new StringBuffer();
        if (!"visit".equals(pathType) && "save".equals(pathType)) {
            String projectPath = PublicUtil.getPorjectPath().replaceAll("\\\\", "/");
            projectPath = splitString(projectPath, "bin/");
            strBuf.append(projectPath);
            strBuf.append("webapps/ROOT/");
        }

        strResult = strBuf.toString();
        return strResult;
    }

    private static String splitString(String str, String param) {
        String result = str;
        if (str.contains(param)) {
            int start = str.indexOf(param);
            result = str.substring(0, start);
        }

        return result;
    }

    public static String getClasspath() {
        String path = (Thread.currentThread().getContextClassLoader().getResource("") + "../../").replaceAll("file:/", "").replaceAll("%20", " ").trim();
        if (path.indexOf(":") != 1) {
            path = File.separator + path;
        }

        return path;
    }

    public static String getClassResources() {
        String path = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")).replaceAll("file:/", "").replaceAll("%20", " ").trim();
        if (path.indexOf(":") != 1) {
            path = File.separator + path;
        }

        return path;
    }
}
