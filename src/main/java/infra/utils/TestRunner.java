package utils;

import infra.pages.SearchResultItem;

import java.io.FileWriter;
import java.util.List;

public class TestRunner {


    /**
     * This method prints info the the console log
     * @param content - The content to print
     */

    public static void printToLog(String content) {
        System.out.println(content);

    }


    /**
     * This method receives a list of items and add them to new csv file
     *
     * @param items - List of items to add to the CSV file
     */
    public static void writeToCsvFile(List<SearchResultItem> items) {
        FileWriter csvWriter = null;

        try {
            csvWriter = new FileWriter("items.csv");
            for (SearchResultItem singleItem : items) {
                csvWriter.append(String.join(",", singleItem.toString()));
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (Exception ex) {
            printToLog("scrapSearchResults.writeToCsvFile: " + ex.getMessage());
        }
    }


}
