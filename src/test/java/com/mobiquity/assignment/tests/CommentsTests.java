package com.mobiquity.assignment.tests;

import com.mobiquity.assignment.TestRunnerBase;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/post-comments.feature",
        glue = {"com.mobiquity.assignment.steps"},
        plugin = {"json:target/cucumber-reports/comments.json"}
)
public class CommentsTests extends TestRunnerBase {
}
