package com.example.utils;

/**
 * RandomUtils provides small helpers to generate unique test data.
 */
public final class RandomUtils {
    private RandomUtils() {}

    /**
     * Generate a reasonably unique email address for tests.
     * @return email string suitable for POST requests
     */
    public static String uniqueEmail() {
        return "test+" + System.currentTimeMillis() + "@example.com";
    }
}