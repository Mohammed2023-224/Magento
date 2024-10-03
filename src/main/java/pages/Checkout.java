package pages;

import engine.action.ElementActions;
import engine.enums.Waits;
import engine.logger.CustomLogger;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Checkout extends HomePage {
    public Checkout(WebDriver driver) {
        super(driver);
    }

    private By emailField_txtArea = By.id("customer-email");
    private By nameField_txtArea = By.name("firstname");
    private By street1_txtArea = By.name("street[0]");
    private By street2_txtArea = By.name("street[1]");
    private By street3_txtArea = By.name("street[2]");
    private By city_txtArea = By.name("city");
    private By lastname_txtArea = By.name("lastname");
    private By region_id_ddl = By.name("region_id");
    private By telephone_txtArea = By.name("telephone");
    private By country_id_ddl = By.name("country_id");
    private By postcode_txtArea = By.name("postcode");
    private By nex_btn = By.xpath("//button[@class='button action continue primary']");
    private By message = By.xpath("//div[@class='message notice']");
    private By placeOrder = By.xpath("//button//span[text()='Place Order']");
    private By continueShopping = By.xpath("//span[text()='Continue Shopping']");
    private By orderNumber = By.xpath("//div[@class='checkout-success']//p//span");
    private By loadingImg = By.xpath("//img[@alt='Loading...']");
    private By emailLoader = By.xpath("//div[@class='loader']");
    //    private By grandTotal_row = By.className("grand totals");
    private By placedOrderSuccessfully_row = By.xpath("//span[text()='Thank you for your purchase!']");
    private By freeDelivery = By.xpath("(//input[@class='radio'])[1]");

    @Step("Type in email field {[email]}")
    public Checkout typeEmailField(String email) {
        ElementActions.waitExplicitly(driver, 10, emailField_txtArea, Waits.VISIBLE.toString());
        ElementActions.type(driver, emailField_txtArea, email);
        ElementActions.waitExplicitly(driver, 10, loadingImg, Waits.VISIBLE.toString());
        ElementActions.waitExplicitly(driver, 10, loadingImg, "invisible");
        CustomLogger.logger.info("type: " + emailField_txtArea + " in email field");
        return this;
    }

    @Step("Type in name field {[name]}")
    public Checkout typeNameField(String name) {
        ElementActions.waitExplicitly(driver, 10, nameField_txtArea, Waits.VISIBLE.toString());
        ElementActions.type(driver, nameField_txtArea, name);
        CustomLogger.logger.info("type: " + nameField_txtArea + " in name field");
        return this;
    }

    @Step("Type in street field {[street]}")
    public Checkout typeStreetField(String street) {
        ElementActions.waitExplicitly(driver, 10, street1_txtArea, Waits.VISIBLE.toString());
        ElementActions.type(driver, street1_txtArea, street);
        CustomLogger.logger.info("type: " + street1_txtArea + "in street field");
        return this;
    }

    @Step("Type in city field {[city]}")
    public Checkout typeCityField(String city) {
        ElementActions.waitExplicitly(driver, 10, city_txtArea, Waits.VISIBLE.toString());
        ElementActions.type(driver, city_txtArea, city);
        CustomLogger.logger.info("type: " + city_txtArea + " in city field");
        return this;
    }

    @Step("Type in last Name field {[lastName]}")
    public Checkout typeLastNameField(String lastName) {
        ElementActions.waitExplicitly(driver, 10, lastname_txtArea, Waits.VISIBLE.toString());
        ElementActions.type(driver, lastname_txtArea, lastName);
        CustomLogger.logger.info("type: " + lastname_txtArea + " in last name field");
        return this;
    }

    @Step("Type in phone field {[phone]}")
    public Checkout typePhoneField(String phone) {
        ElementActions.waitExplicitly(driver, 10, telephone_txtArea, Waits.VISIBLE.toString());
        ElementActions.type(driver, telephone_txtArea, phone);
        ElementActions.waitExplicitly(driver, 10, emailLoader, Waits.VISIBLE.toString());
        ElementActions.waitExplicitly(driver, 10, emailLoader, "invisible");
        CustomLogger.logger.info("type: " + telephone_txtArea + "in phone field");
        return this;
    }

    @Step("Type in postal code field {[postal]}")
    public Checkout typePostalCodeField(String postal) {
        ElementActions.waitExplicitly(driver, 2, postcode_txtArea, Waits.VISIBLE.toString());
        ElementActions.type(driver, postcode_txtArea, postal);
        CustomLogger.logger.info("type: " + postcode_txtArea + "in postal code field");
        return this;
    }

    @Step("Choose country {[country]}")
    public Checkout chooseCountry(String text, int index) {
        ElementActions.waitExplicitly(driver, 3, country_id_ddl, Waits.VISIBLE.toString());
        try {
            ElementActions.handleSelection(driver, country_id_ddl, text);
        } catch (Exception e) {
            ElementActions.handleSelection(driver, country_id_ddl, index);
        }
        return this;
    }

    @Step("Choose [free delivery] checkbox")
    public Checkout chooseDelivery() {
        ElementActions.useJavaExecutorToClick(driver, freeDelivery);
        return this;
    }

    @Step("Click on [next btn]")
    public Checkout clickNextButton() {
        ElementActions.waitExplicitly(driver, 10, nex_btn, Waits.CLICKABLE.toString());
        ElementActions.useJavaExecutorToClick(driver, nex_btn);
        return this;
    }

    @Step("Click on [place order btn]")
    public Checkout clickPlaceOrderButton() {
        ElementActions.waitExplicitly(driver, 10, placeOrder, Waits.CLICKABLE.toString());
        ElementActions.useJavaExecutorToClick(driver, placeOrder);
        return this;
    }

    @Step("get order number")
    public String getOrderNumber() {
        ElementActions.waitExplicitly(driver, 10, orderNumber, Waits.VISIBLE.toString());
        String order = ElementActions.getText(driver, orderNumber);
        CustomLogger.logger.info("get order Number: " + order);
        return order;
    }

    @Step("check order is created")
    public Boolean checkOrder() {
        ElementActions.waitExplicitly(driver, 10, continueShopping, Waits.CLICKABLE.toString());
        return ElementActions.isElementDisplayed(driver, continueShopping);
    }
}
