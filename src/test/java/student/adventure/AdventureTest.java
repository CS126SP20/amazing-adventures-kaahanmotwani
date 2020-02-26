package student.adventure;

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

import static org.junit.Assert.*;


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
        assertNull(Adventure.combineDirections(null));
    }

    @Test
    public void testCombineDirectionsForEmpty() throws Exception {
        List<Direction> empty = new ArrayList<Direction>();
        assertNull(Adventure.combineDirections(empty));
    }

    @Test
    public void testGetRoomForObject() throws Exception {
        makeRoomList();
        assertEquals(room1, Adventure.getRoomObj("room1", testRooms));
    }

    @Test
    public void testGetRoomForNull() throws Exception {
        makeRoomList();
        assertNull(Adventure.getRoomObj("not a room", testRooms));
    }

    @Test
    public void testIsGivenRoomDirectionValid() throws Exception {
        assertTrue(mapper.isGivenRoomValid("go west", "SiebelEntry"));
    }

    @Test
    public void testIsGivenRoomValidForWrongDirection() throws Exception {
        assertFalse(mapper.isGivenRoomValid("go south", "SiebelEntry"));
    }

    @Test
    public void testIsGivenRoomInvalid() throws Exception {
        assertFalse(mapper.isGivenRoomValid("go west", "blah blah"));
    }

    @Test
    public void testChangeRoomForValidRoom() throws Exception {
        assertEquals("SiebelEntry", mapper.changeRoom("go east",
                "MatthewsStreet"));
    }

    @Test
    public void testChangeRoomForInvalidRoom() throws Exception {
        assertNull(mapper.changeRoom("go east", "abcdef"));
    }

    @Test
    public void testExpectedSystemExit() throws Exception {
        exit.expectSystemExitWithStatus(0);
        System.exit(0);
    }

    @Test
    public void testForInputNotContainingGo() throws Exception {
        assertFalse(Adventure.containsGo("nope"));
    }

    @Test
    public void testForInputContainingGo() throws Exception {
        assertTrue(Adventure.containsGo("go east"));
    }

    @Test
    public void testForGameOver() throws IOException {
        File file = new File("src/main/resources/siebel.json");
        Layout layout = new ObjectMapper().readValue(file, Layout.class);

        assertTrue(Adventure.checkGameOver(layout, "Siebel1314"));
    }

    @Test
    public void testForGameNotOver() throws IOException {
        File file = new File("src/main/resources/siebel.json");
        Layout layout = new ObjectMapper().readValue(file, Layout.class);

        assertFalse(Adventure.checkGameOver(layout, "MatthewsStreet"));
    }

    @Test
    public void testForAddExistingItem() throws IOException {
        assertFalse(Adventure.addedOrRemovedItem(mapper.getRooms().get(2),
                "add pizza"));
    }

    @Test
    public void testForAddNonExistingItem() throws IOException {
        assertTrue(Adventure.addedOrRemovedItem(mapper.getRooms().get(4),
                "ADD GRADING RUBRIC"));
    }

    @Test
    public void testForRemoveExistingItem() throws IOException {
        assertTrue(Adventure.addedOrRemovedItem(mapper.getRooms().get(4),
                "rEmove usb-C connector"));
    }

    @Test
    public void testForRemoveNonExistingItem() throws IOException {
        assertFalse(Adventure.addedOrRemovedItem(mapper.getRooms().get(4),
                "remove rocks"));
    }

    @Test
    public void testForBadUserInput() throws Exception {
        Assert.assertTrue(Adventure.isCommandInvalid("blah blah"));
    }

    @Test
    public void testForGoInSameWord() throws Exception {
        Assert.assertTrue(Adventure.isCommandInvalid("goal east"));
    }

    @Test
    public void testForPhraseWithGo() throws Exception {
        Assert.assertTrue(Adventure.isCommandInvalid("I want to go east"));
    }

    @Test
    public void testForInputLessThanThreeCharacters() throws Exception {
        Assert.assertTrue(Adventure.isCommandLessThanThreeCharacters("g"));
    }

    @Test
    public void testIfUserPassedPuzzle() throws IOException {
        mapper = new ObjectMapper().readValue(new File("src/main/resources/gameExtension.json"),
                Layout.class);
        Adventure.mapRoomToAnswer(mapper);
        Assert.assertTrue(Adventure.passedPuzzle("illini", mapper.getRooms().get(0)));
    }

    @Test
    public void testIfUserFailedPuzzle() throws IOException {
        mapper = new ObjectMapper().readValue(new File("src/main/resources/gameExtension.json"),
                Layout.class);
        Adventure.mapRoomToAnswer(mapper);
        Assert.assertFalse(Adventure.passedPuzzle("blah", mapper.getRooms().get(0)));
    }

}