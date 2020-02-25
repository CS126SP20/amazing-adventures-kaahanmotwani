package student.adventure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
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

    /**
     * Derived from: https://stackoverflow.com/questions/15990433/using-junit-on-code-that-terminates
     */
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    /**
     * Before every test, creates an object mapper to the Layout class, allowing tests to call methods from the class;
     */
    @Before
    public void setUp() {
        try {
            mapper = new ObjectMapper().readValue(new File("src/main/resources/siebel.json"), Layout.class);
        } catch (IOException io) {

        }
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

    /**
     * Converts a given string input into a ByteArrayInputStream
     * @param input The String input
     * @return A ByteArrayInputStream of the given String
     */
    public static ByteArrayInputStream buildInputStream(String input) {
        return new ByteArrayInputStream(input.getBytes());
    }

    @Test
    public void testQuit() throws Exception {
        final ByteArrayInputStream inputStream = buildInputStream("quit");
        exit.expectSystemExitWithStatus(0);
        System.exit(0);
    }

    @Test
    public void testExit() throws Exception {
        final ByteArrayInputStream inputStream = buildInputStream("exit");
        exit.expectSystemExitWithStatus(0);
        System.exit(0);
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

    @Test
    public void testExpectedSystemExit() throws Exception {
        exit.expectSystemExitWithStatus(0);
        System.exit(0);
    }

    @Test
    public void testForInputNotContainingGo() throws Exception {
        assertEquals(false, Adventure.containsGo("nope"));
    }

    @Test
    public void testForInputContainingGo() throws Exception {
        assertEquals(true, Adventure.containsGo("go east"));
    }

    @Test
    public void testForGameOver() throws IOException {
        File file = new File("src/main/resources/siebel.json");
        Layout layout = new ObjectMapper().readValue(file, Layout.class);

        assertEquals(true, Adventure.checkGameOver(layout, "Siebel1314"));
    }

    @Test
    public void testForGameNotOver() throws IOException {
        File file = new File("src/main/resources/siebel.json");
        Layout layout = new ObjectMapper().readValue(file, Layout.class);

        assertEquals(false, Adventure.checkGameOver(layout, "MatthewsStreet"));
    }

    @Test
    public void testForCharactersBeforeGo() {
        final ByteArrayInputStream inputStream = buildInputStream("blah blah");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        Assert.assertThat(outputStream, CoreMatchers.containsString("I don't understand blah blah"));
    }
}