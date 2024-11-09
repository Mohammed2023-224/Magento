package test;

import engine.constants.FrameWorkConstants;
import engine.driver.SetupDriver;
import engine.listeners.TestngListener;
import engine.logger.CustomLogger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

@Listeners(TestngListener.class)
public class BaseTests {
    public WebDriver driver;
    public String testData = FrameWorkConstants.testData;

    @BeforeClass
    @Parameters("browser")
    public void initDriver(ITestContext context, @Optional("edge") String browser) {
        if (FrameWorkConstants.browser.equalsIgnoreCase("all")) {
            driver = new SetupDriver().startDriverWhenAllIsChosen(browser);
        } else {
            driver = new SetupDriver().startDriver();
        }
        CustomLogger.logger.info(driver);
        context.setAttribute("driver", driver);
    }

    @AfterMethod
    public void refreshAndDeleteCookies() {
        driver.manage().deleteAllCookies();
        CustomLogger.logger.info("delete all cookies");
    }

    @AfterClass
    public void tearDriver() {
        driver.close();
    }

}
