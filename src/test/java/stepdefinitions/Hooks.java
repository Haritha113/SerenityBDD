package stepdefinitions;



import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

// Serenity itself handles WebDriver initialization and cleanup at the test level.


    @Before
    public void setup() {
//        basePage.maximizeWindow();
//        DriverFactory.getDriver();  // Initialize the WebDriver using DriverFactory if you want too customizatons in driver
    }

    @After
    public void teardown(){
//        DriverFactory.quitDriver();
    }

}

