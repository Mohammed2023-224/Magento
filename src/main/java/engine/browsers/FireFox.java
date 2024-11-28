package engine.browsers;

import engine.driver.Options;
import engine.logger.CustomLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.ArrayList;

public class FireFox {
    private WebDriver driver;

    public FirefoxOptions firefoxOptions(ArrayList<String> options) {
        FirefoxOptions option = new FirefoxOptions();
        if (options.size() > 0) {
            CustomLogger.logger.info("Start firefox browser with options: {}", options);
            option.addArguments(options);
        } else {
            CustomLogger.logger.info("Start firefox browser");
        }
        return option;
    }

    public WebDriver initDriver() {
        return new FirefoxDriver(firefoxOptions(Options.option()));
    }
}
