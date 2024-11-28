package engine.constants;

import engine.dataDriven.ReadExcel;
import engine.dataDriven.ReadProperties;

public class FrameWorkConstants {

    static {
        ReadProperties.readAllFiles();
    }

    static public final String url = ReadProperties.getProperty("url");
    static public final String propertiesPath = ReadProperties.getProperty("propertiesPath");
    static public final String testAutomationSetup = ReadProperties.getProperty("testAutomationSetup");
    static public final String screenshotsPath = ReadProperties.getProperty("screenshotsPath");
    static public final String testLogFile = ReadProperties.getProperty("testsLogFile");
    static public final String allureDirectory = ReadProperties.getProperty("allureDirectory");
    static public final String testData = ReadProperties.getProperty("testData");
    static public final String completeLogFile = ReadProperties.getProperty("completeLogFile");
    static public final String allureExecutable = ReadProperties.getProperty("allureExec");

    //setup data
    static ReadExcel r = new ReadExcel();
    public static String headlessMode = r.readCertainCell(testAutomationSetup, "Data", "Value", "Headless").toLowerCase();
    public static String maximized = r.readCertainCell(testAutomationSetup, "Data", "Value", "Maximized");
    public static String extensions = r.readCertainCell(testAutomationSetup, "Data", "Value", "Extension");
    public static String noSandBox = r.readCertainCell(testAutomationSetup, "Data", "Value", "No sandbox");
    public static String disableDevSHM = r.readCertainCell(testAutomationSetup, "Data", "Value", "Disable Dev Shm Usage");
    public static String browser = r.readCertainCell(testAutomationSetup, "Data", "Value", "Browser");
    public static String openAllure = r.readCertainCell(testAutomationSetup, "Data", "Value", "Open allure");
    public static String destination = r.readCertainCell(testAutomationSetup, "Data", "Value", "Proxy");
    public static String proxyUrl = r.readCertainCell(testAutomationSetup, "Data", "Value", "Proxy url");

}
