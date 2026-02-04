package com.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestNG sample suite used by `testing.xml` for demonstration.
 */
public class TestNgSample {

    /**
     * Simple TestNG sample test (sanity check).
     */
    @Test
    public void sample() {
        Assert.assertTrue(true, "TestNG sample test always passes");
    }
}