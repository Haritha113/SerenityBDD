package Pages;

import net.serenitybdd.annotations.DefaultUrl;
import org.openqa.selenium.By;


@DefaultUrl("/web/index.php/auth/login")
//serenity uses base.url + relative path
public class LoginPage extends BasePage {


    public boolean isUserOnLoginPage() {
        return isElementDisplayed((By.className("orangehrm-login-branding")));
    }

    public boolean isDashboardAvailable() {
        return isElementDisplayed((By.cssSelector("div.oxd-topbar-header-title")));
    }

    public void enterLoginCredentials(String username, String password) {
        typeText(By.name("username"), username);
        typeText(By.name("password"), password);
    }

    public void clickOnLogin() {
        clickElement(By.xpath("//button[@type='submit']"));
    }

    public String checkForInvalidLogin() {
        return getElementText(By.xpath("//div[contains(@class,'oxd-alert-content--error')]//p"));
    }
}
