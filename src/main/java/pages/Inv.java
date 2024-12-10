package pages;

import engine.action.BrowserActions;
import engine.action.ElementActions;
import engine.enums.Waits;
import engine.logger.CustomLogger;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
    private final By filterApplied = By.xpath("//span[@class='filter-value']");

    private By certainProduct(int index) {
        return By.xpath("(//ol[@class='products list items product-items']//li)[" + index + "]");
    }

    private By productByName(String prod) {
        return By.xpath("//ol[@class='products list items product-items']//li//a[normalize-space(text())='" + prod + "']");
    }

    private By leftSectionFilters(int filterType, int filterName) {
        return By.xpath("((//div[@id='narrow-by-list']//div[@role='presentation'])[" + filterType + "]//ol//li)[" + filterName + "]//a");
    }

    private By leftSectionFilters(String filter) {
        return By.xpath("//div[@id='narrow-by-list']//div[@role='presentation']//a[contains(text(),'" + filter + "')]");
    }

    private By leftSectionSizeAndColorFilters(String filter) {
        return By.xpath("//div[@id='narrow-by-list']//div[@role='presentation']//div[@option-label='" + filter + "']/parent::a");
    }

    private By certainProductCompare(int index) {
        return By.xpath("(//ol[@class='products list items product-items']//li//div[@class='actions-secondary']//a[@class='action tocompare'])[" + index + "]");
    }

    @Step("count compared items")
    public int countComparedItems() {
        CustomLogger.logger.info("Count compared items");
        return ElementActions.countElements(driver, comparedItems);
    }

    @Step("count products")
    public int countProducts() {
        ElementActions.waitExplicitly(driver, 5, allProducts, String.valueOf(Waits.VISIBLE));
        int counting = ElementActions.countElements(driver, allProducts);
        CustomLogger.logger.info("Count products: " + counting);
        return counting;
    }

    @Step("get product names")
    public ArrayList<String> getProductsNames() {
        CustomLogger.logger.info("get Product names in an array list");
        ElementActions.waitExplicitly(driver, 5, allProducts, String.valueOf(Waits.VISIBLE));
        return ElementActions.getTextFromListOfElements(driver, allProductsNames);
    }

    @Step("choose limiter index [{index}]")
    public Inv chooseLimiter(double index) {
        ElementActions.handleSelection(driver, limiter, (int) index, 10);
        CustomLogger.logger.info("Choose limiter indexed: " + index);
        ElementActions.waitExplicitly(driver, 5, allProducts, String.valueOf(Waits.CLICKABLE));
        return this;
    }

    @Step("choose sorter index [{index}]")
    public Inv chooseSorter(double index) {
        ElementActions.handleSelection(driver, sorter, index, 5);
        CustomLogger.logger.info("Choose sorter indexed: " + index);
        ElementActions.waitExplicitly(driver, 5, allProductsNames, String.valueOf(Waits.CLICKABLE));
        return this;
    }

    @Step("click product [{index}]")
    public Inv clickCertainProductCompare(double index) {
        ElementActions.scrollToElement(driver, certainProduct((int) index), 10);
        ElementActions.hover(driver, certainProduct((int) index));
        CustomLogger.logger.info("hover over product indexed: " + index);
        ElementActions.waitExplicitly(driver, 5, certainProductCompare((int) index), String.valueOf(Waits.VISIBLE));
        ElementActions.click(driver, certainProductCompare((int) index), 6);
        CustomLogger.logger.info("click on product compare button for product indexed: " + index);
        ElementActions.waitExplicitly(driver, 5, addedMessage, String.valueOf(Waits.VISIBLE));
        return this;
    }

    @Step(" click on product [{name}]")
    public Inv clickCertainProduct(String name) {
        try {
            ElementActions.click(driver, productByName(name), 6);
            CustomLogger.logger.info("Click on product with the name: " + name);
        } catch (Exception e) {
            ElementActions.click(driver, certainProduct(1), 8);
            CustomLogger.logger.info("Click on the first product");
        }
        return this;
    }

    @Step("choose filter [{filterType}]")
    public Inv clickOnFilter(String filterType) {
        try {
            ElementActions.waitExplicitly(driver, 5, leftSectionFilters(filterType), String.valueOf(Waits.PRESENT));
            BrowserActions.navigateToURL(driver, ElementActions.getAttribute(driver, leftSectionFilters(filterType), "href"));
            CustomLogger.logger.info("choose filter: " + filterType);
        } catch (Exception e1) {
            try {
                ElementActions.waitExplicitly(driver, 5, leftSectionFilters(filterType), String.valueOf(Waits.PRESENT));
                BrowserActions.navigateToURL(driver, ElementActions.getAttribute(driver, leftSectionSizeAndColorFilters(filterType), "href"));
                CustomLogger.logger.info("Choose filter: " + filterType);
            } catch (Exception e2) {
                BrowserActions.navigateToURL(driver, ElementActions.getAttribute(driver, leftSectionFilters(1, 1), "href"));
                CustomLogger.logger.info("Choose first filter");
            }
        }
        return this;
    }

    @Step("click on [compare]")
    public Inv clickOnCompare() {
        ElementActions.scrollToElement(driver, compare_btn, 10);
        ElementActions.click(driver, compare_btn, 5);
        CustomLogger.logger.info("Click on compare");
        return this;
    }

    @Step("Check compare page")
    public Boolean checkComparePage() {
        CustomLogger.logger.info("Check the compare page header");
        return ElementActions.isElementDisplayed(driver, comparePage);
    }

    @Step("Check if filters were applied")
    public Boolean checkFiltersApplied() {
        CustomLogger.logger.info("Check the existence of filters that was applied");
        return ElementActions.isElementDisplayed(driver, filterApplied);
    }


}
