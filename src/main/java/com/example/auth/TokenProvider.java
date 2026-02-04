package com.example.auth;

/**
 * TokenProvider loads API tokens from system properties or environment variables.
 * Priority: system property `api.token` then environment variable `GOREST_TOKEN`.
 */
public final class TokenProvider {
    private TokenProvider() {}

    /**
     * Retrieve the configured token for API requests.
     * @return token string or null if not configured
     */
    public static String getToken() {
        String tokenFromProp = System.getProperty("api.token");
        String tokenFromEnv = System.getenv("GOREST_TOKEN");
        return (tokenFromProp != null && !tokenFromProp.isBlank()) ? tokenFromProp : tokenFromEnv;
    }
}