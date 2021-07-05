package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleRepresentationTest {
    private ConsoleRepresentation consoleRepresentation;
    @BeforeEach
    public void init(){
        consoleRepresentation = ConsoleRepresentation.PREY;
    }
    @Test
    void getRepresentation() {
        assertEquals("F",consoleRepresentation.getRepresentation());
    }

    @Test
    void values() {
        assertNotNull(ConsoleRepresentation.values());
    }

    @Test
    void valueOf() {
        assertNotNull(ConsoleRepresentation.valueOf("PREY"));
    }
}