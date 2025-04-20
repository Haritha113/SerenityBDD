package Pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogoutPage extends BasePage {


    /*
    public LogoutPage(WebDriver driver){
        super(driver);
    }
    not required as we are using serenity pageobject
*/


    public void logout(){
        clickElement($(By.cssSelector("p.oxd-userdropdown-name")));
        clickElement($(By.xpath("//a[@href='/web/index.php/auth/logout']")));
    }
}
