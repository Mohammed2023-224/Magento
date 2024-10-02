package engine.browsers;

import engine.logger.CustomLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;

public class Chrome {
    private WebDriver driver;

    public WebDriver initDriver(String... options) {
        ChromeOptions option = new ChromeOptions();
        option.addArguments(options);
        if (options.length > 0) {
            CustomLogger.logger.info("Start chrome browser with options: " + Arrays.toString(options));
        } else {
            CustomLogger.logger.info("Start chrome browser");
        }
        return new ChromeDriver(option);
    }
}
