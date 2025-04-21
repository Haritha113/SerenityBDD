package Pages;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.annotations.Step;
import org.openqa.selenium.By;


@DefaultUrl("/web/index.php/auth/login")
//serenity uses base.url + relative path
public class LoginPage extends BasePage {


    @Step
    public boolean isUserOnLoginPage() {
        return isElementDisplayed((By.className("orangehrm-login-branding")));
    }

    @Step
    public boolean isDashboardAvailable() {
        return isElementDisplayed((By.cssSelector("div.oxd-topbar-header-title")));
    }

    @Step
    public void enterLoginCredentials(String username, String password) {
        typeText(By.name("username"), username);
        typeText(By.name("password"), password);
    }

    @Step
    public void clickOnLogin() {
        clickElement(By.xpath("//button[@type='submit']"));
    }

    @Step
    public String checkForInvalidLogin() {
        return getElementText(By.xpath("//div[contains(@class,'oxd-alert-content--error')]//p"));
    }
}
