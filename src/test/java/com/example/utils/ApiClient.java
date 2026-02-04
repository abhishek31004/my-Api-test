package com.example.utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public final class ApiClient {

    private ApiClient() {
    }

    public static Response get(RequestSpecification spec, String path) {
        return given()
                .spec(spec)
                .when()
                .get(path)
                .then()
                .extract()
                .response();
    }

    public static Response post(RequestSpecification spec, String path, Object body) {
        return given()
                .spec(spec)
                .body(body)
                .when()
                .post(path)
                .then()
                .extract()
                .response();
    }
}