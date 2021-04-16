package com.mobiquity.assignment;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;


@Slf4j
public class TestRunnerBase extends AbstractTestNGCucumberTests {

    private static final String timeStamp = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());

    @BeforeSuite
    void beforeSuite() {
        log.info("starting the test suite at: " + timeStamp);
    }

    @Test
    void test() {
        log.info("running the test");
    }

}
