package com.example.tests;

import com.example.client.UsersClient;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Basic sanity tests for API availability and list endpoints.
 */
public class SampleGetTest extends BaseTest {

    /**
     * Basic sanity test - listing users should return 200 or 403 depending on environment auth rules.
     */
    @Test
    public void getUsers_returns200Or403() {
        // Some environments may require an API token — accept 200 (ok) or 403 (forbidden)
        Response res = UsersClient.getAll(noAuthSpec);

        int status = res.getStatusCode();
        assertTrue(status == 200 || status == 403, "Expected 200 or 403 but was: " + status);
    }
}