package com.hepsiburada.test.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    String baseURL = "https://www.hepsiburada.com/uyelik/giris";
    private By usernameBy = By.id("email");
    private By passwordBy = By.id("password");
    private By submitButtonBy = By.xpath("//*[@id=\"form-login\"]/div[4]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage goToLoginPage() {
        driver.get(baseURL);
        return this;
    }

    public LoginPage loginToHepsiburada(String username, String password) {
        sendKeyBy(usernameBy, username);
        sendKeyBy(passwordBy, password);
        clickBy(submitButtonBy);
        return new LoginPage(driver);
    }

}
