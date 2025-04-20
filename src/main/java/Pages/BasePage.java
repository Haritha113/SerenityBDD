package Pages;


import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public class BasePage extends PageObject {


    public void clickElement(By locator) {
        $(locator).click();
    }

    public void typeText(By locator, String text) {
        $(locator).sendKeys(text);
    }

    public String getElementText(By locator) {
        return $(locator).waitUntilVisible().getText(); // default timer in serenity is 5 sec, we can configure in config file
    }

    public boolean isElementDisplayed(By locator){
        return $(locator).isDisplayed();
    }

    // Add any more reusable methods here
}

