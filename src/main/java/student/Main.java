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
//        URL url = new URL("https://courses.grainger.illinois.edu/cs126/sp2020/resources/siebel.json");
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
     *
     * @param userInput
     * @return
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
     *
     * @return
     */
    private static Layout parseUrlForJsonFile(String userInputUrl) throws IOException {
        URL url = new URL(userInputUrl);
        Layout layout = new ObjectMapper().readValue(url, Layout.class);
        return layout;
    }

}