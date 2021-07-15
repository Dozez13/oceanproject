package model.cell;


import model.ConsoleRepresentation;
import model.point.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PreyTest {
    private Prey prey;
    @BeforeEach
    public void init(){
        prey = new Prey();
    }
    @Test
    void getCellRepresentation() {
        assertEquals(ConsoleRepresentation.PREY,prey.getCellConsoleRepresentation());
    }

    @Test
    void getTimeToReproduce() {
        int timeToReproduce = 4;
        prey.setTimeToReproduce(timeToReproduce);
        assertEquals(timeToReproduce,prey.getTimeToReproduce());
    }

    @Test
    void setTimeToReproduce() {
        int timeToReproduce = 4;
        prey.setTimeToReproduce(timeToReproduce);
        assertEquals(timeToReproduce,prey.getTimeToReproduce());
    }

    @Test
    void testEquals() {
        Point point = new Point(1,2);
        Point point1 = new Point(1,2);
        prey.setOceanCoordinate(point);
        Prey prey1 = new Prey();
        prey1.setOceanCoordinate(point1);
        assertEquals(prey,prey1);
    }

    @Test
    void testHashCode() {
        Point point = new Point(1,2);
        Point point1 = new Point(1,2);
        prey.setOceanCoordinate(point);
        Prey prey1 = new Prey();
        prey1.setOceanCoordinate(point1);
        assertEquals(prey.hashCode(),prey1.hashCode());
    }

    @Test
    void testToString() {
        Point point = new Point(1,2);
        prey.setOceanCoordinate(point);
        prey.setMoveIsDone(true);
        assertEquals("Type of cell is F"+System.lineSeparator()+
                "X : 1 Y : 2 Time to reproduce is 6Is move done : true",prey.toString());

    }
}