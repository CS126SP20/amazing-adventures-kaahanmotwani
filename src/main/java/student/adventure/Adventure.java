package student.adventure;

import java.util.List;
import java.util.Scanner;

public class Adventure {
    private List<Room> rooms;
    public Adventure() {
        Scanner consoleInput = new Scanner(System.in);
        Layout gameLayout = new Layout();
        System.out.println(gameLayout.getStartingRoom());
    }

    public static void startGame(Layout thing) {
        System.out.println(thing.getStartingRoom());
    }

}