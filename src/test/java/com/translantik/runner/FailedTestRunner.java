package com.translantik.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "com/translantik/step_definitions",
        features = "@target/rerun.txt"
        // tags = "@failedToTest"
)

public class FailedTestRunner {

}
