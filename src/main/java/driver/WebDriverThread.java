package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverThread {

    public void getDriver() {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
    }
}
