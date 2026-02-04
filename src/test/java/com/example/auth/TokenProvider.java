package com.example.auth;

public final class TokenProvider {
    private TokenProvider() {}

    public static String getToken() {
        String tokenFromProp = System.getProperty("api.token");
        String tokenFromEnv = System.getenv("GOREST_TOKEN");
        return (tokenFromProp != null && !tokenFromProp.isBlank()) ? tokenFromProp : tokenFromEnv;
    }
}