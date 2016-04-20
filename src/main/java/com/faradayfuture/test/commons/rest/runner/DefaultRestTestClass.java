package com.faradayfuture.test.commons.rest.runner;

import com.faradayfuture.test.commons.rest.databind.RestTestGroup;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.*;
import org.junit.runners.model.InitializationError;

import java.lang.reflect.Field;

/**
 * Created by maggie on 4/19/16.
 */
public abstract class DefaultRestTestClass {
    protected static RestTestGroup testGroup;

    @BeforeClass
    public static void groupSetup() {
        testGroup.configureBeforeGroup();
    }

    @Before
    public void testSetup() {
        testGroup.configureBeforeTest();
    }

    @Test
    @Parameters
    @TestCaseName("{0}")
    public void test(String testName) {
        testGroup.getTest(testName).configure(testGroup.getConfigureForTest()).asserting();
    }

    protected Object[] parametersForTest() {
        if (testGroup == null) {
            RestTestGroupJsonPath annotation = getClass().getAnnotation(RestTestGroupJsonPath.class);
            String jsonPath = annotation == null ? getClass().getSimpleName() + ".json" : annotation.value();
            testGroup = RestTestGroup.build(getClass().getResource(jsonPath));
        }
        return testGroup.getAllTestNames().toArray(new Object[]{});
    }

    @After
    public void testCleanup() {
        testGroup.configureAfterTest();
    }

    @AfterClass
    public static void groupCleanup() {
        testGroup.configureAfterGroup();
    }
}
