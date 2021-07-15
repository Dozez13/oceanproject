package model.cell;

import model.ConsoleRepresentation;
import model.point.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    private Cell cell;
    @BeforeEach
    public void init(){
        cell = new Cell();
    }
    @Test
    void isMoveIsDoneGetTrue() {
       cell.setMoveIsDone(true);
       assertTrue(cell.isMoveIsDone());
    }

    @Test
    void setMoveIsDoneSetTrue() {
        cell.setMoveIsDone(true);
        assertTrue(cell.isMoveIsDone());
    }

    @Test
    void getOceanCoordinate() {
        Point point = new Point(1,2);
        cell.setOceanCoordinate(point);
        assertEquals(new Point(1,2),cell.getOceanCoordinate());
    }

    @Test
    void setOceanCoordinate() {
        Point point = new Point(1,2);
        cell.setOceanCoordinate(point);
        assertEquals(new Point(1,2),cell.getOceanCoordinate());
    }

    @Test
    void getCellRepresentation() {
        assertEquals(ConsoleRepresentation.CELL,cell.getCellConsoleRepresentation());
    }

    @Test
    void testEqualsTrue() {
        Point point = new Point(1,2);
        Point point1 = new Point(1,2);
        cell.setOceanCoordinate(point);
        Cell cell1 = new Cell();
        cell1.setOceanCoordinate(point1);
        assertEquals(cell1,cell);
    }

    @Test
    void testHashCode() {
        Point point = new Point(1,2);
        Point point1 = new Point(1,2);
        cell.setOceanCoordinate(point);
        Cell cell1 = new Cell();
        cell1.setOceanCoordinate(point1);
        assertEquals(cell1.hashCode(),cell.hashCode());
    }

    @Test
    void testToString() {
        cell.setOceanCoordinate(new Point(1,2));
        cell.setMoveIsDone(true);
        assertEquals("Type of cell is -"+System.lineSeparator() +
                "Point{x=1, y=2}Is move done : true",cell.toString());
    }
}