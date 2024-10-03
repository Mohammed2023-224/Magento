package engine.constants;

import engine.dataDriven.ReadProperties;

public class FrameWorkConstants {
    static {
        ReadProperties.readAllFiles();
    }

    static public final String url = ReadProperties.getProperty("url");
    static public final String propertiesPath = ReadProperties.getProperty("propertiesPath");
    static public final String testAutomationSetup = ReadProperties.getProperty("testAutomationSetup");
    static public final String browser = ReadProperties.getProperty("browser");
    static public final String screenshotsPath = ReadProperties.getProperty("screenshotsPath");
    static public final String testLogFile = ReadProperties.getProperty("testsLogFile");
    static public final String allureDirectory = ReadProperties.getProperty("allureDirectory");
    static public final String testData = ReadProperties.getProperty("testData");
    static public final String completeLogFile = ReadProperties.getProperty("completeLogFile");


}
