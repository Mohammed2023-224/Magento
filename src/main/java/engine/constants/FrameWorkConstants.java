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
    //    static public final String browser = ReadProperties.getProperty("browser");
    static public final String screenshotsPath = ReadProperties.getProperty("screenshotsPath");
    static public final String testLogFile = ReadProperties.getProperty("testsLogFile");
    static public final String allureDirectory = ReadProperties.getProperty("allureDirectory");
    static public final String testData = ReadProperties.getProperty("testData");
    static public final String completeLogFile = ReadProperties.getProperty("completeLogFile");
    static public final String allureExecutable = ReadProperties.getProperty("allureExec");

    //setup data
    static ReadExcel r = new ReadExcel();
    public static String headlessMode = r.readCertainCell(testAutomationSetup, "sheet", "Value", "Headless").toLowerCase();
    public static String maximized = r.readCertainCell(testAutomationSetup, "sheet", "Value", "Maximized");
    public static String extensions = r.readCertainCell(testAutomationSetup, "sheet", "Value", "Extension");
    public static String noSandBox = r.readCertainCell(testAutomationSetup, "sheet", "Value", "No sandbox");
    public static String disableDevSHM = r.readCertainCell(testAutomationSetup, "sheet", "Value", "Disable Dev Shm Usage");
    public static String browser = r.readCertainCell(testAutomationSetup, "sheet", "Value", "Browser");
    public static String openAllure = r.readCertainCell(testAutomationSetup, "sheet", "Value", "Open allure");
    public static String destination = r.readCertainCell(testAutomationSetup, "sheet", "Value", "Proxy");


}
