package stepdefinitions;
import Pages.LoginPage;
import Pages.UserMangmntPage;
import Utils.ConstantsMapper;
import Utils.TestData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

public class UserMngmntSteps {


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
        Assertions.assertEquals(tabname,getTabName);
    }

    @When("^User clicks on Add User button$")
    public void userClicksOnAddUserButton() {
        userMangmntPage.addUser();
    }

    @And("User clicks on Save button")
    public void userClicksOnSaveButton() {
        userMangmntPage.addUserData();
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

        userMangmntPage.addUserRole(role);
        userMangmntPage.addStatus(status);
        userMangmntPage.addUserName(TestData.generatedUsername);
        userMangmntPage.addPassword(TestData.generatedPassword);
        TestData.generatedEmpName = userMangmntPage.selectEmpNameFromAutoComplete(TestData.generatedEmpName);
        System.out.println(TestData.generatedEmpName);

    }


    @And("^search and verify for user in the records table with (.*) (.*)$")
    public void searchUserInfo(String role,String status) {
        role = ConstantsMapper.resolve(role);
        status = ConstantsMapper.resolve(status);
        userMangmntPage.searchUserRecord(TestData.generatedUsername, TestData.generatedEmpName,role,status);
        Map<String, String> actualData = userMangmntPage.verifyUserRecord(TestData.generatedUsername, TestData.generatedEmpName,role,status);
        Assertions.assertEquals(TestData.generatedUsername, actualData.get("userName"));
        Assertions.assertEquals(role, actualData.get("role"));
        Assertions.assertEquals(TestData.generatedEmpName, actualData.get("empName"));
        Assertions.assertEquals(status, actualData.get("status"));

    }

}
