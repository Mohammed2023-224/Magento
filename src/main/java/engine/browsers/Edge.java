package engine.browsers;

import engine.driver.Options;
import engine.logger.CustomLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.ArrayList;

public class Edge {
    private WebDriver driver;

    public EdgeOptions edgeOptions(ArrayList<String> options) {
        EdgeOptions option = new EdgeOptions();
        if (options.size() > 0) {
            CustomLogger.logger.info("Start edge browser with options: {}", options);
            option.addArguments(options);
        } else {
            CustomLogger.logger.info("Start edge browser");
        }
        return option;
    }

    public WebDriver initDriver() {
        return new EdgeDriver(edgeOptions(Options.option()));
    }
}
