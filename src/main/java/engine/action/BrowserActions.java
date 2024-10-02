package engine.action;

import engine.logger.CustomLogger;
import org.openqa.selenium.WebDriver;

public class BrowserActions {
    public static void navigateToURL(WebDriver driver, String url) {
        driver.navigate().to(url);
        CustomLogger.logger.info("Navigate to website: " + url);
    }
}
