package reports;


import driver.DriverManager;
import helpers.SystemsHelper;
import io.qameta.allure.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;


// allure serve target/allure-results: lệnh chạy báo cáo

public class AllureManager {
    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    //Screenshot attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static void delReport() {

        String directoryPath = SystemsHelper.getCurrentDir() + "target/allure-results/";
        File directory = new File(directoryPath);

        try {
            FileUtils.deleteDirectory(directory);

            System.out.println("Allure results directory deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error occurred while deleting Allure results directory: " + e.getMessage());
        }
    }


}

