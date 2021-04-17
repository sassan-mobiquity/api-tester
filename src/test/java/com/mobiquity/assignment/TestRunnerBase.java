package com.mobiquity.assignment;


import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeSuite;

import java.text.SimpleDateFormat;
import java.util.Date;


@Slf4j
@CucumberOptions()
public class TestRunnerBase extends AbstractTestNGCucumberTests {

    private static final String timeStamp = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
    private static TestConfig props;

    @BeforeSuite
    public void beforeSuite() {
        config();
    }


    /**
     * Configures the project based on the following priority:
     * 1- System properties
     * 2- System Environment Variables
     * 3- Local config file
     */
    public static void config() {
        log.info("configuring the project");
        Config profile = ConfigFactory.load("local-config.conf");
        Config envOverrides = ConfigFactory.systemProperties()
                .withFallback(ConfigFactory.systemEnvironment());
        props = new TestConfig(envOverrides.withFallback(profile).resolve());
        RestClientBase.init(props.getDefaultTimeoutMilSec(), props.getTypicodeURL());
    }


}
