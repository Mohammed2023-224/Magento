package pages;

import engine.action.ElementActions;
import engine.enums.Waits;
import engine.logger.CustomLogger;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Product extends HomePage {
    public Product(WebDriver driver) {
        super(driver);
    }

    private final By productName = By.xpath("//div[@class='product-info-main']//span[@itemprop='name']");
    private final By productPrice = By.xpath("//div[@class='product-info-main']//span[@class='price']");
    private final By qty_txtArea = By.id("qty");
    private final By addToCart_btn = By.xpath("//span[text()='Add to Cart']");
    private final By productSizeCount = By.xpath("(//div[@class='swatch-attribute size']//div[@class='swatch-option text'])");
    private final By productColorsCount = By.xpath("//div[@class='swatch-attribute color']//div[@class='swatch-option color']");
    private final By cartCount = By.xpath("//span[@class='counter-number']");
    private By addProductMessage = By.xpath("//div[@role='alert']");

    private By productSizes(String size) {
        return By.xpath("//div[@class='swatch-attribute size']//div[@option-label='" + size + "']");
    }

    private By productSizes(int index) {
        return By.xpath("(//div[@class='swatch-attribute size']//div[@class='swatch-option text'])[" + index + "]");
    }

    private By productColors(String color) {
        return By.xpath("//div[@class='swatch-attribute color']//div[@option-label='" + color + "']");
    }

    private By productColors(int index) {
        return By.xpath("(//div[@class='swatch-attribute color']//div[@class='swatch-option color'])[" + index + "]");
    }

    @Step("click [Add to cart] btn")
    public Product clickAddToCart() {
        ElementActions.useJavaExecutorToClick(driver, addToCart_btn);
        ElementActions.waitExplicitly(driver, 5, addProductMessage, Waits.VISIBLE.toString());
        CustomLogger.logger.info("Click add to cart button");
        return this;
    }

    @Step("Type quantity: [{text}]")
    public Product typeQuantity(String text) {
        ElementActions.waitExplicitly(driver, 5, qty_txtArea, Waits.CLICKABLE.toString());
        ElementActions.useJavaExecutorToType(driver, qty_txtArea, text);
        CustomLogger.logger.info("Type quantity: " + text);
        return this;
    }

    @Step("Choose size [{size}]")
    public Product chooseSize(String size) {
        if (ElementActions.isElementDisplayed(driver, productSizeCount)) {
            try {
                ElementActions.waitExplicitly(driver, 2, productSizes(size), Waits.VISIBLE.toString());
                ElementActions.click(driver, productSizes(size));
                CustomLogger.logger.info("Choose size: " + size);
            } catch (Exception e) {
                ElementActions.click(driver, productSizes(1));
                CustomLogger.logger.info("Choose first size in the list");
            }
        } else {
            CustomLogger.logger.info("Skip this step as no size elements were found");
        }
        return this;
    }

    @Step("Choose color [{color}]")
    public Product chooseColor(String color) {
        if (ElementActions.isElementDisplayed(driver, productColorsCount)) {
            try {
                ElementActions.waitExplicitly(driver, 8, productColors(color), Waits.VISIBLE.toString());
                ElementActions.scrollToElement(driver, productColors(color));
                ElementActions.click(driver, productColors(color));
                CustomLogger.logger.info("chose color: " + color);
            } catch (Exception e) {
                ElementActions.click(driver, productColors(1));
                CustomLogger.logger.info("Choose first color in the list");
            }
        } else {
            CustomLogger.logger.info("Skip this step as no color elements were found");
        }
        return this;
    }

    public String getPrice() {
        ElementActions.waitExplicitly(driver, 5, productPrice, Waits.VISIBLE.toString());
        CustomLogger.logger.info("get Price of: {}", ElementActions.getText(driver, productPrice));
        return ElementActions.getText(driver, productPrice);
    }

    @Step("Get product name")
    public String getName() {
        ElementActions.waitExplicitly(driver, 5, productName, Waits.VISIBLE.toString());
        String name = ElementActions.getText(driver, productName);
        CustomLogger.logger.info("get Product name: " + name);
        return name;
    }

}
