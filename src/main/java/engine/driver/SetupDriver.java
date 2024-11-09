package engine.driver;

import engine.browsers.Chrome;
import engine.browsers.Edge;
import engine.browsers.FireFox;
import engine.constants.FrameWorkConstants;
import engine.logger.CustomLogger;
import org.openqa.selenium.WebDriver;

public class SetupDriver {
    public WebDriver driver;

    public WebDriver startDriver() {
        if (FrameWorkConstants.browser.equalsIgnoreCase("edge")) {
            return new Edge().initDriver(Options.option());
        } else if (FrameWorkConstants.browser.equalsIgnoreCase("chrome")) {
            return new Chrome().initDriver(Options.option());
        } else if (FrameWorkConstants.browser.equalsIgnoreCase("firefox")) {
            return new FireFox().initDriver(Options.option());
        } else {
            CustomLogger.logger.fatal("Can't start driver not a correct browser or not specified correctly");
            return null;
        }
    }


    public WebDriver startDriverWhenAllIsChosen(String browser) {
        if (browser.equalsIgnoreCase("edge")) {
            return new Edge().initDriver(Options.option());
        } else if (browser.equalsIgnoreCase("chrome")) {
            return new Chrome().initDriver(Options.option());
        } else if (browser.equalsIgnoreCase("firefox")) {
            return new FireFox().initDriver(Options.option());
        } else {
            CustomLogger.logger.fatal("Can't start driver not a correct browser or not specified correctly");
            return null;
        }
    }

}
