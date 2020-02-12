
package student.adventure;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "startingRoom",
    "endingRoom",
    "rooms"
})
public class Layout {

    private String startingRoom;
    private String endingRoom;
    private List<Room> rooms;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Layout() {
    }

    /**
     * 
     * @param startingRoom
     * @param rooms
     * @param endingRoom
     */
    public Layout(String startingRoom, String endingRoom, List<Room> rooms) {
        this.startingRoom = startingRoom;
        this.endingRoom = endingRoom;
        this.rooms = rooms;
    }

    /**
     *
     * @return starting room
     */
    public String getStartingRoom() {
        return startingRoom;
    }

    /**
     *
     * @param startingRoom room that you want to set.
     */
    public void setStartingRoom(String startingRoom) {
        this.startingRoom = startingRoom;
    }

    /**
     *
     * @return ending room of game
     */
    public String getEndingRoom() {
        return endingRoom;
    }

    /**
     *
     * @param endingRoom room you want ending room to be
     */
    public void setEndingRoom(String endingRoom) {
        this.endingRoom = endingRoom;
    }

    /**
     *
     * @return the entire list of rooms
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     *
     * @param rooms rooms you want to set
     */
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }


    /**
     * This checks if the user's given direction is valid
     * @param userInpDirection user input direction
     * @param roomRightNow the current room, as a String
     * @return a boolean of whether the direction leads to a room
     */
    boolean isGivenRoomValid(String userInpDirection, String roomRightNow) {
        for (int i = 0; i < rooms.size(); i++) {

            if (rooms.get(i).getName().equals(roomRightNow)) {

                for (Direction dirs: rooms.get(i).getDirections()) {

                    String[] strings = userInpDirection.split(" ");
                    if (strings.length <=1) {
                        return false;
                    }
                    if (strings[1].equalsIgnoreCase(dirs.getDirectionName())) {

                        return true;

                    }

                }

            }

        }
        return false;
    }

    /**
     * Helper function to change the room the user is in, based on direction given
     *
     * @param userInpDirection the direction the user elects to go in
     * @param roomRightNow currentRoom the current room
     * @return the new room, as a String
     */
     String changeRoom(String userInpDirection, String roomRightNow) {
        for (int i = 0; i < rooms.size(); i++) {

            if (rooms.get(i).getName().equals(roomRightNow)) {

                for (Direction dirs: rooms.get(i).getDirections()) {

                    String[] strings = userInpDirection.split(" ");
                    if (strings[1].equalsIgnoreCase(dirs.getDirectionName())) {

                        return dirs.getRoom();

                    }

                }

            }

        }
        return null;
    }
}
