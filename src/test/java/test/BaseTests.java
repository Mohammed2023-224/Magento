package test;

import engine.constants.FrameWorkConstants;
import engine.driver.SetupDriver;
import engine.listeners.TestngListener;
import engine.logger.CustomLogger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners(TestngListener.class)
public class BaseTests {
    public WebDriver driver;
    public String testData = FrameWorkConstants.testData;

    @BeforeClass
    public void initDriver(ITestContext context) {
        driver = new SetupDriver().startDriver();
        CustomLogger.logger.info(driver);
        context.setAttribute("driver", driver);

    }

    @AfterClass
    public void tearDriver() {
        driver.close();
    }

}
