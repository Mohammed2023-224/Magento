package engine.listeners;

import engine.evidence.ScreenshotManager;
import engine.logger.CustomLogger;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.util.ArrayList;
import java.util.Arrays;

public class TestngListener implements ITestListener, IExecutionListener, IRetryAnalyzer, IHookable {
    //TODO delete log files after every test
    int testcaseCount = 0;
    int successfulTestCases = 0;
    int failedTestCases = 0;
    int skippedTestCases = 0;
    ArrayList<String> success = new ArrayList<>();
    static int counter = 0;
    static int retryLimit = 4;


    public void onTestStart(ITestResult result) {
        CustomLogger.logger.info("starting test: " + result.getName());

        testcaseCount++;
    }

    public void onTestSuccess(ITestResult result) {
        CustomLogger.logger.info("Test success hooray: " + result.getName());
        success.add(result.getName());
        successfulTestCases++;
    }


    public void onTestFailure(ITestResult result) {
        WebDriver mainDriver = (WebDriver) result.getTestContext().getAttribute("driver");
        failedTestCases++;
        CustomLogger.logger.info("Test failed: " + result.getName());
        //screenshot to folder
        ScreenshotManager.takeScreenShotFolder(mainDriver, result.getName());
        //screenshot in allure report
        AllureListener.saveScreenShot(mainDriver, result.getName() + " failure screenshot ");
        AllureListener.saveTextLog("Test failed: " + result.getName());
        retry(result);
        CustomLogger.logger.info("retried test case for: " + counter + " times");

    }


    @Override
    public boolean retry(ITestResult iTestResult) {
        if (counter <= retryLimit) {
            counter++;
            CustomLogger.logger.info("ended retry number: " + counter);
            return true;
        }
        return false;
    }

    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        callBack.runTestMethod(testResult);
        if (testResult.getThrowable() != null) {
            if (retry(testResult)) {
                callBack.runTestMethod(testResult);
            }
        }
    }

    public void onFinish(ITestContext context) {
        CustomLogger.logger.info("Finished " + testcaseCount + " test cases: " + Arrays.toString(ListenerHelpers.getAllTestMethodNames(context)));
        CustomLogger.logger.info(successfulTestCases + " test cases successful " + success);
        CustomLogger.logger.info(failedTestCases + " test cases failed" + Arrays.toString(ListenerHelpers.getFailedTestMethodNames(context)));
        CustomLogger.logger.info(skippedTestCases + " test cases skipped" + Arrays.toString(ListenerHelpers.getSkippedTestMethodNames(context)));
    }

    public void onStart(ITestContext context) {
        AllureListener.deleteAllurePastRuns();
//        Allure.getLifecycle();
    }

    public void onExecutionStart() {
        CustomLogger.logger.info("start execution");
    }

    public void onExecutionFinish() {
        CustomLogger.logger.info("end execution");
    }


}
