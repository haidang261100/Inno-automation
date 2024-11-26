package Keywords;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LogUtils;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import static driver.DriverManager.getDriver;
import static junit.framework.Assert.assertTrue;

public class WebUi {
    private static int EXPLICIT_WAIT_TIMEOUT = 10;
//    private static int WAIT_PAGE_LEADED_TIMEOUT = 30;


    // Tìm element
    public static WebElement getWebElement(By by) {
        return getDriver().findElement(by);
    }

    public static WebElement getWebElements(By by) {
        return (WebElement) getDriver().findElements(by);
    }



    public static By chooseIndexElements(By by, int index){
        waitForElementVisible(by);
        List<WebElement> elements = getDriver().findElements(by);
        WebElement secondElement = elements.get(index);
        System.out.print(elements);

        return by;
    }




    // Hover vào element
    public static void hoverOnElement(By by) {
        waitForElementVisible(by);
        Actions action = new Actions(getDriver());
        action.moveToElement(getWebElement(by));
        LogUtils.info("Hover on element " + by);
    }

    // Highline Element
    public static WebElement highLightElement(By by) {
        waitForElementVisible(by);
        // Tô màu border ngoài chính element chỉ định
        if (getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.border='3px solid red'", getWebElement(by));
        }
        return getWebElement(by);
    }


    // Click chuột phải
    @Step("Right click on element {0}")
    public static void rightClickElement(By by) {
        waitForElementVisible(by);
        Actions action = new Actions(getDriver());
        action.contextClick(getWebElement(by));
        LogUtils.info("Right click on element " + by);
    }

    // Mở URl
    @Step("Open URL: {0}")
    public static void openURL(String URL) {
        getDriver().get(URL);
        LogUtils.info("Open URL: " + URL);
    }

    @Step("Open URL: {0}")
    public static void navi(String URL) {


        Set<Cookie> cookies = getDriver().manage().getCookies();

        // Điều hướng đến trang khác mà không cần đăng nhập lại
        getDriver().get(URL);

        // Thêm cookies đã lưu vào phiên làm việc hiện tại
        for (Cookie cookie : cookies) {
            getDriver().manage().addCookie(cookie);
        }

    }


    // Lấy URL hiện tại
    @Step("Get current URL")
    public static String getCurrentUrl() {
        LogUtils.info("Get Current URL: " + getDriver().getCurrentUrl());
        return getDriver().getCurrentUrl();
    }


    // Click vào element
    @Step("Click on element {0}")
    public static void clickElement(By by) {
        waitForElementVisible(by);
        highLightElement(by);
        getWebElement(by).click();
        LogUtils.info("Click on element " + by);
    }


    // verify khong element khong the click
    @Step(" can't click on element{0}")
    public static void verifyDisableElement(By by) {
        waitForElementVisible(by);
        boolean isDisabled = !getWebElement(by).isEnabled();
        assertTrue("Element should be disabled", isDisabled);


    }


    // Set text
    @Step("Set text {1} on element {0}")
    public static void setText(By by, String value) {
        waitForElementVisible(by);
        getWebElement(by).clear();
        getWebElement(by).sendKeys(value);
        LogUtils.info("Set text " + value + " on element " + by);
    }




    // Lấy text từ 1 element
    @Step("Get text of element {0}")
    public static String getTextElement(By by) {
        waitForElementVisible(by);
        LogUtils.info("Get text of element " + by);
        LogUtils.info("==> Text: " + getWebElement(by).getText());
        return getWebElement(by).getText();
    }


    // Lấy giá trị của 1 element
    @Step("Get attribure {1} value of element {0}")
    public static String getAttributeElement(By by, String attributeName) {
        waitForElementVisible(by);
        LogUtils.info("Get attribute value of element " + by);
        LogUtils.info("==> Attribute value: " + getWebElement(by).getAttribute(attributeName));
        return getWebElement(by).getAttribute(attributeName);
    }

