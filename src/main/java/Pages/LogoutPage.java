package Pages;

import org.openqa.selenium.By;

public class LogoutPage extends BasePage {


    public void logout(){
        clickElement(By.cssSelector("p.oxd-userdropdown-name"));
        clickElement(By.xpath("//a[@href='/web/index.php/auth/logout']"));
    }
}
