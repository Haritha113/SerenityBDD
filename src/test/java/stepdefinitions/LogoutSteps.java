package stepdefinitions;

import Pages.LogoutPage;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

public class LogoutSteps {


    @Steps
    LogoutPage logoutPage; // Serenity will inject the PageObject here

    /*
    WebDriver driver = DriverFactory.getDriver();
    LogoutPage logoutPage = new LogoutPage(driver);

    this is not required as we are using serenity bdd, it internally calls driver when we
    */

    @When("user clicks on logout option")
    public void userClicksOnLogoutOption() {
        logoutPage.logout();
    }
}
