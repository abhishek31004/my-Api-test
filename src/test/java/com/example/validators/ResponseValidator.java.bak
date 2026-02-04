package com.example.validators;

import io.restassured.response.Response;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class ResponseValidator {
    private ResponseValidator() {}

    public static void validateStatus(Response response, int expected) {
        assertEquals(expected, response.getStatusCode(), "Status code should match");
    }

    public static void validateStatusIsOneOf(Response response, int... allowed) {
        int status = response.getStatusCode();
        for (int s : allowed) {
            if (status == s) return;
        }
        String msg = "Status code was " + status + " but expected one of: ";
        for (int s : allowed) msg += s + " ";
        assertTrue(false, msg);
    }

    public static void validateSchema(Response response, String schemaClasspath) {
        response.then().assertThat().body(matchesJsonSchemaInClasspath(schemaClasspath));
    }

    public static void validateField(Response response, String path, Object expected) {
        Object actual = response.jsonPath().get(path);
        assertEquals(expected, actual);
    }
}