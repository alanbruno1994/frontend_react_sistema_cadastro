package com.example.mobilesystem.constants;

public class Constants {
    private static String URL = "http://192.168.1.7:8080";
    private static String token;

    public static String getURL() {
        return URL;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Constants.token = token;
    }
}
