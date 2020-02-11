
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
     * @return
     */
    public String getStartingRoom() {
        return startingRoom;
    }

    /**
     *
     * @param startingRoom
     */
    public void setStartingRoom(String startingRoom) {
        this.startingRoom = startingRoom;
    }

    /**
     *
     * @return
     */
    public String getEndingRoom() {
        return endingRoom;
    }

    /**
     *
     * @param endingRoom
     */
    public void setEndingRoom(String endingRoom) {
        this.endingRoom = endingRoom;
    }

    /**
     *
     * @return
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     *
     * @param rooms
     */
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }


    /**
     *
     * @param input
     * @param roomRightNow currentRoom
     * @return
     */
    boolean getRoomDirections(String input, String roomRightNow) {
        for (int i = 0; i < rooms.size(); i++) {

            if (rooms.get(i).getName().equals(roomRightNow)) {

                for (Direction dirs: rooms.get(i).getDirections()) {

                    String[] strings = input.split(" ");
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
     *
     * @param input
     * @param roomRightNow currentRoom
     * @return
     */
    public String getRoomDirectionsStr(String input, String roomRightNow) {
        for (int i = 0; i < rooms.size(); i++) {

            if (rooms.get(i).getName().equals(roomRightNow)) {

                for (Direction dirs: rooms.get(i).getDirections()) {

                    String[] strings = input.split(" ");
                    if (strings[1].equalsIgnoreCase(dirs.getDirectionName())) {

                        return dirs.getRoom();

                    }

                }

            }

        }
        return null;
    }
}
