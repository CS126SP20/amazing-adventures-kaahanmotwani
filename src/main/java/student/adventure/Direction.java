
package student.adventure;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "directionName",
    "room"
})
public class Direction {

    private String directionName;

    private String room;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Direction() {
    }

    public Direction(String directionName, String room) {
        super();
        this.directionName = directionName;
        this.room = room;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public Direction withDirectionName(String directionName) {
        this.directionName = directionName;
        return this;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Direction withRoom(String room) {
        this.room = room;
        return this;
    }

}
