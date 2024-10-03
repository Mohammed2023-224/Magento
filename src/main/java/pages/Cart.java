package pages;

import engine.action.ElementActions;
import engine.logger.CustomLogger;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
        ElementActions.waitExplicitly(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(proceed_Btn));
        ElementActions.click(driver, proceed_Btn);
        CustomLogger.logger.info("Click on proceed button");
        return this;
    }

    @Step("Click on [update shopping cart]")
    public Cart clickUpdatShoppingCart() {
        ElementActions.waitExplicitly(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(updateShoppingCart_Btn));
        ElementActions.click(driver, updateShoppingCart_Btn);
        CustomLogger.logger.info("Click on update shopping cart");
        return this;
    }

    @Step("get total price")
    public String getTotalPrice() {
        ElementActions.waitExplicitly(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(total_row));
        return ElementActions.getText(driver, total_row);
    }

    @Step("Type Quantity {[quantity]}")
    public Cart typeQuantity(String quantity) {
        ElementActions.waitExplicitly(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(quantity_txtArea));
        ElementActions.type(driver, proceed_Btn, quantity);
        CustomLogger.logger.info("type: " + quantity + "in quantity field");
        return this;
    }
}
