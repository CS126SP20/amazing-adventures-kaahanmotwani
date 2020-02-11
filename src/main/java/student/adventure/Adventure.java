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

    }

    public static Room getRoomObj(String room, List<Room> roomObj) {

        for (int i = 0; i < roomObj.size(); i++) {

            Room currentRoom = roomObj.get(i);

            if (currentRoom.getName().equals(room)) {

                return currentRoom;
            }
        }
        return null;
    }

    public static String combineDirections(List<Direction> dirsList) {

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