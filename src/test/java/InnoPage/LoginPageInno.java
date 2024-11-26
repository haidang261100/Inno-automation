package InnoPage;

import constant.ConstantGlobal;
import helpers.PropertiesHelper;
import helpers.SystemsHelper;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;

import static Keywords.WebUi.*;

public class LoginPageInno {
    String URL = "https://"+ ConstantGlobal.ENVIRONMENT + ".kingwork.vn/login";
    String expectedUrl = "https://"+ ConstantGlobal.ENVIRONMENT + ".kingwork.vn/dashboard";


//    ant-notification-notice-description
//    Username does not exist or password is invalid


    By notiErrorLogin = By.className("ant-notification-notice-description");
    By usernameInput = By.id("username");
    By passwordInput = By.id("password");
    By loginButton = By.xpath("//button[@type = 'submit']");
    By dashboardPage = By.xpath("//span[text()='Dashboard']");

    public void verifyErrorMessageDisplay(String expected, String message) {
        Assert.assertTrue(getWebElement(notiErrorLogin).isDisplayed(), "FAIL. Error message no displays.");
        Assert.assertEquals(getTextElement(notiErrorLogin), expected, message);
    }


    public void enterUsername(String userName) {
        setText(usernameInput, userName);

    }

    public void enterPassword(String password) {
        setText(passwordInput, password);

    }

    public void clickBtnLogin() {
        clickElement(loginButton);

    }

    public void loginPass(String userName, String password) {
        openURL(URL);
        enterUsername(userName);
        enterPassword(password);
        clickBtnLogin();
        waitForElementVisible(dashboardPage);
        String actualUrl = getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "AcuaURL and expectedURL don't match");
    }
    public void loginFail(String userName, String password) {
        openURL(URL);
        enterUsername(userName);
        enterPassword(password);
        clickBtnLogin();
        waitForElementVisible(notiErrorLogin);
        verifyErrorMessageDisplay("Username does not exist or password is invalid", "không trùng khớp tên thông báo");

    }

}
