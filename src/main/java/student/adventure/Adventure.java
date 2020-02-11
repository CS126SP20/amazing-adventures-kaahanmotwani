package student.adventure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Adventure {

    /**
     * As long as the player has not reached the final room, the game continues
     * @param gameLayout
     */
    public static void continueGame(Layout gameLayout) {

        Scanner consoleInput = new Scanner(System.in);

        String currentRoomStr = gameLayout.getStartingRoom(); //string

        boolean gameOver = (currentRoomStr.equals(gameLayout.getEndingRoom()));

        //while the current room is not the final room
        while (!gameOver) {

            Room currentRoom = getRoomObj(currentRoomStr, gameLayout.getRooms());

            System.out.println(currentRoom.getDescription());

            String directions = combineDirections(currentRoom.getDirections());

            System.out.println("From here, you can go: " + directions);

            String answer = consoleInput.nextLine();

            if (answer.equals("quit") || answer.equals("exit")) {
                //exit the program
                System.exit(0);
            }

            else {

                boolean isValid = gameLayout.getRoomDirections(answer, currentRoomStr);
                String go = "go";
                // if they typed in a valid command
                if (answer.substring(0, go.length()).equalsIgnoreCase("go") && isValid) {

                    answer = answer.toLowerCase();

                    //changes the current room string to where they'd like to go
                    currentRoomStr = gameLayout.getRoomDirectionsStr(answer, currentRoomStr);

                }

                //if they typed in somewhere they can't go
                else if (answer.substring(0, 2).equalsIgnoreCase("go")) {

                    System.out.println("I can't " + answer);

                }

                else {

                    System.out.println("I don't understand '" + answer + "'");

                }

            }

        }

        System.out.println("You've reached the final room, the game is over!");
    }

    /**
     *
     * @param room The current room the user is in (as a string)
     * @param roomObjs The list of rooms
     * @return
     */
    static Room getRoomObj(String room, List<Room> roomObjs) {

        for (int i = 0; i < roomObjs.size(); i++) {

            Room currentRoom = roomObjs.get(i);

            if (currentRoom.getName().equals(room)) {

                return currentRoom;
            }
        }
        return null;
    }

    /**
     *
     * @param dirsList
     * @return
     */
    static String combineDirections(List<Direction> dirsList) {

        String returned = "";

        for (int i = 0; i < dirsList.size(); i++) {

            returned += dirsList.get(i).getDirectionName() + " ";

        }

        return returned;
    }

}

// while current room is not end room
// once you're given the current room string, get the room object
// then print description
// take in user input from scanner
// then determine if input is valid or not
// its valid if input starts with go
// and the direction that they said to go is in the list of possible directions
// if its not valid then
// if it is valid then you find the new room based on the direction that you've been given
// then set that as your current room