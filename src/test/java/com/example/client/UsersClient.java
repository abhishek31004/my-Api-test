package com.example.client;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public final class UsersClient {
    private UsersClient() {
    }

    private static final String ROOT = "/users";

    public static Response getAll(RequestSpecification spec) {
        return given()
                .spec(spec)
                .when()
                .get(ROOT)
                .then()
                .extract()
                .response();
    }

    public static Response getAll(RequestSpecification spec, java.util.Map<String, Object> queryParams) {
        return given()
                .spec(spec)
                .queryParams(queryParams)
                .when()
                .get(ROOT)
                .then()
                .extract()
                .response();
    }

    public static Response getById(RequestSpecification spec, int id) {
        return given()
                .spec(spec)
                .when()
                .get(ROOT + "/" + id)
                .then()
                .extract()
                .response();
    }

    public static Response create(RequestSpecification spec, Object body) {
        return given()
                .spec(spec)
                .body(body)
                .when()
                .post(ROOT)
                .then()
                .extract()
                .response();
    }

    public static Response update(RequestSpecification spec, int id, Object body) {
        return given()
                .spec(spec)
                .body(body)
                .when()
                .put(ROOT + "/" + id)
                .then()
                .extract()
                .response();
    }

    public static Response patch(RequestSpecification spec, int id, Object body) {
        return given()
                .spec(spec)
                .body(body)
                .when()
                .patch(ROOT + "/" + id)
                .then()
                .extract()
                .response();
    }

    public static Response delete(RequestSpecification spec, int id) {
        return given()
                .spec(spec)
                .when()
                .delete(ROOT + "/" + id)
                .then()
                .extract()
                .response();
    }
}