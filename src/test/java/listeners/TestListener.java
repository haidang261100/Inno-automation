package listeners;

import helpers.CaptureHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.AllureManager;
import utils.LogUtils;

public class TestListener implements ITestListener {
    CaptureHelper cap = new CaptureHelper();


    @Override
    public void onStart(ITestContext result) {
        //Khởi tạo report (Allure)
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("Test case " + result.getName() + " is failed.");
        //Screenshot khi fail
            cap.captureScreenshot(result.getMethod().getMethodName(),result.getMethod().getRealClass().getSimpleName());
        LogUtils.error(result.getThrowable().toString());


        //Allure Report
        AllureManager.saveTextLog(result.getName() + " is failed.");
        AllureManager.saveScreenshotPNG();
    }
}

