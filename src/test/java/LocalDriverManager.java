import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;

public class LocalDriverManager {

    // to make this work just pass the browser name, that must match from the DriverManagerType class
    public WebDriver createInstance(String browser) {
        WebDriver driver = null;

        try {
            DriverManagerType driverManagerType = DriverManagerType.valueOf(browser.toUpperCase());
            Class<?> driverClass = Class.forName(driverManagerType.browserClass());
            WebDriverManager.getInstance(driverManagerType).setup();
            driver = (WebDriver) driverClass.newInstance();
        } catch (IllegalAccessException | ClassNotFoundException e) {
            // exception or log for class not found
        } catch (InstantiationException e) {
            // exception of log for instantiation problem
        }
        return driver;
    }

}
