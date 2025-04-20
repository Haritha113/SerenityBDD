package stepdefinitions;
import Pages.LoginPage;
import Pages.UserMangmntPage;
import Utils.ConstantsMapper;
import Utils.TestData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import org.junit.Assert;

public class UserMngmntSteps {

    @Steps
    UserMangmntPage userMangmntPage;
    LoginPage loginPage;

    @When("^user logs in with (.*) and (.*)$")
    public void userLogsIn(String username,String password) {
        loginPage.enterLoginCredentials(username,password);
        loginPage.clickOnLogin();
    }

    @And("^user navigates to (.*) tab$")
    public void userNavigatesToTab(String tabname) {
        userMangmntPage.navigateToTab(ConstantsMapper.resolve(tabname));
    }

    @Then("^user should land on (.*) successfully$")
    public void userShouldNavigateToTab(String tabname) {
        tabname = ConstantsMapper.resolve(tabname);
        String getTabName = userMangmntPage.getActiveTabTitle(tabname);
        Assert.assertEquals(tabname,getTabName);
    }

    @When("^User clicks on Add User button$")
    public void userClicksOnAddUserButton() {
        userMangmntPage.addUser();
    }

    @And("User clicks on Save button")
    public void userClicksOnSaveButton() {
        userMangmntPage.submitUserData();
    }

    @And("^User fills details with username password empname for (.*) and (.*)$")
    public void andUserFillsUserDetails(String role, String status) {
        role = ConstantsMapper.resolve(role);
        status = ConstantsMapper.resolve(status);
        TestData.generatedEmpName = UtilMethods.generateRandomEmpName();
        TestData.generatedUsername = UtilMethods.generateRandomUsername();
        TestData.generatedPassword = UtilMethods.generateRandomPassword();
//        System.out.println(TestData.generatedEmpName);
//        System.out.println(TestData.generatedUsername);
//        System.out.println(TestData.generatedPassword);
        TestData.generatedEmpName = userMangmntPage.addUserDetailsAndReturnEmpName(role,status,TestData.generatedEmpName,TestData.generatedUsername,TestData.generatedPassword);

    }

    @Then("^user should be listed in the records table with (.*)$")
    public void checkUserInfo(String role) {
        userMangmntPage.validateUserInTable(TestData.generatedUsername, TestData.generatedEmpName,role);

    }

}
