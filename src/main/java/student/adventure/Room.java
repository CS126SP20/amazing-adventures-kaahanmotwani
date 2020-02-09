
package student.adventure;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "description",
    "items",
    "directions"
})
public class Room {

    //
    private String name;

    //
    private String description;

    //
    private List<String> items = null;

    //
    private List<Direction> directions = null;

    /**
     * Empty constructor
     * 
     */
    public Room() {
    }

    /**
     * 
     * @param directions
     * @param name
     * @param description
     * @param items
     */
    public Room(String name, String description, List<String> items, List<Direction> directions) {
        super();
        this.name = name;
        this.description = description;
        this.items = items;
        this.directions = directions;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param name
     * @return
     */
    public Room withName(String name) {
        this.name = name;
        return this;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @param description
     * @return
     */
    public Room withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     *
     * @return
     */
    public List<String> getItems() {
        return items;
    }

    /**
     *
     * @param items
     */
    public void setItems(List<String> items) {
        this.items = items;
    }

    /**
     *
     * @param items
     * @return
     */
    public Room withItems(List<String> items) {
        this.items = items;
        return this;
    }

    /**
     *
     * @return
     */
    public List<Direction> getDirections() {
        return directions;
    }

    /**
     *
     * @param directions
     */
    public void setDirections(List<Direction> directions) {
        this.directions = directions;
    }

    /**
     *
     * @param directions
     * @return
     */
    public Room withDirections(List<Direction> directions) {
        this.directions = directions;
        return this;
    }

}
