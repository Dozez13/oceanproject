package model.point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    private Point point;
    @BeforeEach
    public void init(){
        point = new Point(1,2);
    }
    @Test
    void getX() {
        point.setX(1);
        assertEquals(1,point.getX());
    }

    @Test
    void getY() {
        point.setY(1);
        assertEquals(1,point.getY());
    }

    @Test
    void setX() {
        point.setX(3);
        assertEquals(3,point.getX());
    }

    @Test
    void setY() {
        point.setY(3);
        assertEquals(3,point.getY());
    }

    @Test
    void testHashCodeTrue() {
        Point point1 = new Point(1,2);
        assertEquals(point.hashCode(),point1.hashCode());
    }
    @Test
    void testHashCodeFalse() {
        Point point1 = new Point(2,2);
        assertNotEquals(point.hashCode(),point1.hashCode());
    }

    @Test
    void testEqualsTrue() {
        Point point1 = new Point(new Point(1,2));
        assertEquals(point,point1);

    }
    @Test
    void testEqualsFalse() {
        Point point1 = new Point(2,2);
        assertNotEquals(point,point1);

    }

    @Test
    void testToString() {
        assertEquals("Point{x=1, y=2}",point.toString());
    }
}