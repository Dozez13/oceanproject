package model.cell;


import model.ConsoleRepresentation;
import model.point.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PredatorTest {
    private Predator predator;
    @BeforeEach
    public void init(){
        predator = new Predator();
    }
    @Test
    void getCellRepresentation() {
        assertEquals(ConsoleRepresentation.PREDATOR,predator.getCellRepresentation());
    }

    @Test
    void setTimeToFeed() {
        int timeToFeed = 4;
        predator.setTimeToFeed(timeToFeed);
        assertEquals(timeToFeed,predator.getTimeToFeed());
    }

    @Test
    void getTimeToFeed() {
        int timeToFeed = 4;
        predator.setTimeToFeed(timeToFeed);
        assertEquals(timeToFeed,predator.getTimeToFeed());
    }

    @Test
    void testEquals() {
        Point point = new Point(1,2);
        Point point1 = new Point(1,2);
        predator.setOceanCoordinate(point);
        predator.setMoveIsDone(true);
        Predator predator1 = new Predator();
        predator1.setOceanCoordinate(point1);
        predator1.setMoveIsDone(true);
        assertEquals(predator1,predator);
    }

    @Test
    void testHashCode() {
        Point point = new Point(1,2);
        Point point1 = new Point(1,2);
        predator.setOceanCoordinate(point);
        predator.setMoveIsDone(true);
        Predator predator1 = new Predator();
        predator1.setOceanCoordinate(point1);
        predator1.setMoveIsDone(true);
        assertEquals(predator1.hashCode(),predator.hashCode());
    }

    @Test
    void testToString() {
        Point point = new Point(1,2);
        predator.setOceanCoordinate(point);
        predator.setMoveIsDone(true);
        assertEquals("Type of cell is P"+System.lineSeparator() +
                "X : 1 Y : 2 Time to reproduce is 6 Time to feed is 6Is move done : true",predator.toString());
    }
}