package engine.browsers;

import engine.logger.CustomLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.ArrayList;

public class FireFox {
    private WebDriver driver;

    public WebDriver initDriver(ArrayList<String> options) {
        FirefoxOptions option = new FirefoxOptions();
        option.addArguments(options);
        if (options.size() > 0) {
            CustomLogger.logger.info("Start firefox browser with options: " + options);
        } else {
            CustomLogger.logger.info("Start firefox browser");
        }
        return new FirefoxDriver(option);
    }
}
