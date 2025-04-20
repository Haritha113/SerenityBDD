//package Utils;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import net.serenitybdd.core.Serenity;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//
//// Serenity itself handles WebDriver initialization and cleanup at the test level.
//
//public class DriverFactory {
//    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
//
//    public static void getDriver() {
//        if (driver.get() == null) {
//            createDriver();
//        }
//        driver.get();
//    }
//
//    private static void createDriver() {
//        // Get the browser name from Serenity's configuration
//        String browser = Serenity.environmentVariables().getProperty("serenity.webdriver.driver", "chrome").toLowerCase();
//
//        WebDriver newDriver;
//
//        switch (browser) {
//            case "firefox":
//                WebDriverManager.firefoxdriver().setup();
//                FirefoxOptions firefoxOptions = new FirefoxOptions();
//                newDriver = new FirefoxDriver(firefoxOptions);
//                break;
//            case "chrome":
//            default:
//                WebDriverManager.chromedriver().setup();
//                ChromeOptions chromeOptions = new ChromeOptions();
//                if (System.getProperty("headless", "false").equalsIgnoreCase("true")) {
//                    chromeOptions.addArguments("--headless");
//                }
//                chromeOptions.addArguments("--incognito");
//                newDriver = new ChromeDriver(chromeOptions);
//                break;
//        }
//
//        newDriver.manage().window().maximize();
//        driver.set(newDriver);
//    }
//
//    public static void quitDriver() {
//        if (driver.get() != null) {
//            driver.get().quit();
//            driver.remove();
//        }
//    }
//}
