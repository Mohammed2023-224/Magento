package pages;

import engine.action.BrowserActions;
import engine.action.ElementActions;
import engine.enums.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

public class Inv extends HomePage {
    public Inv(WebDriver driver) {
        super(driver);
    }

    private final By allProducts = By.xpath("//ol[@class='products list items product-items']//li");
    //    private By allProductsPrices = By.xpath("//ol[@class='products list items product-items']//li//span[@class='price-wrapper ']");
    private final By allProductsNames = By.xpath("//ol[@class='products list items product-items']//li//strong");
    //    private By allProductsColors = By.xpath("//ol[@class='products list items product-items']//li//div[@class='swatch-attribute color']");
//    private By allProductsSizes = By.xpath("//ol[@class='products list items product-items']//li//div[@class='swatch-attribute size']");
    private final By limiter = By.xpath("(//select[@id='limiter'])[2]");
    private final By sorter = By.id("sorter");
    private final By compare_btn = By.xpath("//span[text()='Compare']");
    private final By comparePage = By.xpath("//span[text()='Compare Products']");
    private final By comparedItems = By.xpath("//ol[@id='compare-items']//li");
    private final By addedMessage = By.xpath("//div[@class='page messages']");

    private By certainProduct(int index) {
        return By.xpath("(//ol[@class='products list items product-items']//li)[" + index + "]");
    }

    private By productByName(String prod) {
        return By.xpath("//ol[@class='products list items product-items']//li//a[normalize-space(text())='" + prod + "']");
    }

    private By leftSectionFilters(int filterType, int filterName) {
        return By.xpath("((//div[@id='narrow-by-list']//div[@role='presentation'])[" + filterType + "]//ol//li)[" + filterName + "]//a");
    }

    private By certainProductCompare(int index) {
        return By.xpath("(//ol[@class='products list items product-items']//li//div[@class='actions-secondary']//a[@class='action tocompare'])[" + index + "]");
    }


    public int checkComparedItems() {
        return ElementActions.countElements(driver, comparedItems);
    }

    public int countProducts() {
        ElementActions.waitExplicitly(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(allProducts));
        return ElementActions.countElements(driver, allProducts);
    }

    public ArrayList<String> getProductsNames() {
        ElementActions.waitExplicitly(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(allProducts));
        return ElementActions.getTextFromListOfElements(driver, allProductsNames);
    }

    public Inv chooseLimiter(int index) {
        ElementActions.waitExplicitly(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(limiter));
        ElementActions.handleSelection(driver, limiter, index);
        ElementActions.waitExplicitly(driver, 5).until(ExpectedConditions.elementToBeClickable(allProducts));
        return this;
    }

    public Inv chooseSorter(int index) {
        ElementActions.waitExplicitly(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(sorter));
        ElementActions.handleSelection(driver, sorter, index);
        ElementActions.waitExplicitly(driver, 5).until(ExpectedConditions.elementToBeClickable(allProductsNames));
        return this;
    }

    public Inv clickCertainProductCompare(int index) {
        ElementActions.hover(driver, certainProduct(index));
        ElementActions.waitExplicitly(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(certainProductCompare(index)));
        ElementActions.click(driver, certainProductCompare(index));
        ElementActions.waitExplicitly(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(addedMessage));
        return this;
    }

    public Inv clickCertainProduct(String name) {
        try {
            ElementActions.waitExplicitly(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(productByName(name)));
            ElementActions.click(driver, productByName(name));
        } catch (Exception e) {
            ElementActions.click(driver, certainProduct(1));

        }
        return this;
    }

    public Inv clickOnFilter(int filterType, int filterName) {
        ElementActions.waitExplicitly(driver, 8).until(ExpectedConditions.presenceOfElementLocated(leftSectionFilters(filterType, filterName)));
        BrowserActions.navigateToURL(driver, ElementActions.getAttribute(driver, leftSectionFilters(filterType, filterName), "href"));
        return this;
    }

    public Inv clickOnCompare() {
        ElementActions.waitExplicitly(driver, 15, compare_btn, Waits.CLICKABLE.toString());
        ElementActions.click(driver, compare_btn);
        return this;
    }

    public Boolean checkComparePage() {
//        ElementActions.waitExplicitly(driver, 15, comparePage, Waits.PRESENT.toString());
        return ElementActions.isElementDisplayed(driver, comparePage);
    }

//    public String getProductName() {
//    }

}
