package Pages;


import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

public class BasePage extends PageObject {


    @Step
    public void clickElement(By locator) {
        clickOn($(locator));
    }

    @Step
    public void typeText(By locator, String text) {
        WebElementFacade element = $(locator);
        element.click();
        element.sendKeys(text);
    }


    @Step
    public String getElementText(By locator) {
        return $(locator).getText(); // default timer in serenity is 5 sec, we can configure in config file
    }

    @Step
    public boolean isElementDisplayed(By locator){
        return $(locator).isDisplayed();
    }

    // Add any more reusable methods here
}

