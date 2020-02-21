package student.adventure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AdventureTest {

    Layout mapper;
    List<Direction> testingDirections;
    List<Room> testRooms;
    Room room1 = new Room();
    Room room2 = new Room();

    @Before
    public void setUp() {
        try {
            mapper = new ObjectMapper().readValue(new File("src/main/resources/siebel.json"), Layout.class);
        } catch (IOException io) {

        }

        // This is run before every test.
    }

    /**
     * Want this to run before to test my helper functions,
     * which rely on lists of Directions
     */
    public void setTestDirectionList() {
        Direction first = new Direction("up", "first");
        Direction second = new Direction("down", "second");
        testingDirections = new ArrayList<Direction>();
        testingDirections.add(first);
        testingDirections.add(second);
    }

    /**
     *
     */
    public void makeRoomList() {
        setTestDirectionList();
        room1 = new Room("room1", "first", null, testingDirections);
        room2 = new Room("room2", "second", null, testingDirections);
        testRooms = new ArrayList<Room>();
        testRooms.add(room1);
        testRooms.add(room2);
    }

    @Test
    public void testCombineDirectionsHelper() throws Exception {
        setTestDirectionList();
        assertEquals("up down ", Adventure.combineDirections(testingDirections));
    }

    @Test
    public void testCombineDirectionsHelperForNull() throws Exception {
        assertEquals(null, Adventure.combineDirections(null));
    }

    @Test
    public void testCombineDirectionsHelperForEmpty() throws Exception {
        List<Direction> empty = new ArrayList<Direction>();
        assertEquals(null, Adventure.combineDirections(empty));
    }

    @Test
    public void testGetRoomHelperForObject() throws Exception {
        makeRoomList();
        assertEquals(room1, Adventure.getRoomObj("room1", testRooms));
    }

    @Test
    public void testGetRoomHelperForNull() throws Exception {
        makeRoomList();
        assertEquals(null, Adventure.getRoomObj("not a room", testRooms));
    }

    @Test
    public void testIsGivenRoomValidHelper() throws Exception {
        assertEquals(true, mapper.isGivenRoomValid("go west", "SiebelEntry"));
    }

    @Test
    public void testIsGivenRoomInvalidHelper() throws Exception {
        assertEquals(false, mapper.isGivenRoomValid("go west", "blah blah"));
    }

    @Test
    public void testChangeRoomHelperValidRoom() throws Exception {
        assertEquals("SiebelEntry", mapper.changeRoom("go east", "MatthewsStreet"));
    }

    @Test
    public void testChangeRoomHelperInvalidRoom() throws Exception {
        assertEquals(null, mapper.changeRoom("go east", "abcdef"));
    }

}