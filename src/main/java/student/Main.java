package student;

import com.fasterxml.jackson.databind.ObjectMapper;
import student.adventure.Adventure;
import student.adventure.Layout;

import java.io.File;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.Scanner;

public class Main {
    /**
     * Decides what to do with user input into command line.
     * @param args User input into command line.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if (args == null || args.length == 0) {
            File file = new File("src/main/resources/siebel.json");
            Layout layout = new ObjectMapper().readValue(file, Layout.class);
            Adventure.startGame(layout);
        } else {
            File file = new File(args[0]);
            Layout layout = new ObjectMapper().readValue(file, Layout.class);
            Adventure.startGame(layout);
        }
    }
}