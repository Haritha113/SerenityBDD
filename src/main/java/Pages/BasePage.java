package Pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;

public class BasePage extends PageObject {

    public void clickElement(By locator) {
        $(locator).click();
    }

    public void typeText(By locator, String text) {
        $(locator).sendKeys(text);
    }

    public String getElementText(By locator) {
        return $(locator).getText();
    }

    public boolean isElementDisplayed(By locator){
        return $(locator).isDisplayed();
    }

    // Add any more reusable methods here
}

