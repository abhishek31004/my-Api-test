package com.example.utils;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * LoggingUtils provides helpers for timestamp formatting used in logs and test output.
 */
public final class LoggingUtils {
    private LoggingUtils() {}

    /**
     * Return current UTC time in ISO instant format.
     * @return timestamp string
     */
    public static String utcNow() {
        return DateTimeFormatter.ISO_INSTANT
                .withZone(ZoneOffset.UTC)
                .format(Instant.now());
    }
}