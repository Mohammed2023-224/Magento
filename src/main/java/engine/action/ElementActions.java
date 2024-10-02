package engine.action;

import engine.logger.CustomLogger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ElementActions {

    public static void type(WebDriver driver, By locator, String text) {
        driver.findElement(locator).sendKeys(text);
        CustomLogger.logger.info("Type: " + text + " in field: " + locator);
    }

    public static String getText(WebDriver driver, By locator) {
        CustomLogger.logger.info("Read data in field " + locator);
        return driver.findElement(locator).getText();
    }


    public static void click(WebDriver driver, By locator) {
        driver.findElement(locator).click();
        CustomLogger.logger.info("Click on element: " + locator);
    }

    public static WebDriverWait waitExplicitly(WebDriver driver, int time, By locator, Function condition, String report) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(condition);
        CustomLogger.logger.info("Wait for element: " + locator + " to be " + report + " for: " + time + " sec");
        return wait;
    }


    public static WebDriverWait waitExplicitly(WebDriver driver, int time, By locator, String condition) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        switch (condition.trim().toLowerCase()) {
            case "visible":
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                CustomLogger.logger.info("Wait for element: " + locator + " to be visible for: " + time + " sec");
                break;
            case "clickable":
                wait.until(ExpectedConditions.elementToBeClickable(locator));
                CustomLogger.logger.info("Wait for element: " + locator + " to be clickable for: " + time + " sec");
                break;
            case "invisible":
                wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
                CustomLogger.logger.info("Wait for element: " + locator + " to be invisible for: " + time + " sec");
                break;
            case "present":
                wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                CustomLogger.logger.info("Wait for element: " + locator + " to be present for: " + time + " sec");
                break;
        }
        return wait;
    }

    public static WebDriverWait waitExplicitly(WebDriver driver, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        CustomLogger.logger.info("Wait for element to be ready for: " + time + " sec");
        return wait;
    }


    public static void clickEnter(WebDriver driver, By locator) {
        driver.findElement(locator).sendKeys(Keys.ENTER);
        CustomLogger.logger.info("Click on enter");
    }

    public static void useJavaExecutorToClick(WebDriver driver, By locator) {
        CustomLogger.logger.info("Click on element using JS executor: " + locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(locator));
    }

    public static void useJavaExecutorToType(WebDriver driver, By locator, String text) {
        CustomLogger.logger.info("type in element using JS executor: " + locator + ":" + text);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1];", driver.findElement(locator), text);
    }

    public static void scrollToElement(WebDriver driver, By locator) {
        CustomLogger.logger.info("scroll to element using JS executor: " + locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
    }

    public static String getAttribute(WebDriver driver, By locator, String attribute) {
        CustomLogger.logger.info("get attribute: " + attribute + " for element:" + locator);
        return driver.findElement(locator).getAttribute(attribute);
    }

    public static void hover(WebDriver driver, By locator) {
        CustomLogger.logger.info("hover over element:" + locator);
        new Actions(driver).moveToElement(driver.findElement(locator)).perform();
    }

    public static Boolean isElementDisplayed(WebDriver driver, By locator) {
        try {
            driver.findElement(locator).isDisplayed();
            CustomLogger.logger.info("Element: " + locator + " is displayed");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void handleSelection(WebDriver driver, By locator, int index) {
        scrollToElement(driver, locator);
        Select select = new Select(driver.findElement(locator));
        CustomLogger.logger.info("choose option by index: " + index);
        select.selectByIndex(index);
    }

    public static void handleSelection(WebDriver driver, By locator, String value) {
        Select select = new Select(driver.findElement(locator));
        CustomLogger.logger.info("choose option by value: " + value);
        select.selectByVisibleText(value);
    }

    public static void handleFirstSelection(WebDriver driver, By locator) {
        Select select = new Select(driver.findElement(locator));
        CustomLogger.logger.info("choose first option");
        select.getFirstSelectedOption();
    }

    public static String getTextFromListOfElementsByIndex(WebDriver driver, By locator, int index) {
        CustomLogger.logger.info("get text from element no: " + index);
        ArrayList<String> list = new ArrayList<>();
        for (WebElement we : driver.findElements(locator)) {
            list.add(we.getText());
        }
        return list.get(index);
    }

    public static WebElement getCertainElementFromMultipleElementsByIndex(WebDriver driver, By locator, int index) {
        List<WebElement> list = driver.findElements(locator);
        return list.get(index);
    }

    public static ArrayList<String> getTextFromListOfElements(WebDriver driver, By locator) {
        CustomLogger.logger.info("get text from elements");
        ArrayList<String> list = new ArrayList<>();
        for (WebElement we : driver.findElements(locator)) {
            list.add(we.getText());
        }
        return list;
    }

    public static int countElements(WebDriver driver, By locator) {
        CustomLogger.logger.info("count elements with the locator: " + locator);
        return driver.findElements(locator).size();
    }


}
