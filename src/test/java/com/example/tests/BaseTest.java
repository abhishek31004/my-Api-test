package com.example.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * BaseTest provides common test setup and RequestSpecifications used across tests.
 */
public abstract class BaseTest {
    protected static RequestSpecification requestSpec;
    protected static RequestSpecification noAuthSpec;
    protected static String baseUri;

    /**
     * Global setup executed before any tests run.
     * - Loads `config.properties`
     * - Builds authenticated and unauthenticated RequestSpecifications
     */
    @BeforeAll
    public static void setup() {
        Properties props = new Properties();
        try (InputStream is = BaseTest.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is != null) {
                props.load(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        baseUri = props.getProperty("base.uri", "https://gorest.co.in/public/v2");

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