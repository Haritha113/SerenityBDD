package Pages;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class UserMangmntPage extends BasePage {

    private final By usernameField = By.xpath("//label[text()='Username']//..//..//..//input[contains(@class,'oxd-input--active')]");


    @Step
    public void addUser() {
        clickElement((By.xpath("//button//i[starts-with(@class,'oxd-icon bi-plus')]")));
    }

    @Step
    public void navigateToTab(String tabname) {
        clickElement(By.xpath("//span[text()='" + tabname + "']"));
    }

    @Step
    public String getActiveTabTitle(String tabname) {
        return getElementText(By.xpath("//div[contains(@class,'oxd-topbar-header-title')]//h6[1]"));
    }

    @Step
    public void addUserData() {
        clickOn($(By.xpath("//button[contains(@class,'oxd-button--secondary') and contains(.,'Add')]")).waitUntilClickable());
    }

    @Step
    public void selectFromDropdown(String label, String optionText) {
        By dropdown = By.xpath("//label[text()='" + label + "']//..//..//..//div[contains(@class,'oxd-select-wrapper')]");
        clickElement(dropdown);
        clickElement(By.xpath("//span[text()='" + optionText + "']"));
    }

    @Step
    public void addUserRole(String role) {
        // checks user is on add user info page
        $(By.xpath("//h6[contains(@class,'orangehrm-main-title') and text()='Add User']")).waitUntilVisible();

        selectFromDropdown("User Role", role);

    // UI Issue, after entering role, its redirecting to previous page, so failing if we check again user info page locator
    }

    @Step
    public void addUserName(String generatedUsername){
        // input username
        typeText(usernameField,generatedUsername);

    }
    @Step
    public void addStatus(String status){
        selectFromDropdown("Status", status);
    }

    @Step
    public void addPassword(String generatedPassword) {
        typeText(By.xpath("//label[text()='Password']//..//..//..//input[contains(@class,'oxd-input oxd-input--focus')]"), generatedPassword);
        typeText(By.xpath("//label[text()='Confirm Password']//..//..//..//input[contains(@class,'oxd-input oxd-input--active')]"), generatedPassword);

        //save details
        clickOn($(By.xpath("//button[contains(.,'Save')]")));
    }

    @Step
    public String selectEmpNameFromAutoComplete(String generatedEmpName) {

        By employeeNameField = By.xpath("//label[text()='Employee Name']//..//..//..//div[contains(@class,'oxd-autocomplete-wrapper')]//input");
        By searchingLabel = By.xpath("//div[@role='listbox']//div[text()='Searching....']");
        By firstOption = By.xpath("//div[@role='listbox']//div[contains(@class,'oxd-autocomplete-option')]");
        By selectedInput = By.xpath("//input[@placeholder='Type for hints...']");

        typeText(employeeNameField,generatedEmpName);

        $(searchingLabel).waitUntilNotVisible();

        WebElementFacade firstOptionInDropDown = $(firstOption).waitUntilPresent();

        System.out.println("Dropdown suggestion: " + firstOptionInDropDown.getText());
        firstOptionInDropDown.click();

        // returning employee name to use in search
        String selectedName = $(selectedInput).getValue();
        System.out.println("Selected Employee Name: " + selectedName);

        if (selectedName == null || selectedName.trim().isEmpty() || selectedName.equals("Searching....")) {
            throw new RuntimeException("No valid employee name selected. Cannot continue with user creation.");
        }
        return selectedName;
    }

    @Step
    public void searchUserRecord(String generatedUsername, String generatedEmpName, String role,String status) {

        selectFromDropdown("User Role", role);
        selectFromDropdown("Status", status);
        selectEmpNameFromAutoComplete(generatedEmpName);
        addUserName(generatedUsername);

        clickElement($(By.xpath("//button[@type='submit' and contains(.,'Search')]")));

    }

    @Step
    public Map<String, String> verifyUserRecord(String generatedUsername, String generatedEmpName, String role, String status) {


        String baseXpath = "(//div[contains(@class,'oxd-table-card')]//div[contains(@class,'oxd-table-row oxd-table-row--with-border')]//div[not(@class)])[index]";

        Map<String,String> userData = new HashMap<>();
        userData.put("userName",getElementText(By.xpath(baseXpath.replace("index","1"))));
        userData.put("empName",getElementText(By.xpath(baseXpath.replace("index","3"))));
        userData.put("role",getElementText(By.xpath(baseXpath.replace("index","2"))));
        userData.put("status",getElementText(By.xpath(baseXpath.replace("index","4"))));

        return userData;
    }


    @Step
    public void verifyNoRecordToaster(String generatedUsername, String generatedEmpName, String role,String status) {

        searchUserRecord(generatedUsername,generatedEmpName,role,status);
//        #1e6ceb background-color
//                --oxd-primary-font-color: #FFFFFF;
//        --oxd-secondary-four-color: #76BC21;
//        --oxd-secondary-font-color: #FFFFFF;
        //div[@id='oxd-toaster_1']
        //div[contains(@class,'oxd-toast-content oxd-toast-content--info')]//p[text()='No Records Found']

    }


    @Step
    public void resetSearch(){
        //all fields should be empty
        By resetButton = By.xpath("//button[text()='Reset']");
    }
}