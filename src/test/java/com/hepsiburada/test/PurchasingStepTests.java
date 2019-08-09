package com.hepsiburada.test;

import com.hepsiburada.test.Pages.BasketPage;
import com.hepsiburada.test.Pages.LoginPage;
import com.hepsiburada.test.Pages.OrderPage;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.testng.Assert;


public class PurchasingStepTests extends TestRunner {
    String totalPrice;
    String summaryPageBankName;
    String bankName;

    @Given("^I go to login page$")
    public void ı_go_to_login_page() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();

    }

    @Given("^enter my email and password for smoke test$")
    public void enter_my_email_and_password_for_smoke_test(DataTable arg1) throws Throwable {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToHepsiburada("test@testhepsiburada.com", "Test1.1");
    }

    @And("^I go to cart$")
    public void ı_go_to_cart() throws Throwable {
        BasketPage basketPage = new BasketPage(driver);
        basketPage.goToBasketPage();
    }

    @And("^I add a random recommended product to cart$")
    public void ı_add_a_random_recommended_product_to_cart() throws Throwable {
        BasketPage basketPage = new BasketPage(driver);
        basketPage.addRecommendedProduct();
    }

    @And("^I click to 'Alışverişi Tamamla' button on cart$")
    public void ı_click_to_Alışverişi_Tamamla_button_on_cart() throws Throwable {
        BasketPage basketPage = new BasketPage(driver);
        basketPage.clickFinishButton().isDeliveryOptions();
    }

    @And("^I go to payment page$")
    public void ı_go_to_payment_page() throws Throwable {
        BasketPage basketPage = new BasketPage(driver);
        basketPage.goToPayment().isPayment();
    }

    @And("^I select payment option as \"([^\"]*)\"$")
    public void ı_select_payment_option_as(String arg1) throws Throwable {
        BasketPage basketPage = new BasketPage(driver);
        basketPage.transferOptions();
    }

    @And("^I check if money order bank list is seen$")
    public void ı_check_if_money_order_bank_list_is_seen() throws Throwable {
        BasketPage basketPage = new BasketPage(driver);
        basketPage.isTransferOptions();
    }

    @And("^select \"([^\"]*)\" bank for transfer$")
    public void select_bank_for_transfer(String arg1) throws Throwable {
        BasketPage basketPage = new BasketPage(driver);
        basketPage.paymentWithTransfer();
    }

    @And("^I go to order summary page$")
    public void ı_go_to_order_summary_page() throws Throwable {
        BasketPage basketPage = new BasketPage(driver);
        basketPage.goToShoppingSummary();
    }

    @And("^click 'Devam Et' button on money order popup$")
    public void click_Devam_Et_button_on_money_order_popup() throws Throwable {
        BasketPage basketPage = new BasketPage(driver);
        basketPage.eftInfoContanier();
    }

    @And("^I get money order payment total in order summary page$")
    public void ı_get_money_order_payment_total_in_order_summary_page() throws Throwable {
        BasketPage basketPage = new BasketPage(driver);
        this.totalPrice = basketPage.isShoppingSummary().totalPrice();
    }

    @And("^I get money order IBAN in order summary page$")
    public void ı_get_money_order_IBAN_in_order_summary_page() throws Throwable {
        BasketPage basketPage = new BasketPage(driver);
        this.summaryPageBankName = basketPage.summaryPageBankName();
    }

    @And("^I complete my order$")
    public void ı_complete_my_order() throws Throwable {
        BasketPage basketPage = new BasketPage(driver);
        basketPage.finishShopping();
    }

    @And("^I check if order number is seen$")
    public void ı_check_if_order_number_is_seen() throws Throwable {
        OrderPage orderPage = new OrderPage(driver);
        orderPage.isTrackingNumber();
    }

    @And("^I check payment type in complete order is \"([^\"]*)\"$")
    public void ı_check_payment_type_in_complete_order_is(String arg1) throws Throwable {
        OrderPage orderPage = new OrderPage(driver);
        Assert.assertEquals(orderPage.selectPaymentOption(), "Havale");
    }

    @And("^I check if total paid price matches with summary in completed order page$")
    public void ı_check_if_total_paid_price_matches_with_summary_in_completed_order_page() throws Throwable {
        OrderPage orderPage = new OrderPage(driver);
        Assert.assertEquals(orderPage.selectTotalPrice(), totalPrice);
    }

    @And("^I check if amount paid with money order matches with order summary$")
    public void ı_check_if_amount_paid_with_money_order_matches_with_order_summary() throws Throwable {
        OrderPage orderPage = new OrderPage(driver);
        Assert.assertEquals(orderPage.selectTransferPrice(), totalPrice);
    }

    @And("^I check if bank IBAN for mone$")
    public void ı_check_if_bank_IBAN_for_mone() throws Throwable {
        OrderPage orderPage = new OrderPage(driver);
        this.bankName = orderPage.getBankName();
        Assert.assertTrue(bankName.split(" ")[0].contains(summaryPageBankName.split(" ")[0]));
    }
}
