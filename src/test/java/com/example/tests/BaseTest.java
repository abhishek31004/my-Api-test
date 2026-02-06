package com.example.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

/**
 * BaseTest provides common test setup and RequestSpecifications used across tests.
 */
public abstract class BaseTest {
    protected static RequestSpecification requestSpec;
    protected static RequestSpecification noAuthSpec;
    protected static String baseUri;

    /**
     * Global setup executed before any tests run.
     * - Sets base URI
     * - Builds authenticated and unauthenticated RequestSpecifications
     */
    @BeforeAll
    public static void setup() {
        baseUri = "https://gorest.co.in/public/v2";

        // support token from system property or environment variable
        String tokenFromProp = System.getProperty("api.token");
        String tokenFromEnv = System.getenv("GOREST_TOKEN");
        String token = tokenFromProp != null && !tokenFromProp.isBlank() ? tokenFromProp : tokenFromEnv;

        // Build specs via RequestSpecFactory
        requestSpec = com.example.utils.RequestSpecFactory.createWithToken(baseUri, token);
        noAuthSpec = com.example.utils.RequestSpecFactory.createNoAuth(baseUri);

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}