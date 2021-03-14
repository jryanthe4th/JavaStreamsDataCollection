import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import testbase.TestBase;

import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.*;

public class JavaStreamsExample_Pagination extends TestBase {

    @Test
    public void test() {
        driver.get("https://www.nfl.com/stats/player-stats");
        String pageTitle = driver.getTitle();
        System.out.println("Page Title is: " + pageTitle);
        assertEquals(pageTitle, "NFL 2020 passing stats - Players | NFL.com");

        // 'Pass Yds' column xpath
        // List<WebElement> elementsList = driver.findElements(By.xpath("//table/tbody/tr/td[2]"));

        // 'Player' column xpath
        // List<WebElement> elementsList = driver.findElements(By.xpath("//table/tbody/tr/td[1]/div/div/a"));

        // Capture all web elements into a list

        List<WebElement> elementsList = driver.findElements(By.xpath("//table/tbody/tr/td[1]/div/div/a"));

        // Capture text of all web elements into new (original) list
        List<String> passYards = elementsList.stream().map(s -> s.getText()).collect(Collectors.toList());

        // Sort on the original list of step 3 -> sorted lists
        List<String> passYardsSorted = passYards.stream().sorted().collect(Collectors.toList());

        System.out.println("passYards: " + passYards);
        System.out.println("passYardSorted: " + passYardsSorted);

        // Compare original list vs sorted list
        assertNotEquals(passYards, passYardsSorted);

        List<String> price;
        // Scan the name column with getText -> Rice -> print the price of the Rice
        // Tab through multiple pages to find the product
        do {
            // Each page, grab new data in the table, or we'll hit 'state element' error
            List<WebElement> rows = driver.findElements(By.xpath("xpath goes here"));
            price = rows.stream()
                    .filter(s -> s.getText().contains("Rice"))
                    .map(s -> getPriceVeggie(s)).collect(Collectors.toList());

            price.forEach(a -> System.out.println(a));
            if (price.size() < 1) {
                // If multiple pages of products to search through, this will tab through each page until no more prices are found
                driver.findElement(By.cssSelector("[aria-label='Next']")).click();
            }
        } while (price.size() < 1);
    }

//    @Test
//    public void captureAllWebElementsIntoList() {
//
//        WebDriver driver = new ChromeDriver();
//        driver.get("www.nfl.com/stats/player-stats");
//
//        // Click on column
//        driver.findElement(By.xpath("xpath goes here")).click();
//
//        // Capture all web elements into a list
//        List<WebElement> elementsList = driver.findElements(By.xpath("xpath goes here"));
//
//        // Capture text of all web elements into new (original) list
//        List<String> originalList = elementsList.stream().map(s -> s.getText()).collect(Collectors.toList());
//
//        // Sort on the original list of step 3 -> sorted lists
//        List<String> sortedList = originalList.stream().sorted().collect(Collectors.toList());
//
//        // Compare original list vs sorted list
//        assertEquals(sortedList, originalList);
//
//        List<String> price;
//        // Scan the name column with getText -> Rice -> print the price of the Rice
//        // Tab through multiple pages to find the product
//        do {
//            // Each page, grab new data in the table, or we'll hit 'state element' error
//            List<WebElement> rows = driver.findElements(By.xpath("xpath goes here"));
//            price = rows.stream().filter(s -> s.getText().contains("Rice"))
//                    .map(s -> getPriceVeggie(s)).collect(Collectors.toList());
//
//            price.forEach(a -> System.out.println(a));
//            if (price.size() < 1) {
//                // If multiple pages of products to search through, this will tab through each page until no more prices are found
//                driver.findElement(By.cssSelector("[aria-label='Next']")).click();
//            }
//        } while (price.size() < 1);
//
//    }

    private String getPriceVeggie(WebElement s) {
        s.findElement(By.xpath("following-sibling::td[1]")).getText();
        return null;
    }
}
