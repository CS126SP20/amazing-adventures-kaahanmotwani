package student.adventure;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Scanner;

public class Adventure {

    /**
     * As long as the player has not reached the final room, the game continues
     * This function then handles user input to change rooms if the user enters a valid input
     * Relies on helper functions to make this happen
     *
     * @param gameLayout A Layout object that contains the list of the rooms, starting & ending room
     *                   Used those objects to create functionality
     */
    public static void continueGame(Layout gameLayout) {
        Scanner consoleInput = new Scanner(System.in);
        String currentRoomStr = gameLayout.getStartingRoom(); //string
        boolean isGameOver = (currentRoomStr.equals(gameLayout.getEndingRoom()));

        //while the current room is not the final room
        while (!isGameOver) {
            Room currentRoom = getRoomObj(currentRoomStr, gameLayout.getRooms());
            System.out.println(currentRoom.getDescription());
            String directions = combineDirections(currentRoom.getDirections());
            System.out.println("From here, you can go: " + directions);
            String userAnswer = consoleInput.nextLine();
            if (userAnswer.equals("quit") || userAnswer.equals("exit")) {
                //exits the program
                System.exit(0);
            } else {

                //calls on helper function to check if the room that they want to go to is valid
                boolean isValid = gameLayout.isGivenRoomValid(userAnswer, currentRoomStr);
                String go = "go";
                // if they typed in a valid command
                if (isValid && userAnswer.substring(0, go.length()).equalsIgnoreCase("go")) {
                    userAnswer = userAnswer.toLowerCase();
                    //changes the current room string to where they'd like to go
                    currentRoomStr = gameLayout.changeRoom(userAnswer, currentRoomStr);
                    isGameOver = checkGameOver(gameLayout, currentRoomStr);
                } else if (userAnswer.substring(0, go.length() + 1).equalsIgnoreCase("go ")) {
                    //if they typed in somewhere they can't go
                    System.out.println("I can't " + userAnswer);
                } else {
                    System.out.println("I don't understand '" + userAnswer + "'");
                }
            }
        }
        // the current room is the final room, and the game is over
        System.out.println("You've reached the final room, the game is over!");
    }

    /**
     * This function takes a string of a room object, returns the corresponding room object
     * Used to change the room object when user moves
     *
     * @param room The current room the user is in (as a string object)
     * @param roomObjs The list of all rooms
     * @return Corresponding (to String) room object
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
     * Takes directions list and outputs a String of all to displau to console
     *
     * @param directionsList A list of possible directions that the user can move to
     * @return String of directions with space between them
     */
    static String combineDirections(List<Direction> directionsList) {
        if (directionsList == null || directionsList.size() == 0) {
            return null;
        }

        StringBuilder returned = new StringBuilder("");
        for (Direction direction : directionsList) {
            returned.append(direction.getDirectionName() + " ");
        }

        return returned.toString();
    }

    /**
     * Checks if the game is over by checking if the current room is the ending room (used everytime the user goes
     * somewhere valid)
     * @param gameLayout the game Layout being played on
     * @param currentRoomString the current room, as a string
     * @return whether the game is over or not
     */
    static boolean checkGameOver(Layout gameLayout, String currentRoomString) {
        if (currentRoomString.equals(gameLayout.getEndingRoom())) {
            return true;
        }
        return false;
    }

}