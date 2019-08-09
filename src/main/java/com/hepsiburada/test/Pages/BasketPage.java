package com.hepsiburada.test.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasketPage extends BasePage {
    By cartButton = By.id("CartButton");
    By continueButton = By.xpath("//*[@id=\"short-summary\"]/div[1]/div[2]/button");
    By paymentContainer = By.xpath("//*[@class=\"payment-container\"]");
    By finishShoppingButton = By.xpath("//*[@id=\"frm-save-order\"]/button");
    By addressContanier = By.id("addresses");
    By transferButton = By.xpath("//*[@class=\"accordions\"]/div[3]/a");
    By orderContanier = By.id("order-container");
    By randomRecommendedProduct = By.xpath("//*[@class=\"owl-item active\"]/li/div/div/span");
    By eftInfoButton = By.xpath("//*[@class=\"button-container\"]/button[2]");
    By transferOptions = By.xpath("//*[@id=\"payment-type-2\"]/div/ul/li");
    By recommendedProductCards = By.xpath("//*[@id=\"recommended-products\"]/div[1]/ul/div[1]/div/li[2]/li/div/div/span");
    By outOfStockRecomendationButton = By.xpath(".//*[@class=\"box outof-stock-reco\"]/ul/li/div/div[3]/div[2]/button");
    By outOfStockRecomendationContanier = By.xpath("//*[@class=\"box outof-stock-reco\"]");
    By getBankName = By.xpath("//*[@class=\"order-payment-info\"]/div/div[2]/p");
    By getTotalPrice = By.xpath("//*[@id=\"short-summary\"]/div[1]/div[1]/div/span");

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public BasketPage goToBasketPage() {
        clickBy(cartButton);
        return this;
    }

    public BasketPage addRecommendedProduct() throws InterruptedException {
        untilWaitVisibilityOfElementLocated(recommendedProductCards);
        WebElement el = findRandomBy(randomRecommendedProduct);
        el.click();
        try {
            untilWaitVisibilityOfElementLocated(outOfStockRecomendationContanier);
            if (driver.findElement(outOfStockRecomendationContanier).isDisplayed()) {
                clickBy(outOfStockRecomendationButton);
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println(e.getStackTrace().toString());
        } finally {
            return this;
        }
    }

    public BasketPage clickFinishButton() {
        clickBy(continueButton);
        return this;
    }

    public BasketPage isDeliveryOptions() {
        untilWaitElementPresence(addressContanier);
        return this;
    }

    public BasketPage isTransferOptions() {
        untilWaitElementPresence(transferOptions);
        return this;
    }

    public BasketPage goToPayment() {
        clickBy(continueButton);
        return this;
    }

    public BasketPage isPayment() {
        untilWaitElementPresence(paymentContainer);
        return this;
    }

    public BasketPage transferOptions() throws InterruptedException {
        clickBy(transferButton);
        return this;
    }

    public BasketPage paymentWithTransfer() throws InterruptedException {
        runWithRetries(() -> clickBy(By.xpath("//*[@id=\"payment-type-2\"]/div[1]/ul/li[6]")));
        return this;
    }

    public BasketPage goToShoppingSummary() {
        clickBy(continueButton);
        return this;
    }

    public BasketPage isShoppingSummary() {
        untilWaitElementPresence(orderContanier);
        return this;
    }

    public OrderPage finishShopping() {
        clickBy(finishShoppingButton);
        return new OrderPage(driver);
    }

    public BasketPage eftInfoContanier() {
        clickBy(eftInfoButton);
        return this;
    }

    public String totalPrice() {
        untilWaitVisibilityOfElementLocated(getTotalPrice);
        return driver.findElement(getTotalPrice).getText();
    }

    public String summaryPageBankName() {
        return driver.findElement(getBankName).getText();
    }
}
