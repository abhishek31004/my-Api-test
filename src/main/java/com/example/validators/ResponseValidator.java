package com.example.validators;

import io.restassured.response.Response;

import java.util.Objects;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/**
 * ResponseValidator contains convenience assertion helpers for API responses.
 * Methods throw AssertionError when validations fail so they can be used in both test and main code.
 */
public final class ResponseValidator {
    private ResponseValidator() {}

    /**
     * Validate the HTTP status equals expected.
     * @param response RestAssured Response
     * @param expected expected HTTP status code
     * @throws AssertionError when status does not match
     */
    public static void validateStatus(Response response, int expected) {
        int actual = response.getStatusCode();
        if (actual != expected) {
            throw new AssertionError("Expected status " + expected + " but was " + actual);
        }
    }

    /**
     * Validate the HTTP status is one of a set of allowed values.
     * @param response RestAssured Response
     * @param allowed allowed status codes
     * @throws AssertionError when status is not in allowed set
     */
    public static void validateStatusIsOneOf(Response response, int... allowed) {
        int status = response.getStatusCode();
        for (int s : allowed) {
            if (status == s) return;
        }
        StringBuilder msg = new StringBuilder("Status code was " + status + " but expected one of: ");
        for (int s : allowed) msg.append(s).append(' ');
        throw new AssertionError(msg.toString());
    }

    /**
     * Validate response body against a JSON Schema on the classpath.
     * @param response RestAssured Response
     * @param schemaClasspath classpath resource path for JSON schema
     */
    public static void validateSchema(Response response, String schemaClasspath) {
        response.then().assertThat().body(matchesJsonSchemaInClasspath(schemaClasspath));
    }

    /**
     * Validate a JSON field equals expected value.
     * @param response RestAssured Response
     * @param path JSON path to evaluate
     * @param expected expected value
     * @throws AssertionError when values do not match
     */
    public static void validateField(Response response, String path, Object expected) {
        Object actual = response.jsonPath().get(path);
        if (!Objects.equals(expected, actual)) {
            throw new AssertionError("Expected '" + expected + "' at '" + path + "' but was '" + actual + "'");
        }
    }
}