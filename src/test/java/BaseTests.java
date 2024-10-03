import engine.constants.FrameWorkConstants;
import engine.driver.SetupDriver;
import engine.logger.CustomLogger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;

public class BaseTests {
    public WebDriver driver;
    public String testData = FrameWorkConstants.completeLogFile;

    @BeforeClass
    public void initDriver(ITestContext context) {
        driver = new SetupDriver().startDriver(options());
        CustomLogger.logger.info(driver);
        context.setAttribute("driver", driver);
    }

    @AfterClass
    public void tearDriver() {
        driver.close();
    }


    private String options() {
        ArrayList<String> ops = new ArrayList<>();
        ops.add("--start-maximized");
//        ops.add("--headless");
        return ops.toString().replaceAll("\\]", "").replaceAll("\\[", "");
    }
}
