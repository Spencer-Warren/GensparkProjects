package com.dragon;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class DragonTest {
    private static final PrintStream originalOut = System.out;
    private static final InputStream originalIn = System.in;
    private static final ByteArrayOutputStream baos = new ByteArrayOutputStream();

    @BeforeAll
    static void beforeAll() {
        System.setOut(new PrintStream(baos));
    }

    @AfterEach
    void afterEach() {
        baos.reset();
    }

    @AfterAll
    static void afterAll() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }


    @Test
    void getResponseTest() {
        String input = "1";
        ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
        System.setIn(bais);

        int expected = 1;
        int actual = new Dragon().getResponse();
        assertEquals(expected, actual);

    }

    @Test
    void getCaveTextTest1() {

        String expected = "You approach the cave...%n" +
                "It is dark and spooky...%n" +
                "A large dragon jumps out in front of you! He opens his jaws and...%n" +
                "Gobbles you down in one bite!%n";
        expected = String.format(expected);
        String actual = new Dragon().getCaveText(1);

        assertEquals(expected, actual);
    }

    @Test
    void getCaveTextTest2() {

        String expected = "You approach the cave...%n" +
                "It is dark and spooky...%n" +
                "A large dragon jumps out in front of you! He opens his jaws and...%n" +
                "Offers you a handful of gold!%n";
        expected = String.format(expected);
        String actual = new Dragon().getCaveText(2);

        assertEquals(expected, actual);
    }

    @Test
    void fullTest() {
        String input = "1%n";
        input = String.format(input);
        InputStream in = new ByteArrayInputStream(input.getBytes());

        String expected = "You are in a land full of dragons. In front of you,%n" +
                "you see two caves. In one cave, the dragon is friendly%n" +
                "and will share his treasure with you. The other dragon%n" +
                "is greedy and hungry and will eat you on sight.%n" +
                "Which cave will you go into? (1 or 2)%n%n" +
                "You approach the cave...%n" +
                "It is dark and spooky...%n" +
                "A large dragon jumps out in front of you! He opens his jaws and...%n" +
                "Gobbles you down in one bite!%n%n";
        expected = String.format(expected);

        new Dragon(in).run();
        assertEquals(expected, baos.toString());
    }

    @Test
    void numberExceptionTest(){
        String input = "a%n";
        input = String.format(input);
        InputStream in = new ByteArrayInputStream(input.getBytes());

        new Dragon(in).getResponse();
        String expected = "Please enter 1 or 2";
        assertTrue(baos.toString().contains(expected), "Should contain:" + expected);
    }

//    @Test
//    void mainTest(){
//        String input = "1%n";
//        input = String.format(input);
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//
//        String expected = "You are in a land full of dragons. In front of you,%n" +
//                "you see two caves. In one cave, the dragon is friendly%n" +
//                "and will share his treasure with you. The other dragon%n" +
//                "is greedy and hungry and will eat you on sight.%n" +
//                "Which cave will you go into? (1 or 2)%n%n" +
//                "You approach the cave...%n" +
//                "It is dark and spooky...%n" +
//                "A large dragon jumps out in front of you! He opens his jaws and...%n" +
//                "Gobbles you down in one bite!%n%n";
//        expected = String.format(expected);
//        as
//    }

}