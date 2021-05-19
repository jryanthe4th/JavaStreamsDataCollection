package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertEquals;

public class GoogleHomePage {

    WebDriver driver;

    public void getPageTitle() {
        String pageTitle = driver.getTitle().trim();
        System.out.println("Page Title is: " + pageTitle);
        assertEquals(pageTitle, "Google");
    }

    public void enterTextAndSearch(String userQuestion) {
        driver.findElement(By.name("q")).sendKeys(userQuestion);
        driver.findElement(By.className("CcAdNb")).click();
        new GoogleHomePage();
    }

    public static class GoogleSearchAction {

        public void search(final String userSearch) {
            
        }
    }
}
