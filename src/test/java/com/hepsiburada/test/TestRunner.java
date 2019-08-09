package com.hepsiburada.test;

import com.hepsiburada.test.Drivers.Browser;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@CucumberOptions(
        features = "src/test/resources/features",
        tags = {"~@Ignore"},
        format = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/json-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun-reports/rerun.txt"
        })
public class TestRunner {
    static WebDriver driver;
    public TestNGCucumberRunner testNGCucumberRunner;

    @Parameters({"browser_name", "version"})
    @BeforeClass(alwaysRun = true)
    public void setUpClass(String browser_name, String version) throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());

        Browser browser = new Browser();
        browser.setUp(browser_name, version);
        this.driver = browser.getDriver();
        driver.manage().window().fullscreen();
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {

        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
        driver.quit();
    }
}
