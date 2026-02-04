package com.example.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

/**
 * Factory for creating RestAssured RequestSpecification instances.
 * Centralizes headers, baseUri and optional Authorization header.
 */
public final class RequestSpecFactory {
    private RequestSpecFactory() {}

    /**
     * Create RequestSpecification with optional bearer token.
     * @param baseUri base URI for requests
     * @param token bearer token (nullable)
     * @return configured RequestSpecification
     */
    public static RequestSpecification createWithToken(String baseUri, String token) {
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .setRelaxedHTTPSValidation();

        if (token != null && !token.isBlank()) {
            builder.addHeader("Authorization", "Bearer " + token);
        }

        return builder.build();
    }

    /**
     * Create a RequestSpecification without Authorization header.
     * Useful for testing unauthenticated flows.
     * @param baseUri base URI
     * @return RequestSpecification
     */
    public static RequestSpecification createNoAuth(String baseUri) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .setRelaxedHTTPSValidation()
                .build();
    }
}