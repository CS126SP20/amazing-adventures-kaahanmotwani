package student.adventure;

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

        boolean gameOver = (currentRoomStr.equals(gameLayout.getEndingRoom()));

        //while the current room is not the final room
        while (!gameOver) {

            Room currentRoom = getRoomObj(currentRoomStr, gameLayout.getRooms());

            System.out.println(currentRoom.getDescription());

            String directions = combineDirections(currentRoom.getDirections());

            System.out.println("From here, you can go: " + directions);

            String userAnswer = consoleInput.nextLine();

            if (userAnswer.equals("quit") || userAnswer.equals("exit")) {
                //exit the program
                System.exit(0);
            }

            else {

                //calls on helper function
                boolean isValid = gameLayout.isGivenRoomValid(userAnswer, currentRoomStr);
                String go = "go";
                // if they typed in a valid command
                if (userAnswer.substring(0, go.length()).equalsIgnoreCase("go") && isValid) {

                    userAnswer = userAnswer.toLowerCase();

                    //changes the current room string to where they'd like to go
                    currentRoomStr = gameLayout.changeRoom(userAnswer, currentRoomStr);

                }

                //if they typed in somewhere they can't go
                else if (userAnswer.substring(0, 2).equalsIgnoreCase("go")) {

                    System.out.println("I can't " + userAnswer);

                }

                else {

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
     * @param dirsList A list of possible directions that the user can move to
     * @return String of directions with space between them
     */
    static String combineDirections(List<Direction> dirsList) {

        String returned = "";

        for (int i = 0; i < dirsList.size(); i++) {

            returned += dirsList.get(i).getDirectionName() + " ";

        }

        return returned;
    }

}