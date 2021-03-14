import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
public class WDManagerAgentTest {

    public void chromeDriverTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/");
        driver.quit();
    }

    public void firefoxDriverTest() {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.selenium.dev/");
        driver.quit();
    }

}
