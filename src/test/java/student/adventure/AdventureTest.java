package student.adventure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;


public class AdventureTest {
    @Before
    public void setUp() {
        ObjectMapper mapper = new ObjectMapper();
//        Layout tester = new ObjectMapper().readValue(new File("src/main/resources/siebel.json"), Layout.class);
        // This is run before every test.
    }

//    @Test
//    public void testCombineDirsHelper() throws Exception {
//        List<Direction> testFunc = new List<>();
//        testFunc =
//        assertEquals("how are you", Adventure.combineDirections("how", "are", "you"));
//    }
//
//    @Test
//    public void testTieGame() throws Exception {
//        assertEquals(Evaluation.NoWinner, TicTacToe.evaluateBoard("xoxxxooxo"));
//    }
//
//    @Test
//    public void testNullBoard() throws Exception {
//        assertEquals(Evaluation.InvalidInput, TicTacToe.evaluateBoard(null));
//    }
//
//    @Test
//    public void testEmptyString() throws Exception {
//        assertEquals(Evaluation.InvalidInput, TicTacToe.evaluateBoard(""));
//    }
//
//    @Test
//    public void testTooLongString() throws Exception {
//        assertEquals(Evaluation.InvalidInput, TicTacToe.evaluateBoard("xxoxo.ox.xo...xo"));
//    }
//
//    @Test
//    public void testTooShortString() throws Exception {
//        assertEquals(Evaluation.InvalidInput, TicTacToe.evaluateBoard("abc"));
//    }

}