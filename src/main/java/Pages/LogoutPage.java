package Pages;

import net.serenitybdd.annotations.Step;
import org.openqa.selenium.By;

public class LogoutPage extends BasePage {


    @Step
    public void logout(){
        clickElement(By.cssSelector("p.oxd-userdropdown-name"));
        clickElement(By.xpath("//a[@href='/web/index.php/auth/logout']"));
    }
}
