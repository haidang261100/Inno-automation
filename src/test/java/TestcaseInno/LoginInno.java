package TestcaseInno;

import InnoPage.LoginPageInno;
import common.BaseTest;
import helpers.PropertiesHelper;
import helpers.SystemsHelper;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginInno extends BaseTest {

    LoginPageInno login = new LoginPageInno();
    ArrayList<HashMap<String, String>> loginDataList = PropertiesHelper.readJsonData(SystemsHelper.getCurrentDir() + "src/test/resources/dataSet/loginInno.json");
    HashMap<String, String> data;

    @Test
    public void loginPass() {
        data = loginDataList.get(0);
        login.loginPass(data.get("username"), data.get("pass"));
    }

    @Test
    public void loginFailWithMailWrong() {
        data = loginDataList.get(1);
        login.loginFail(data.get("username"), data.get("pass"));

    }

    @Test
    public void loginFailWithPassWrong() {
        data = loginDataList.get(2);
        login.loginFail(data.get("username"), data.get("pass"));
    }

    @Test
    public void loginFailWithMailandPassWrong() {
        data = loginDataList.get(3);
        login.loginFail(data.get("username"), data.get("pass"));

    }

}
