package engine.browsers;

import engine.logger.CustomLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.Arrays;

public class Edge {
    private WebDriver driver;

    public WebDriver initDriver(String... options) {
        EdgeOptions option = new EdgeOptions();
        option.addArguments(options);
        if (options.length > 0) {
            CustomLogger.logger.info("Start edge browser with options: " + Arrays.toString(options));
        } else {
            CustomLogger.logger.info("Start edge browser");
        }
        return new EdgeDriver(option);
    }
}
