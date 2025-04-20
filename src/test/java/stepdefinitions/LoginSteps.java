package stepdefinitions;

import Pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import org.junit.Assert;

public class LoginSteps {

        @Steps
        LoginPage loginPage;

    @When("^user enters (.*) and (.*)$")
    public void enterCredentials(String username,String password) {
        loginPage.enterLoginCredentials(username,password);
    }

    @Then("User should see the dashboard for valid credentials")
    public void dashboardShouldBeAvailable() {
        Assert.assertTrue(loginPage.isDashboardAvailable());
    }

    @And("User clicks on login button")
    public void userClicksOnLoginButton() {
        loginPage.clickOnLogin();
    }

    @Then("user should be logged out successfully")
    public void userShouldBeLoggedOutSuccessfully() {
        Assert.assertTrue(loginPage.isUserOnLoginPage());
    }

    @Then("^user should see prompt with text (.*)$")
    public void userShouldSeeErrormsg(String errorMsg) {
        Assert.assertEquals(loginPage.checkForInvalidLogin(),errorMsg);
    }

    @Given("the user is on the login page")
    public void getUrl() {
        loginPage.open();
    }
}
