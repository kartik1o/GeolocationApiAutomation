package api.cucumberRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {".//Features/GeolocationApiTest.feature"},
        glue = "api.stepdefs",
        dryRun = false,
        monochrome = true,
        plugin = {"pretty", "html:target/cucumber-reports/report_html.html"}
)
public class Runner{}
