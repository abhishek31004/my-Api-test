package com.example.client;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

/**
 * UsersClient provides convenient methods to interact with the `/users` API group.
 * Each method returns a RestAssured `Response` for flexible assertions in tests.
 */
public final class UsersClient {
    private UsersClient() {
    }

    private static final String ROOT = "/users";

    /**
     * Retrieve all users.
     * @param spec RequestSpecification to use (headers, baseUri, auth)
     * @return RestAssured Response with the HTTP response for further assertions
     */
    public static Response getAll(RequestSpecification spec) {
        return given()
                .spec(spec)
                .when()
                .get(ROOT)
                .then()
                .extract()
                .response();
    }

    /**
     * Retrieve users with query parameters (pagination/filtering).
     * @param spec RequestSpecification to use
     * @param queryParams query parameters map (e.g., page, per_page)
     * @return RestAssured Response
     */
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

    /**
     * Get a user by ID.
     * @param spec RequestSpecification to use
     * @param id user id
     * @return RestAssured Response (200 if found, 404 if not)
     */
    public static Response getById(RequestSpecification spec, int id) {
        return given()
                .spec(spec)
                .when()
                .get(ROOT + "/" + id)
                .then()
                .extract()
                .response();
    }

    /**
     * Create a new user.
     * @param spec RequestSpecification to use (must include Authorization)
     * @param body request body (POJO or map) representing user
     * @return RestAssured Response (201 on success, 422 validation errors, 401/403 auth errors)
     */
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

    /**
     * Full update (PUT) for a user by id.
     * @param spec RequestSpecification to use
     * @param id user id
     * @param body full user payload
     * @return RestAssured Response (200 on success)
     */
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

    /**
     * Partial update (PATCH) for a user by id.
     * @param spec RequestSpecification to use
     * @param id user id
     * @param body partial payload (e.g., status change)
     * @return RestAssured Response (200 on success)
     */
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

    /**
     * Delete user by id.
     * @param spec RequestSpecification to use
     * @param id user id to delete
     * @return RestAssured Response (204 on success, 404 if not found)
     */
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