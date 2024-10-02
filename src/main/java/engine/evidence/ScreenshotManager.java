package engine.evidence;

import engine.constants.FrameWorkConstants;
import engine.logger.CustomLogger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotManager {

    public static void takeScreenShotFolder(WebDriver driver, String testName) {
        File SrcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        String shotName = testName + " " + dtf.format(now) + ".png";
        File DestFile = new File(FrameWorkConstants.screenshotsPath + shotName);
        CustomLogger.logger.info("Saving screenshot to: " + FrameWorkConstants.screenshotsPath + shotName);
        try {
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException e) {
            CustomLogger.logger.error("can't save file");
        }
    }
}
