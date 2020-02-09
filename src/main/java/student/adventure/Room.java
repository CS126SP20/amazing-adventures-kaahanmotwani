
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

    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("items")
    private List<String> items = null;
    @JsonProperty("directions")
    private List<Direction> directions = null;

    /**
     * No args constructor for use in serialization
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

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Room withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Room withDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("items")
    public List<String> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<String> items) {
        this.items = items;
    }

    public Room withItems(List<String> items) {
        this.items = items;
        return this;
    }

    @JsonProperty("directions")
    public List<Direction> getDirections() {
        return directions;
    }

    @JsonProperty("directions")
    public void setDirections(List<Direction> directions) {
        this.directions = directions;
    }

    public Room withDirections(List<Direction> directions) {
        this.directions = directions;
        return this;
    }

}
