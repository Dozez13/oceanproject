package model.cell;

import model.ConsoleRepresentation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObstacleTest {

    @Test
    void getCellRepresentation() {
        Obstacle obstacle = new Obstacle();
        assertEquals(ConsoleRepresentation.OBSTACLE,obstacle.getCellConsoleRepresentation());
    }
}