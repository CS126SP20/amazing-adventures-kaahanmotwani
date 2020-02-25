package student.adventure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.contrib.java.lang.system.Assertion;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;


public class AdventureTest {

    Layout mapper;
    List<Direction> testingDirections;
    List<Room> testRooms;
    Room room1 = new Room();
    Room room2 = new Room();

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    /**
     * Creates an object mapper to the Layout class, allowing tests to call methods from the class
     */
    @Before
    public void setUp() {
        try {
            mapper = new ObjectMapper().readValue(new File("src/main/resources/siebel.json"), Layout.class);
        } catch (IOException io) {

        }

        // This is run before every test.
    }

    /**
     * Creates a list of directions and populates it for tests that need a mock list of directions
     */
    public void setTestDirectionList() {
        Direction first = new Direction("up", "first");
        Direction second = new Direction("down", "second");
        testingDirections = new ArrayList<Direction>();
        testingDirections.add(first);
        testingDirections.add(second);
    }

    /**
     * Creates a list of rooms and populates it for tests that need a mock list of rooms
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
    public void testQuitOrExit() throws Exception {
        Scanner consoleInput = new Scanner(System.in);
        String userAnswer = consoleInput.nextLine();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userAnswer.getBytes());
        //TODO: Use expected system exit to add testing for that
//        assertEquals();
    }

    @Test
    public void testCombineDirections() throws Exception {
        setTestDirectionList();
        assertEquals("up down ", Adventure.combineDirections(testingDirections));
    }

    @Test
    public void testCombineDirectionsForNull() throws Exception {
        assertEquals(null, Adventure.combineDirections(null));
    }

    @Test
    public void testCombineDirectionsForEmpty() throws Exception {
        List<Direction> empty = new ArrayList<Direction>();
        assertEquals(null, Adventure.combineDirections(empty));
    }

    @Test
    public void testGetRoomForObject() throws Exception {
        makeRoomList();
        assertEquals(room1, Adventure.getRoomObj("room1", testRooms));
    }

    @Test
    public void testGetRoomForNull() throws Exception {
        makeRoomList();
        assertEquals(null, Adventure.getRoomObj("not a room", testRooms));
    }

    @Test
    public void testIsGivenRoomDirectionValid() throws Exception {
        assertEquals(true, mapper.isGivenRoomValid("go west", "SiebelEntry"));
    }

    @Test
    public void testIsGivenRoomValidForWrongDirection() throws Exception {
        assertEquals(false, mapper.isGivenRoomValid("go south", "SiebelEntry"));
    }

    @Test
    public void testIsGivenRoomInvalid() throws Exception {
        assertEquals(false, mapper.isGivenRoomValid("go west", "blah blah"));
    }

    @Test
    public void testChangeRoomForValidRoom() throws Exception {
        assertEquals("SiebelEntry", mapper.changeRoom("go east",
                "MatthewsStreet"));
    }

    @Test
    public void testChangeRoomForInvalidRoom() throws Exception {
        assertEquals(null, mapper.changeRoom("go east", "abcdef"));
    }

//    @Test
//    public void test
}