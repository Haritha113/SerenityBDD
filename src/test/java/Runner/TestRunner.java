package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;


@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        features = "src/test/resources/features/user_management.feature",
        glue = {"stepdefinitions"},
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true
)

public class TestRunner {
}
