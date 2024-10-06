package engine.browsers;

import engine.logger.CustomLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;

public class Chrome {
    private WebDriver driver;

    public WebDriver initDriver(ArrayList<String> options) {
        ChromeOptions option = new ChromeOptions();
        option.addArguments(options);
        if (options.size() > 0) {
            CustomLogger.logger.info("Start chrome browser with options: " + options);
        } else {
            CustomLogger.logger.info("Start chrome browser");
        }
        return new ChromeDriver(option);
    }
}
