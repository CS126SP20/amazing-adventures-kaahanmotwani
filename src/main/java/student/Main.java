package student;

import com.fasterxml.jackson.databind.ObjectMapper;
import student.adventure.Adventure;
import student.adventure.Layout;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.server.ExportException;
import java.util.Scanner;

public class Main {
    /**
     * Decides what to do with user input into command line.
     * @param args User input into command line.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Layout layout = new Layout();
        File file;
        if (args == null || args.length == 0) {
            file = new File("src/main/resources/siebel.json");
        } else {
            file = new File(args[0]);
            if (isValidUrl(args[0])) {
                layout = parseUrlForJsonFile(args[0]);
            } else if (!(file).exists()) {
                System.out.println("This file does not exist, sorry!");
            } else {
                layout = new ObjectMapper().readValue(file, Layout.class);
            }
        }

        Adventure.continueGame(layout);
    }

    /**
     * Checks if given the url is valid by using an HttpURLConnection.
     * Used https://www.baeldung.com/java-check-url-exists#using-a-head-request as a resource
     * @param userInput the user input url
     * @return whether it's a valid url or not
     * @throws IOException
     */
    private static boolean isValidUrl(String userInput) throws IOException {
        URL url;

        try {
            url = new URL(userInput);
        } catch (MalformedURLException e) {
            return false;
        }

        HttpURLConnection huc = (HttpURLConnection) url.openConnection();

        return huc.getResponseCode() == HttpURLConnection.HTTP_OK;
    }

    /**
     * Creates an object mapper object based on the url being loaded
     * @return a Layout object that can be used to start the game
     */
    private static Layout parseUrlForJsonFile(String inputUrl) throws IOException {
        URL url = new URL(inputUrl);
        Layout layout = new ObjectMapper().readValue(url, Layout.class);
        return layout;
    }

}