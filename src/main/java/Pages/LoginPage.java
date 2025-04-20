package Pages;

import net.serenitybdd.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


@DefaultUrl("/web/index.php/auth/login")
//serenity uses base.url + relative path
public class LoginPage extends BasePage {

    /* public LoginPage(WebDriver driver) {
        super(driver); // this passes the WebDriver to BasePage
    }
     this is not required as serenity pageobjects is used here

     we can use getDriver() or $() t=inplace of driver

      WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        usernameInput.sendKeys(username);
        getDriver().findElement(By.name("password")).sendKeys(password);
    }

    public void clickOnLogin() {
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
    }
     */

    public boolean isUserOnLoginPage() {
        return isElementDisplayed($(By.className("orangehrm-login-branding")));
    }

    public boolean isDashboardAvailable() {
        return isElementDisplayed($(By.cssSelector("div.oxd-topbar-header-title")));
    }

    public void enterLoginCredentials(String username, String password) {
        typeText($(By.name("username")), username);
        typeText($(By.name("password")), password);
    }

    public void clickOnLogin() {
        clickElement($(By.xpath("//button[@type='submit']")));
    }

    public String checkForInvalidLogin() {
        return getElementText($(By.xpath("//div[contains(@class,'oxd-alert-content--error')]//p")));
    }
}
