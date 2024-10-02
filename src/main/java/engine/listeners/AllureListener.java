package engine.listeners;

import engine.logger.CustomLogger;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AllureListener implements ITestListener {

    static String allurePath = "allure-results";

    public static void saveScreenShot(WebDriver driver, String text) {
        CustomLogger.logger.info("Taking screenshot and saving to allure report");
        Allure.addAttachment(text, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    public static void saveTextLog(String message) {
        String filePath = "test_output/output/logs/firstLog.log";
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Allure.addAttachment("logs", fis);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteAllurePastRuns() {
        File directory = new File(allurePath);
        try {
            FileUtils.forceDelete(directory);
            CustomLogger.logger.info("Deleted the old allure reports");
        } catch (Exception e) {
            CustomLogger.logger.info("couldn't delete file");
        }
    }
}
