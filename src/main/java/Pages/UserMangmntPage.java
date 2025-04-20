package Pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class UserMangmntPage extends BasePage {

    // Add a new user by clicking the "Add User" button
    public void addUser() {
       clickElement($(By.xpath("//button//i[starts-with(@class,'oxd-icon bi-plus')]")));
    }

    // Navigate to a specific tab based on the tab name
    public void navigateToTab(String tabName) {
        clickElement($(By.xpath("//span[text()='" + tabName + "']")));

        // Consider waiting for the tab to load or something similar if necessary

        withTimeoutOf(Duration.ofSeconds(6)).waitFor((By) $(By.xpath("//span[text()='" + tabName + "']")));
    }

    // Get the title of the currently active tab
    public String getActiveTabTitle() {
        return $(By.xpath("//div[contains(@class,'oxd-topbar-header-title')]//h6[1]")).getText();
    }

    // Submit the user data after entering the necessary information
    public void submitUserData() {
        $(By.xpath("//button[@type='submit']")).click();
    }

    // Add user details and return the employee name
    public String addUserDetailsAndReturnEmpName(String role, String status, String empName, String username, String password) {
        selectDropdownOption("//label[text()='User Role']//..//..//..//div[contains(@class,'oxd-select-wrapper')]", role);
        enterText("//label[text()='Username']//..//..//..//input[contains(@class,'oxd-input oxd-input--active')]", username);
        selectDropdownOption("//label[text()='Status']//..//..//..//div[contains(@class,'oxd-select-wrapper')]", status);
        String employeeName = selectEmployeeName(empName);
        enterText("//label[text()='Password']//..//..//..//input[contains(@class,'oxd-input oxd-input--focus')]", password);
        enterText("//label[text()='Confirm Password']//..//..//..//input[contains(@class,'oxd-input oxd-input--active')]", password);

        return employeeName;
    }

    // Select an option from a dropdown based on the visible text
    private void selectDropdownOption(String xpath, String optionText) {
        $(By.xpath(xpath)).click();
        $(By.xpath("//span[text()='" + optionText + "']")).click();
    }

    // Enter text into an input field located by the given xpath
    private void enterText(String xpath, String text) {
        WebElement element = $(By.xpath(xpath));
        element.click();
        element.sendKeys(text);
    }

    // Select an employee from the autocomplete dropdown and return the selected name
    private String selectEmployeeName(String empName) {
        WebElement empNameField = $(By.xpath("//label[text()='Employee Name']//..//..//..//div[contains(@class,'oxd-autocomplete-wrapper')]//input"));
        empNameField.click();
        empNameField.sendKeys(empName);

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='listbox']//div[text()='Searching....']")));

        WebElement firstOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='listbox']//div[contains(@class,'oxd-autocomplete-option')]")));
        firstOption.click();

        String selectedName = $(By.xpath("//input[@placeholder='Type for hints...']")).getAttribute("value");

        if (selectedName == null || selectedName.trim().isEmpty() || selectedName.equals("Searching....")) {
            throw new RuntimeException("No valid employee name selected. Cannot continue with user creation.");
        }

        return selectedName;
    }

    // Validate that the user exists in the user table by matching the generated username and employee name
    public void validateUserInTable(String generatedUsername, String generatedEmpName, String role) {
        List<WebElement> rows = getDriver().findElements(By.xpath("//div[@role='rowgroup']/div[contains(@class, 'oxd-table-row')]"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.xpath(".//div[contains(@class, 'oxd-table-cell')]"));
            if (cells.size() > 1) {
                String usernameCellText = cells.get(1).getText().trim(); // Assuming username is in column 2
                if (usernameCellText.equals(generatedUsername)) {
                    System.out.println("Found user row:");
                    cells.forEach(cell -> System.out.println(" → " + cell.getText()));
                    break;
                }
            }
        }
    }
}
