package com.registrysystem.mobilesystem.constants;

public class Constants {
    private static String URL = "https://app-backendjava.herokuapp.com";
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
