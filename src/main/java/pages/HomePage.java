package pages;

import engine.action.BrowserActions;
import engine.action.ElementActions;
import engine.constants.FrameWorkConstants;
import engine.enums.Waits;
import engine.logger.CustomLogger;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By search_txtArea = By.id("search");
    private final By cart = By.xpath("//a[@class='action showcart']");
    private final By logo = By.xpath("//a[@aria-label='store logo']");
    private final By checkout = By.id("top-cart-btn-checkout");
    private final By viewAndEditCart = By.xpath("//span[text()='View and Edit Cart']");
    private By cartTotal = By.xpath("//div[@class='items-total']");

    private By navigationOptions(String criteria, double index) {
        return By.xpath("(//div[@id='store.menu']//span[text()=\"" + criteria + "\"])[" + index + "]/parent::a");

    }

    @Step("Navigate to website")
    public HomePage navigationToWebSite() {
        driver.get(FrameWorkConstants.url);
        ElementActions.waitExplicitly(driver, 5, logo, String.valueOf(Waits.VISIBLE));
        CustomLogger.logger.info("Navigate to: {}", FrameWorkConstants.url);
        return this;
    }

    @Step("Navigate to [{crit}] numbered [{index}]")
    public HomePage navigateBetweenLinks(String crit, double index) {
        ElementActions.waitExplicitly(driver, 5, navigationOptions(crit, (int) index), String.valueOf(Waits.PRESENT));
        BrowserActions.navigateToURL(driver, ElementActions.getAttribute(driver, navigationOptions(crit, (int) index), "href"));
        CustomLogger.logger.info("Navigate to sub link: " + crit + " with occurence number: " + index);
        return this;
    }

    @Step("Click on [cart]")
    public HomePage clickOnCart() {
        ElementActions.click(driver, cart, 8);
        CustomLogger.logger.info("Click on cart");
        return this;
    }

    @Step("Click on [checkOut]")
    public HomePage clickOnCheckOut() {
//        ElementActions.scrollToElement(driver, checkout, 15);
        ElementActions.useJavaExecutorToClick(driver, checkout, 15);
        CustomLogger.logger.info("Click on checkout");
        return this;
    }

    @Step("Click on [viewAndEdit]")
    public HomePage clickOnViewAndEdit() {
        ElementActions.click(driver, viewAndEditCart, 6);
        CustomLogger.logger.info("Click on view and edit");
        return this;
    }

    @Step("Type in search bar [{text}] and click [enter]")
    public HomePage typeSearchBar(String text) {
        ElementActions.type(driver, search_txtArea, text, 10);
        ElementActions.clickEnter(driver, search_txtArea);
        CustomLogger.logger.info("Type in search bar: " + text + " Then click enter");
        return this;
    }
}
