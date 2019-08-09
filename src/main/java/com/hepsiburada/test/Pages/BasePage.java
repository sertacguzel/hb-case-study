package com.hepsiburada.test.Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;
import java.util.Random;

import static java.util.concurrent.TimeUnit.SECONDS;

class BasePage {
    private static final Random RANDOM = new Random();
    WebDriver driver;
    private Wait<WebDriver> wait;

    BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new FluentWait<>(driver)
                .withTimeout(30, SECONDS)
                .pollingEvery(10, SECONDS)
                .ignoring(Exception.class);

    }

    void untilWaitVisibilityOfElementLocated(By byLocator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
    }

    void clickBy(By byLocator) {
        untilWaitVisibilityOfElementLocated(byLocator);
        WebElement el = driver.findElement(byLocator);
        el.click();
    }

    void sendKeyBy(By byLocator, String text) {
        untilWaitVisibilityOfElementLocated(byLocator);
        WebElement el = driver.findElement(byLocator);
        el.sendKeys(text);
    }

    WebElement findRandomBy(By byLocator) {
        List<WebElement> el = driver.findElements(byLocator);
        return el.get(RANDOM.nextInt(el.size() - 1));
    }

    WebElement untilWaitElementPresence(By byLocator) {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
        return el;
    }

    void runWithRetries(Runnable t) throws InterruptedException {
        runWithRetries(t, 3, 100L);
    }

    void runWithRetries(Runnable t, int countDown, long sleepTime) throws InterruptedException {
        try {
            t.run();
        } catch (Exception e) {
            if (countDown == 0) {
                throw e;
            } else {
                Thread.sleep(sleepTime);
                runWithRetries(t, countDown - 1, sleepTime);
            }
        }
    }
}
