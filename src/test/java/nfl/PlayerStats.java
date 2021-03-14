package nfl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import testbase.TestBase;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.testng.Assert.*;

public class PlayerStats extends TestBase {

    public PlayerStats() throws IOException {};

    @Test
    public void getPlayerNames() throws IOException {
        driver.get("https://www.nfl.com/stats/player-stats");
        String pageTitle = driver.getTitle();
        System.out.println("Page Title is: " + pageTitle);
        assertEquals(pageTitle, "NFL 2020 passing stats - Players | NFL.com");

        /*
        // 'Pass Yds' column xpath
        List<WebElement> elementsList = driver.findElements(By.xpath("//table/tbody/tr/td[2]"));
        // 'Player' column xpath
        List<WebElement> elementsList = driver.findElements(By.xpath("//table/tbody/tr/td[1]/div/div/a"));
        */

        List<String> player;
        ArrayList<String> playerStats = new ArrayList<>();
        ArrayList<List<String>> playerNames = new ArrayList<>();

        // Scan the table column with getText -> print the text
        // Tab through multiple pages to find all the xpath text
        do {
            // Each page, grab new data in the table, or we'll hit 'state element' error
            List<WebElement> playerRows = driver.findElements(By.xpath("//table/tbody/tr/td[1]/div/div/a"));

            player = playerRows.stream()
                    .map(s -> s.getText())
                    .collect(Collectors.toList());



            playerNames.add(player);
            //player.forEach(a -> System.out.println(a));
            if (player.size() == 25) {
                // If multiple pages of players to search through, this will tab through each page until a less that full page is found
                driver.findElement(By.className("nfl-o-table-pagination__next")).click();
            }
        } while (player.size() == 25);

        List<String> allPlayers = playerNames.stream()
                .flatMap(s -> s.stream())
                .collect(Collectors.toList());

        System.out.println(playerNames);
        System.out.println(allPlayers);
        writeCSVFile(allPlayers);

    }

    private String getPlayerStats(WebElement s) {
        s.findElement(By.xpath("//table/tbody/tr/td[1]/div/div/a/ancestor::td/following-sibling::td")).getText();
        System.out.println(s);
        return null;
    }

    public void writeCSVFile(List<String> playerNames) throws IOException {
        String NEW_LINE_SEPARATOR = "\n";
        String CSV_File_Path = "./target/csv/AllPlayers.csv";
        // Write the file
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(CSV_File_Path));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                .withRecordSeparator(NEW_LINE_SEPARATOR));
                //.withHeader("Player Name"));

        // Push the values into the file
        // csvPrinter.printRecord("TEST");
        csvPrinter.printRecords(playerNames);
        csvPrinter.flush();
            csvPrinter.close();
    }
}
