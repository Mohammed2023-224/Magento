package pages;

import engine.action.BrowserActions;
import engine.action.ElementActions;
import engine.constants.FrameWorkConstants;
import engine.enums.Waits;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    private By navigationOptions(String criteria, int index) {
        return By.xpath("(//div[@id='store.menu']//span[text()=\"" + criteria + "\"])[" + index + "]/parent::a");

    }

    @Step("Navigate to website")
    public HomePage navigationToWebSite() {
        driver.get(FrameWorkConstants.url);
        ElementActions.waitExplicitly(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(logo));
        return this;
    }

    @Step("Navigate to {[crit]}")
    public HomePage navigateBetweenLinks(String crit, int inex) {
        ElementActions.waitExplicitly(driver, 5).until(ExpectedConditions.presenceOfElementLocated(navigationOptions(crit, inex)));
        BrowserActions.navigateToURL(driver, ElementActions.getAttribute(driver, navigationOptions(crit, inex), "href"));
        return this;
    }

    @Step("Click on [cart]")
    public HomePage clickOnCart() {
        ElementActions.waitExplicitly(driver, 5, cart, Waits.CLICKABLE.toString());
        ElementActions.click(driver, cart);
        return this;
    }


    @Step("Click on [checkOut]")
    public HomePage clickOnCheckOut() {
        ElementActions.waitExplicitly(driver, 5, checkout, Waits.CLICKABLE.toString());
        ElementActions.useJavaExecutorToClick(driver, checkout);
        return this;
    }


    @Step("Click on [viewAndEdit]")
    public HomePage clickOnViewAndEdit() {
        ElementActions.waitExplicitly(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(viewAndEditCart));
        ElementActions.click(driver, viewAndEditCart);
        return this;
    }

    @Step("Type in search bar {[text]} and click [enter]")
    public HomePage typeSearchBar(String text) {
        ElementActions.type(driver, search_txtArea, text);
        ElementActions.clickEnter(driver, search_txtArea);
        return this;
    }


}
