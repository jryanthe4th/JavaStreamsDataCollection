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

        List<String> data;
        ArrayList<List<String>> playerData = new ArrayList<>();

        // Scan the table column with getText -> print the text
        // Tab through multiple pages to find all the xpath text
        do {
            // Each page, grab new data in the table, or we'll hit 'state element' error
            List<WebElement> playerNameRows = driver.findElements(By.xpath("//table/tbody/tr/td[1]/div/div/a"));
            List<WebElement> playerStatRows = driver.findElements(By.xpath("//table/tbody/tr/td[1]/div/div/a/ancestor::td/following-sibling::td"));

            List<WebElement> playerRows = driver.findElements(By.xpath("//table/tbody/tr"));

            data = playerRows.stream()
                    .map(row -> row.getText().toString().replace(" ", ","))
                    .collect(Collectors.toList());

            playerData.add(data);

            if (data.size() == 25) {
                // If multiple pages of players to search through, this will tab through each page until a less that full page is found
                driver.findElement(By.className("nfl-o-table-pagination__next")).click();
            }
        } while (data.size() == 25);

        List<String> allData = playerData.stream()
                .flatMap(s -> s.stream())
                .collect(Collectors.toList());

        System.out.println(allData);

        writeCSVFile(allData, "AllData", "\n");

    }

    private void writeCSVFile(List<String> data, String filename, String recordSeparator) throws IOException {
        //String NEW_LINE_SEPARATOR = lineSeparator;
        String CSV_File_Path = "./target/csv/" + filename + ".csv";
        // Write the file
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(CSV_File_Path));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                .withRecordSeparator(recordSeparator)
                .withDelimiter(' '));
                //.withHeader("Player Name"));

        // Push the values into the file
        // csvPrinter.printRecord("TEST");
        csvPrinter.printRecords(data);
        csvPrinter.flush();
            csvPrinter.close();
    }
}
