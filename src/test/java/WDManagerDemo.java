import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class WDManagerDemo {

    WebDriver driver;

//    @BeforeSuite
//    public void instantiateDriver() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//    }

    @BeforeTest
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterTest
    public void tearDown() throws Exception {
        if (driver != null)
            driver.quit();
    }

    @Test
    public void test() {
        driver.get("https://www.google.com");
        String pageTitle = driver.getTitle();
        System.out.println("Page Title is: " + pageTitle);
        assertEquals(pageTitle, "Google");
    }
}
