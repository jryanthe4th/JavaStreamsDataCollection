import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.OperatingSystem;
import io.github.bonigarcia.wdm.managers.PhantomJsDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;

@Test
public class WDManagerBrowsersTest {

    public void openGoogleChrome() {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.selenium.dev/");
        driver.quit();
    }

    public void openFirefox() {

        WebDriverManager.firefoxdriver().setup();

        WebDriver driver = new FirefoxDriver();

        driver.get("https://www.selenium.dev/");
        driver.quit();
    }

//    public void openEdge() {
//
////        WebDriverManager.edgedriver().setup();
//        WebDriverManager.edgedriver().operatingSystem(OperatingSystem.MAC).setup();
//
//        WebDriver driver = new EdgeDriver();
//
//        driver.get("https://www.selenium.dev/");
//        driver.quit();
//    }
//
//    public void openOpera() {
//
////        WebDriverManager.operadriver().setup();
//        WebDriverManager.operadriver().proxy("server:port").setup();
//
//        WebDriver driver = new OperaDriver();
//
//        driver.get("https://www.selenium.dev/");
//        driver.quit();
//    }
//
//    public void openSafari() {
//
//        WebDriverManager.phantomjs().setup();
//
//        WebDriver driver = new SafariDriver();
//
//        driver.get("https://www.selenium.dev/");
//        driver.quit();
//    }
}
