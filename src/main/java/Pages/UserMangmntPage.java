package Pages;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class UserMangmntPage extends BasePage {

    public void addUser() {
        clickElement((By.xpath("//button//i[starts-with(@class,'oxd-icon bi-plus')]")));
    }

    public void navigateToTab(String tabname) {
        clickElement(By.xpath("//span[text()='" + tabname + "']"));
    }


    public String getActiveTabTitle(String tabname) {
        return getElementText(By.xpath("//div[contains(@class,'oxd-topbar-header-title')]//h6[1]"));
    }

    public void submitUserData() {
        clickElement(By.xpath("//button[@type='submit']"));
    }

    public String addUserDetailsAndReturnEmpName(String role, String status, String generatedEmpName, String generatedUsername, String generatedPassword) {
        clickElement((By.xpath("//label[text()='User Role']//..//..//..//div[contains(@class,'oxd-select-wrapper')]")));
        clickElement((By.xpath("//span[text()='"+ role +"']")));

        typeText(By.xpath("//label[text()='Username']//..//..//..//input[contains(@class,'oxd-input oxd-input--active')]"),generatedUsername);


        clickElement(By.xpath("//label[text()='Status']//..//..//..//div[contains(@class,'oxd-select-wrapper')]"));
        clickElement(By.xpath("//span[text()='"+ status +"']"));

        typeText(By.xpath("//label[text()='Employee Name']//..//..//..//div[contains(@class,'oxd-autocomplete-wrapper')]//input"),generatedEmpName);

        System.out.println("element clicked");
        System.out.println("After entering empname");

//        $(By.xpath("//div[@role='listbox']//div[text()='Searching....']")).waitUntilNotVisible();

        $(By.xpath("//div[@role='listbox']//div[text()='Searching....']")).withTimeoutOf(Duration.ofSeconds(8)).waitUntilNotVisible();


        WebElementFacade firstOption = $(By.xpath("//div[@role='listbox']//div[contains(@class,'oxd-autocomplete-option')]")).waitUntilPresent();

        System.out.println("Dropdown suggestion: " + firstOption.getText());

        firstOption.click();

        String selectedName = $(By.xpath("//input[@placeholder='Type for hints...']")).getValue();
        System.out.println("Selected Employee Name: " + selectedName);

        if (selectedName == null || selectedName.trim().isEmpty() || selectedName.equals("Searching....")) {
            throw new RuntimeException("No valid employee name selected. Cannot continue with user creation.");
        }


        typeText(By.xpath("//label[text()='Password']//..//..//..//input[contains(@class,'oxd-input oxd-input--focus')]"),generatedPassword);


        typeText(By.xpath("//label[text()='Confirm Password']//..//..//..//input[contains(@class,'oxd-input oxd-input--active')]"),generatedPassword);

        return selectedName;


    }

    public void validateUserInTable(String generatedUsername, String generatedEmpName, String role) {
        List<WebElementFacade> rows = $$((By.xpath("//div[@role='rowgroup']/div[contains(@class, 'oxd-table-row')]")));

        for (WebElementFacade row : rows) {
            List<WebElement> cells = row.findElements(By.xpath(".//div[contains(@class, 'oxd-table-cell')]"));
            if (cells.size() > 1) {
                String cellText = cells.get(1).getText().trim(); // Assuming username is column 2
                if (cellText.equals(generatedUsername)) {
                    System.out.println("Found user row:");
                    for (WebElement cell : cells) {
                        System.out.println(" → " + cell.getText());
                    }
                    break;
                }
            }
        }


    }
}