package demo;

import org.testng.annotations.Test;
import testbase.TestBase;

import static org.testng.Assert.assertEquals;

public class GoogleSearchPageTest extends TestBase {

    @Test
    public void search() {

        driver.get("https://google.com");

//
//        new GoogleSearchPage().searchGoogle("selenium automation");

//        new GoogleHomePage().getPageTitle();
        new GoogleHomePage().enterTextAndSearch("selenium automation framework");


    }
}
