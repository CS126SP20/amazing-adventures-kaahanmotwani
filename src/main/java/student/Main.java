package student;

import com.fasterxml.jackson.databind.ObjectMapper;
import student.adventure.Adventure;
import student.adventure.Layout;

import java.io.File;
import java.io.IOException;
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
//        Layout layout = new ObjectMapper().readValue(url, Layout.class);
        File file;
        if (args == null || args.length == 0) {
            file = new File("src/main/resources/siebel.json");
        } else {
            file = new File(args[0]);
            if (!(file).exists()) {
                System.out.println("This file does not exist, sorry!");
            }
        }
        Layout layout = new ObjectMapper().readValue(file, Layout.class);
        Adventure.continueGame(layout);
        //TODO check if JSON files have null rooms, directions etc
    }
}