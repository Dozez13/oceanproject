package model.cell;

import model.ConsoleRepresentation;
import model.point.Point;

public class Obstacle extends Cell {
    private static final ConsoleRepresentation CELL_REPRESENTATION = ConsoleRepresentation.OBSTACLE;

    public Obstacle() {
        this(new Point(0,0));
    }
    public Obstacle(Obstacle obstacle){
        super(obstacle);
    }
    public Obstacle(Point oceanCoordinate) {
        super(oceanCoordinate);
    }
    @Override
    public ConsoleRepresentation getCellRepresentation() {
        return CELL_REPRESENTATION;
    }
}
