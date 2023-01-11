package com.dragon;

import java.io.ByteArrayInputStream;

import static com.dragon.Dragon.getCaveText;
import static org.junit.jupiter.api.Assertions.*;

class DragonTest {

    @org.junit.jupiter.api.Test
    void getResponseTest() {
        String input = "1";
        ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
        System.setIn(bais);

        int expected = 1;
        int actual = Dragon.getResponse();
        assertEquals(expected, actual);

    }
    @org.junit.jupiter.api.Test
    void getCaveTextTest1() {

        String expected = "You approach the cave...\n" +
                "It is dark and spooky...\n" +
                "A large dragon jumps out in front of you! He opens his jaws and...\n" +
                "Gobbles you down in one bite!\n";
        String actual = getCaveText(1);

        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getCaveTextTest2() {

        String expected = "You approach the cave...\n" +
                "It is dark and spooky...\n" +
                "A large dragon jumps out in front of you! He opens his jaws and...\n" +
                "Offers you a handful of gold!\n";
        String actual = getCaveText(2);

        assertEquals(expected, actual);
    }

}