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

    @Before
    public void setUp() {
        try {
            mapper = new ObjectMapper().readValue(new File("src/main/resources/siebel.json"), Layout.class);
        } catch (IOException io) {

        }

        // This is run before every test.
    }

    @Test
    public void testCombineDirsHelper() throws Exception {
        Direction first = new Direction("up", "first");
        Direction second = new Direction("down", "second");
        List<Direction> testingDirs = new ArrayList<Direction>();
        testingDirs.add(first);
        testingDirs.add(second);
        assertEquals("up down ", Adventure.combineDirections(testingDirs));
    }

    @Test
    public void testTieGame() throws Exception {
        Direction first = new Direction("up", "first");
        Direction second = new Direction("down", "second");
        List<Direction> testingDirs = new ArrayList<Direction>();
        testingDirs.add(first);
        testingDirs.add(second);
        Room room1 = new Room("room1", "first", null, null);
        Room room2 = new Room("room2", "second", null, null);
        List<Room> testRooms = new ArrayList<Room>();
        testRooms.add(room1);
        testRooms.add(room2);
        assertEquals("room1", Adventure.getRoomObj("room1", testRooms));
    }

}