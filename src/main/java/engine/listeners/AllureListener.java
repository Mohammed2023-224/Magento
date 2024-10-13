package engine.listeners;

import engine.logger.CustomLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;

import java.io.*;

public class AllureListener implements ITestListener {

    public static void saveScreenShot(WebDriver driver, String text) {
        CustomLogger.logger.info("Taking screenshot and saving to allure report");
        Allure.addAttachment(text, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Attachment(value = "{text}", type = "image/png")
    public static byte[] saveScreenShot(WebDriver driver) {
        CustomLogger.logger.info("Taking screenshot and saving to allure report");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public static void saveTextLog(String filePath) {
        try {
            CustomLogger.logger.info("Attempting to access log file at: " + filePath);
            FileInputStream fis = new FileInputStream(filePath);
            Allure.addAttachment("logs", fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Attachment(value = "logs", type = "text/plain")
    public static byte[] saveLog(String filePath) {
        try {
            CustomLogger.logger.info("Attempting to access log file at: " + filePath);
            FileInputStream fis = new FileInputStream(filePath);
            byte[] content = new byte[(int) new File(filePath).length()];
            fis.read(content);
            return content;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
