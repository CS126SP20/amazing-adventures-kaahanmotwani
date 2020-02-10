
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

    public Layout withStartingRoom(String startingRoom) {
        this.startingRoom = startingRoom;
        return this;
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

    public Layout withEndingRoom(String endingRoom) {
        this.endingRoom = endingRoom;
        return this;
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

    public Layout withRooms(List<Room> rooms) {
        this.rooms = rooms;
        return this;
    }

}
