package com.hepsiburada.test.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage extends BasePage {

    By trackingNumber = By.xpath("//*[@id=\"content\"]/div/div[2]/div[1]/div/div[1]/strong");
    By totalPrice = By.xpath("//*[@id=\"content\"]/div/div[2]/div[1]/div/div[2]/div[3]/strong");
    By transferPrice = By.xpath("//*[@id=\"content\"]/div/div[2]/div[1]/div/div[2]/div[2]/strong");
    By bankName = By.xpath("//*[@id=\"content\"]/div/div[2]/div[2]/strong");
    By paymentOption = By.xpath("//*[@id=\"content\"]/div/div[2]/div[1]/div/div[2]/div[2]/span");

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    public OrderPage isTrackingNumber() {
        untilWaitElementPresence(trackingNumber);
        return this;
    }

    public String selectTotalPrice() {
        return driver.findElement(totalPrice).getText().replace(" TL", "");
    }

    public String selectTransferPrice() {
        return driver.findElement(transferPrice).getText().replace(" TL", "");
    }

    public String selectPaymentOption() {
        return driver.findElement(paymentOption).getText();
    }

    public String getBankName() {
        return driver.findElement(bankName).getText();
    }
}
