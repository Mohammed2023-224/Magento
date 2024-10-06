package engine.browsers;

import engine.logger.CustomLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.ArrayList;

public class Edge {
    private WebDriver driver;

    public WebDriver initDriver(ArrayList<String> options) {
        EdgeOptions option = new EdgeOptions();
        option.addArguments(options);
        if (options.size() > 0) {
            CustomLogger.logger.info("Start edge browser with options: " + options);
        } else {
            CustomLogger.logger.info("Start edge browser");
        }
        return new EdgeDriver(option);
    }
}
