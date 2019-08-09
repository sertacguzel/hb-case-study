package com.hepsiburada.test.Drivers;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class Browser {

    private ThreadLocal<RemoteWebDriver> WEB_DRIVER;

    public void setUp(String browser_name, String version) throws Exception {

        WEB_DRIVER = new ThreadLocal<>();
        MutableCapabilities mutableCapabilities;
        if (browser_name.equalsIgnoreCase("chrome")) {
            mutableCapabilities = new ChromeOptions();
            mutableCapabilities.setCapability("version", version);


        } else if (browser_name.equalsIgnoreCase("firefox")) {
            mutableCapabilities = new FirefoxOptions();
            mutableCapabilities.setCapability("version", version);

        } else {
            throw new RuntimeException("Please pass valid Browser name....");
        }
        WEB_DRIVER.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), mutableCapabilities));
    }

    public WebDriver getDriver() {
        return WEB_DRIVER.get();
    }

    public void tearDown() {
        WEB_DRIVER.get().quit();
    }

}
