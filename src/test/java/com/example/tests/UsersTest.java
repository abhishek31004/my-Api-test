package com.example.tests;

import com.example.client.UsersClient;
import com.example.models.UserRequest;
import com.example.utils.RandomUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import com.example.validators.ResponseValidator;

/**
 * Integration tests for Users endpoints. Covers CRUD and negative scenarios against the configured API.
 * Tests that require authentication are skipped automatically when no token is provided.
 */
public class UsersTest extends BaseTest {

    private boolean hasToken() {
        return System.getProperty("api.token") != null || System.getenv("GOREST_TOKEN") != null;
    }

    private int createUserForTest() {
        UserRequest u = new UserRequest("Test User", "male", RandomUtils.uniqueEmail(), "active");
        Response res = UsersClient.create(requestSpec, u);
        assertEquals(201, res.getStatusCode());
        return res.jsonPath().getInt("id");
    }

    /**
     * Create user with valid payload — expects 201 and schema validation.
     */
    @Test
    public void createUser_valid_returns201() {
        Assumptions.assumeTrue(hasToken(), "No token provided; skipping");

        UserRequest u = new UserRequest("Create Valid", "female", RandomUtils.uniqueEmail(), "active");
        Response res = UsersClient.create(requestSpec, u);

        assertEquals(201, res.getStatusCode());
        assertEquals(u.getEmail(), res.jsonPath().getString("email"));
        // validate schema
        ResponseValidator.validateSchema(res, "schemas/user-schema.json");
    }

    /**
     * Creating user with missing mandatory field (email) should return 422 and include error for email.
     */
    @Test
    public void createUser_missingEmail_returns422() {
        Assumptions.assumeTrue(hasToken(), "No token provided; skipping");

        UserRequest u = new UserRequest("No Email", "female", null, "active");
        Response res = UsersClient.create(requestSpec, u);

        assertEquals(422, res.getStatusCode());
        assertTrue(res.asString().contains("email"));
    }

    /**
     * Creating user with invalid email format should return 422 and include email validation error.
     */
    @Test
    public void createUser_invalidEmail_returns422() {
        Assumptions.assumeTrue(hasToken(), "No token provided; skipping");

        UserRequest u = new UserRequest("Bad Email", "male", "bad-email-format", "active");
        Response res = UsersClient.create(requestSpec, u);

        assertEquals(422, res.getStatusCode());
        assertTrue(res.asString().contains("email"));
    }

    /**
     * Creating user without token should be rejected with 401 or 403.
     */
    @Test
    public void createUser_withoutToken_returns401or403() {
        UserRequest u = new UserRequest("No Token", "male", RandomUtils.uniqueEmail(), "active");
        Response res = UsersClient.create(noAuthSpec, u);

        int status = res.getStatusCode();
        assertTrue(status == 401 || status == 403);
    }

    /**
     * Get user by valid id (created during test) should return 200 and correct id.
     */
    @Test
    public void getUserById_valid_returns200() {
        Assumptions.assumeTrue(hasToken(), "No token provided; skipping");

        int id = createUserForTest();
        Response res = UsersClient.getById(requestSpec, id);

        assertEquals(200, res.getStatusCode());
        assertEquals(id, res.jsonPath().getInt("id"));
    }

    /**
     * Verify listing users with pagination query params returns 200 or 403 (if auth required).
     */
    @Test
    public void getUsers_withPagination_returns200() {
        java.util.Map<String, Object> params = new java.util.HashMap<>();
        params.put("page", 2);
        params.put("per_page", 5);

        Response res = UsersClient.getAll(noAuthSpec, params);
        assertTrue(res.getStatusCode() == 200 || res.getStatusCode() == 403);
    }

    /**
     * Requesting a non-existing user id yields 404 (or 403 if unauthenticated).
     */
    @Test
    public void getUserById_invalid_returns404Or403() {
        Response res = UsersClient.getById(requestSpec, 99999999);
        int status = res.getStatusCode();
        // unauthenticated requests can return 403; authenticated requests return 404 for missing IDs
        assertTrue(status == 404 || status == 403, "Expected 404 or 403 but was: " + status);
    }

    /**
     * Full update (PUT) should replace fields and return 200.
     */
    @Test
    public void fullUpdate_put_returns200() {
        Assumptions.assumeTrue(hasToken(), "No token provided; skipping");

        int id = createUserForTest();
        UserRequest update = new UserRequest("Updated Name", "male", RandomUtils.uniqueEmail(), "inactive");

        Response res = UsersClient.update(requestSpec, id, update);
        assertEquals(200, res.getStatusCode());
        assertEquals("Updated Name", res.jsonPath().getString("name"));
    }

    /**
     * Partial update (PATCH) to modify only status field should return 200 and updated field.
     */
    @Test
    public void partialUpdate_patch_status_only_returns200() {
        Assumptions.assumeTrue(hasToken(), "No token provided; skipping");

        int id = createUserForTest();
        Map<String, Object> patch = new HashMap<>();
        patch.put("status", "inactive");

        Response res = UsersClient.patch(requestSpec, id, patch);
        assertEquals(200, res.getStatusCode());
        assertEquals("inactive", res.jsonPath().getString("status"));
    }

    /**
     * Delete a valid user should return 204 and subsequent GET must return 404.
     */
    @Test
    public void deleteUser_valid_returns204() {
        Assumptions.assumeTrue(hasToken(), "No token provided; skipping");

        int id = createUserForTest();
        Response res = UsersClient.delete(requestSpec, id);
        assertEquals(204, res.getStatusCode());

        Response get = UsersClient.getById(requestSpec, id);
        assertEquals(404, get.getStatusCode());
    }

    /**
     * Deleting a non-existent user should return 404.
     */
    @Test
    public void deleteUser_invalid_returns404() {
        Assumptions.assumeTrue(hasToken(), "No token provided; skipping");

        Response res = UsersClient.delete(requestSpec, 99999999);
        assertEquals(404, res.getStatusCode());
    }
}