package org.petstore.cucumbertest;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/org/petstore/features"
       , glue ="org.petstore.stepdefinitions",
        plugin = {"json:target/jsonReports/cucumber.json"})
public class TestRunner extends AbstractTestNGCucumberTests {
}