    // Sleep khoàng nghỉ
    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    // Đợi đến khi 1 element có thể dùng được
    public static void waitForElementVisible(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));

        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    // Đợi đến khi 1 đoạn text được hiển thị trong 1 element
    public static void waitForPerFormText(By by, String expectedText) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(by, expectedText));

    }


    // Kiểm tra xem element có tồn tại
    public static boolean checkElementExist(By by) {
        List<WebElement> listElement = getDriver().findElements(by);

        if (listElement.size() > 0) {
            System.out.println("Element " + by + " existing.");
            return true;
        } else {
            System.out.println("Element " + by + " NOT exist.");
            return false;
        }
    }


    public String getCellValue(WebElement table, int rowIndex, int columnIndex) {
        try {
            WebElement row = table.findElement(By.xpath(".//tbody/tr[" + rowIndex + "]"));
            WebElement cell = row.findElement(By.xpath(".//td[" + columnIndex + "]"));
            return cell.getText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Dem cac hang co du lieu trong bang
    public static int getRowCount(By tableLocator, By rowLocator, By cellLocator) {
        WebElement table = getWebElement(tableLocator);
        List<WebElement> rows = table.findElements(rowLocator);
        int rowCount = 0;

        for (WebElement row : rows) {
            // Kiểm tra xem hàng có chứa ô nào không rỗng hay không
            List<WebElement> cells = row.findElements(cellLocator);
            boolean hasData = false;
            for (WebElement cell : cells) {
                if (!cell.getText().trim().isEmpty()) {
                    hasData = true;
                    break;
                }
            }
            if (hasData) {
                rowCount++;
            }
        }

        return rowCount;
    }


    // double click
    public static void doubleClick(By by) {
        waitForElementVisible(by);
        Actions action = new Actions(getDriver());
        action.doubleClick(getWebElement(by)).perform();
        LogUtils.info("Double Click on element" + by);

    }


    // select Text in dropdown by Select
    public static void selectTextInDropdown(By by, String selectText) {
        waitForElementVisible(by);
        Select dropdown = new Select(getWebElement(by));
        dropdown.selectByVisibleText(selectText);
        LogUtils.info("Select Dropdown " + by);
        LogUtils.info("Select " + selectText + " in dropdown " + by);

    }

    // select index in dropdown by Select
    public static void selecIndexInDropdown(By by, int index) {
        waitForElementVisible(by);
        Select dropdown = new Select(getWebElement(by));
        dropdown.selectByIndex(index);
        LogUtils.info("Select Dropdown " + by);
        LogUtils.info("Select " + index + " in dropdown " + by);

    }

    public static void clickInDropdown(By by, By by2, int index) {
        waitForElementVisible(by);
        WebElement dropdownButton = getDriver().findElement(by); // Thay bằng ID thật của nút mở dropdown
        dropdownButton.click();

        // Lấy tất cả các phần tử trong danh sách dropdown
        List<WebElement> dropdownItems = getDriver().findElements(by2); // Thay đổi XPath cho phù hợp với cấu trúc trang

        // Kiểm tra nếu index hợp lệ
        if (index >= 0 && index < dropdownItems.size()) {
            // Click vào phần tử tại index
            dropdownItems.get(index).click();
        }
        LogUtils.info("Select Dropdown " + by);
        LogUtils.info("Select " + index + " in dropdown " + by);

    }

    // select value in dropdown by Select
    public static void selecValueInDropdown(By by, String value) {
        waitForElementVisible(by);
        Select dropdown = new Select(getWebElement(by));
        dropdown.selectByValue(value);
        LogUtils.info("Select Dropdown " + by);
        LogUtils.info("Select " + value + " in dropdown " + by);

    }


    public static void selectAddressByInputGGmap(By by, String keySearch, By elenmentDropdown) {
        try {
            setText(by, keySearch);
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            List<WebElement> suggestions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elenmentDropdown));
            if (!suggestions.isEmpty()) {
                suggestions.get(0).click();
            } else {
                System.out.println("Don't find suggestion");
            }
        } finally {

        }
    }


    public static void waitForElementRefresh(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));

        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void verifyNotiAfterAction(By by, String expected){

        waitForElementVisible(by);
        WebElement a = getWebElement(by);
        String notiText = a.getText();
        Assert.assertEquals( " don't match",expected ,notiText);
    }





}
