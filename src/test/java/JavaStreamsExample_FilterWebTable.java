import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

public class JavaStreamsExample_FilterWebTable {

    @Test
    public void filterWebTable() {

        WebDriver driver = new ChromeDriver();
        driver.get(""); // TODO Find URL to use
        driver.findElement(By.id("search-field")).sendKeys("Rice");
        driver.findElement(By.xpath("'//tr/td[1]'"));
        List<WebElement> veggies = driver.findElements(By.xpath("'//tr/td[1]'")); // 1 result in test

        List<WebElement> filteredList = veggies.stream().filter(veggie -> veggie.getText().contains("Rice"))
                .collect(Collectors.toList()); // 1 result in test

        // If both sizes are not equal, test will fail
        assertEquals(veggies.size(), filteredList.size());
    }
}
