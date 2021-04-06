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

//    public PlayerStats() throws IOException {};

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
        List<String> stats;
        List<String> data;
        ArrayList<List<String>> playerStats = new ArrayList<>();
        ArrayList<List<String>> playerNames = new ArrayList<>();
        ArrayList<List<String>> playerData = new ArrayList<>();

        // Scan the table column with getText -> print the text
        // Tab through multiple pages to find all the xpath text
        do {
            // Each page, grab new data in the table, or we'll hit 'state element' error
            List<WebElement> playerNameRows = driver.findElements(By.xpath("//table/tbody/tr/td[1]/div/div/a"));
            List<WebElement> playerStatRows = driver.findElements(By.xpath("//table/tbody/tr/td[1]/div/div/a/ancestor::td/following-sibling::td"));

//            List<WebElement> playerRows = driver.findElements(By.xpath("//table/tbody/tr"));
            List<WebElement> playerRows = driver.findElements(By.xpath("//table/tbody/tr"));

            //data = playerRows.stream().map(s -> s.getText()).collect(Collectors.toList());
            // This gets all player data for one row per page
//            data = playerRows.stream()
//                    .map(row -> row.findElements(By.xpath("//table/tbody/tr[1]/td"))
//                        .stream()
//                        .map(td -> td.getText())
//                        .collect(Collectors.toList()))
//                    .collect(Collectors.toList());

            data = playerRows.stream()
                    .map(row -> row.getText().toString().replace(" ", ","))
                    .collect(Collectors.toList());
//            data = playerRows.stream()
//                    .toString().toCharArray();

                    //.map(row -> row.getText().toString().replace(" ", ","))
                    //.collect(Collectors.toList());

//            data = playerRows.stream()
//                    .map(row -> row.findElements(By.xpath("//table/tbody/tr[1]/td"))
//                            .stream()
//                            .map(s -> getPlayerStats(s))
//                            .collect(Collectors.toList()))
//                    .collect(Collectors.toList());

//            data = playerRows.stream()
//                    .map(row -> getPlayerStats(row))
//                    .collect(Collectors.toList());


            // Player Names
            player = playerNameRows.stream()
                    .map(s -> s.getText())
                    .collect(Collectors.toList());


            // Player Stats
//            stats = playerStatRows.stream()
//                    .map(s -> s.getText())
//                    .collect(Collectors.toList());

            playerNames.add(player);
            playerData.add(data);
//            playerStats.add(stats);
            //player.forEach(a -> System.out.println(a));
            if (player.size() == 25) {
                // If multiple pages of players to search through, this will tab through each page until a less that full page is found
                driver.findElement(By.className("nfl-o-table-pagination__next")).click();
            }
        } while (player.size() == 25);

        List<String> allPlayers = playerNames.stream()
                .flatMap(s -> s.stream())
                .collect(Collectors.toList());

        List<String> allData = playerData.stream()
                .flatMap(s -> s.stream())
                .collect(Collectors.toList());

//        List<String[]> arr = allData.stream().map(playerRow -> playerRow.split(" ")).collect(Collectors.toList());
        String[] allDataString = allData.stream().toArray(String[]::new);
        String cleanData = Arrays.toString(allDataString);
        System.out.println("cleanData : " + Arrays.toString(cleanData.split(",")));

//        System.out.println(playerNames);
//        System.out.println(allPlayers);
//        System.out.println(playerStats);
//        System.out.println(playerData);
//        System.out.println(allData);

        //writeCSVFile(allPlayers, "AllPlayers", "\n");
        writeCSVFile(allDataString, "AllData", "\n");

    }

//    private String getPlayerStats(WebElement td) {
//        td.findElement(By.xpath("//child::td")).getText();
//        System.out.println(td);
//        return null;
//    }

//    private String getPlayerStats(WebElement s) {
//        s.findElement(By.xpath("//table/tbody/tr/td[1]/div/div/a/ancestor::td/following-sibling::td")).getText();
//        System.out.println(s);
//        return null;
//    }

    private void writeCSVFile(String[] data, String filename, String recordSeparator) throws IOException {
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
