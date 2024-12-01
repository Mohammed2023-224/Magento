package pages;

import engine.action.ElementActions;
import engine.logger.CustomLogger;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Cart extends HomePage {
    public Cart(WebDriver driver) {
        super(driver);
    }

    private By quantity_txtArea = By.id("cart-324885-qty");
    private By updateShoppingCart_Btn = By.xpath("//span[text()='Update Shopping Cart']");
    private By total_row = By.xpath("//tr[@class='price']");
    private By proceed_Btn = By.xpath("//span[text()='Proceed to Checkout']");

    @Step("Click on [proceed btn]")
    public Cart clickProccedButton() {
        ElementActions.click(driver, proceed_Btn, 10);
        CustomLogger.logger.info("Click on proceed button");
        return this;
    }

    @Step("Click on [update shopping cart]")
    public Cart clickUpdatShoppingCart() {
        ElementActions.click(driver, updateShoppingCart_Btn, 10);
        CustomLogger.logger.info("Click on update shopping cart");
        return this;
    }

    @Step("get total price")
    public String getTotalPrice() {
        String text = ElementActions.getText(driver, total_row, 10);
        CustomLogger.logger.info("get total price: " + text);
        return text;
    }

    @Step("Type Quantity [{quantity}]")
    public Cart typeQuantity(String quantity) {
        ElementActions.type(driver, proceed_Btn, quantity, 10);
        CustomLogger.logger.info("type: " + quantity + "in quantity field");
        return this;
    }
}
