package engine.driver;

import engine.browsers.Chrome;
import engine.browsers.Edge;
import engine.browsers.FireFox;
import engine.constants.FrameWorkConstants;
import engine.logger.CustomLogger;
import org.openqa.selenium.WebDriver;

public class SetupDriver {
    public WebDriver driver;

    public WebDriver startDriver(String options) {
        if (FrameWorkConstants.browser.equalsIgnoreCase("edge")) {
            return new Edge().initDriver(options);
        } else if (FrameWorkConstants.browser.equalsIgnoreCase("chrome")) {
            return new Chrome().initDriver(options);
        } else if (FrameWorkConstants.browser.equalsIgnoreCase("firefox")) {
            return new FireFox().initDriver(options);
        } else {
            CustomLogger.logger.fatal("Can't start driver not a correct browser or not specified correctly");
            return null;
        }
    }

}
