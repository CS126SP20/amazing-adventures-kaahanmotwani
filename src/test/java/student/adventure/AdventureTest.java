package student.adventure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AdventureTest {

    Layout mapper;
    List<Direction> testingDirs;

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
    @Before
    public  void setTestDirList() {
        Direction first = new Direction("up", "first");
        Direction second = new Direction("down", "second");
        testingDirs = new ArrayList<Direction>();
        testingDirs.add(first);
        testingDirs.add(second);
    }

    @Test
    public void testCombineDirsHelper() throws Exception {
        assertEquals("up down ", Adventure.combineDirections(testingDirs));
    }

    @Test
    public void testGetRoomObjHelper() throws Exception {
        Room room1 = new Room("room1", "first", null, testingDirs);
        Room room2 = new Room("room2", "second", null, testingDirs);
        List<Room> testRooms = new ArrayList<Room>();
        testRooms.add(room1);
        testRooms.add(room2);
        assertEquals(room1, Adventure.getRoomObj("room1", testRooms));
    }

    @Test
    public void testIsGivenRoomValidHelper() throws Exception {
        assertEquals(true, mapper.isGivenRoomValid("go east", "MatthewsStreet"));
    }

    @Test
    public void testChangeRoomHelper() throws Exception {
        assertEquals("SiebelEntry", mapper.changeRoom("go east", "MatthewsStreet"));
    }
}