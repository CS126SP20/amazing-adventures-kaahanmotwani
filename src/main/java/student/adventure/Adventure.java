package student.adventure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Adventure {
    private List<Room> rooms;
    public Adventure() {

    }

    /**
     *
     * @param gameLayout
     */
    public static void startGame(Layout gameLayout) {
        Map<String, String> roomList = new HashMap<>();

        for (Room room: gameLayout.getRooms()) {
            roomList.put(room.getName(), room.getDescription());
        }

        System.out.println(gameLayout.getRooms().get(0).getDescription());
        System.out.println("From here, you can go: " + gameLayout.getRooms().get(0).getDirections().get(0).getDirectionName());

        continueGame(gameLayout, roomList);
    }

    /**
     *
     * @param gameLayout
     */
    public static void continueGame(Layout gameLayout, Map<String, String> roomList) {
        Scanner consoleInput = new Scanner(System.in);
        boolean gameOver = (gameLayout.getStartingRoom().equals(gameLayout.getEndingRoom()));
        while (!gameOver) {

            String answer = consoleInput.nextLine();
            if (answer.equals("quit") || answer.equals("exit")) {
                System.out.println("GAEM OVEAEAER");
                //exit the program
            }
            else {
                System.out.println("From here, you can go: " + gameLayout.getRooms().get(0).getDirections().get(0).getDirectionName());
            }
            //play game
        }
        System.out.println("You've reached the final room, the game is over!");
    }

    /**
     *
     */
    public static void endGame() {

    }
}