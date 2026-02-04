package com.example.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public final class RequestSpecFactory {
    private RequestSpecFactory() {}

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

    public static RequestSpecification createNoAuth(String baseUri) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .setRelaxedHTTPSValidation()
                .build();
    }
}