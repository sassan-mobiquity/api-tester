package com.mobiquity.assignment;


import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;


@Slf4j
public class TestRunnerBase extends AbstractTestNGCucumberTests {

    private static final String timeStamp = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
    private static TestConfig props;

    @BeforeSuite
    void beforeSuite() {
        log.info("starting the test suite at: " + timeStamp);
        log.info("configuring the project");
        Config profile = ConfigFactory.load("local-config.conf");
        Config envOverrides = ConfigFactory.systemProperties()
                .withFallback(ConfigFactory.systemEnvironment());
        props = new TestConfig(envOverrides.withFallback(profile).resolve());
    }

    @Test
    void test() {
        log.info("running api tests against: " + props.getTypicodeURL());
    }

}
