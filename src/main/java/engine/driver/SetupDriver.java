package engine.driver;

import engine.browsers.Chrome;
import engine.browsers.Edge;
import engine.browsers.FireFox;
import engine.constants.FrameWorkConstants;
import engine.logger.CustomLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SetupDriver {
    public WebDriver driver;

    public WebDriver startDriver() {
        if (FrameWorkConstants.browser.equalsIgnoreCase("edge")) {
            return new Edge().initDriver();
        } else if (FrameWorkConstants.browser.equalsIgnoreCase("chrome")) {
            return new Chrome().initDriver();
        } else if (FrameWorkConstants.browser.equalsIgnoreCase("firefox")) {
            return new FireFox().initDriver();
        } else {
            CustomLogger.logger.fatal("Can't start driver not a correct browser or not specified correctly");
            return null;
        }
    }

    public WebDriver startDriverRemotely() {
        String url = FrameWorkConstants.proxyUrl;
        if (FrameWorkConstants.browser.equalsIgnoreCase("edge")) {
            try {
                return new RemoteWebDriver(new URL(url), new Edge().edgeOptions(Options.option()));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        } else if (FrameWorkConstants.browser.equalsIgnoreCase("chrome")) {
            try {
                return new RemoteWebDriver(new URL(url), new Chrome().chromeOptions(Options.option()));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        } else if (FrameWorkConstants.browser.equalsIgnoreCase("firefox")) {
            try {
                return new RemoteWebDriver(new URL(url), new FireFox().firefoxOptions(Options.option()));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        } else {
            CustomLogger.logger.fatal("Can't start driver not a correct browser or not specified correctly");
            return null;
        }
    }


    public WebDriver startDriverRemotelyWhenAllIsChosen(String browser) {
        String url = FrameWorkConstants.proxyUrl;
        if (browser.equalsIgnoreCase("edge")) {
            try {
                return new RemoteWebDriver(new URL(url), new Edge().edgeOptions(Options.option()));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        } else if (browser.equalsIgnoreCase("chrome")) {
            try {
                return new RemoteWebDriver(new URL(url), new Chrome().chromeOptions(Options.option()));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        } else if (browser.equalsIgnoreCase("firefox")) {
            try {
                return new RemoteWebDriver(new URL(url), new FireFox().firefoxOptions(Options.option()));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        } else {
            CustomLogger.logger.fatal("Can't start driver not a correct browser or not specified correctly");
            return null;
        }
    }


    public WebDriver startDriverWhenAllIsChosen(String browser) {
        if (browser.equalsIgnoreCase("edge")) {
            return new Edge().initDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            return new Chrome().initDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            return new FireFox().initDriver();
        } else {
            CustomLogger.logger.fatal("Can't start driver not a correct browser or not specified correctly");
            return null;
        }
    }

}
