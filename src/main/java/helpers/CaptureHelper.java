package helpers;

import driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CaptureHelper {


    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");


    public  void captureScreenshot(String screenshotName, String className) {


        try {
            // Tạo tham chiếu đối tượng của TakesScreenshot với dirver hiện tại
            TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
            // Gọi hàm getScreenshotAs để chuyển hóa hình ảnh về dạng FILE
            File source = ts.getScreenshotAs(OutputType.FILE);
            //Kiểm tra folder nếu không tồn tại thì tạo folder
            File theDir = new File(SystemsHelper.getCurrentDir() + "Reports/ScreenShort/"  + className);
            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            // Chổ này đặt tên thì truyền biến "screenName" gán cho tên File chụp màn hình
            FileHandler.copy(source, new File( theDir + File.separator +  screenshotName +  ".png"));
//            System.out.println("Screenshot taken: " + screenshotName);
//            System.out.println("Screenshot taken current URL: " + DriverManager.getDriver().getCurrentUrl());
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }
}
