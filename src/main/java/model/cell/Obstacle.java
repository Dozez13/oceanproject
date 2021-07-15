package model.cell;

import model.ConsoleRepresentation;
import model.point.Point;

public class Obstacle extends Cell {
    private static final ConsoleRepresentation CELL_REPRESENTATION = ConsoleRepresentation.OBSTACLE;
    public Obstacle(){
        super();
    }
    public Obstacle(CellGroup cellGroup){
        this(new Point(0,0),cellGroup);
    }
    public Obstacle(Point oceanCoordinate,CellGroup cellGroup) {
        super(oceanCoordinate,cellGroup);
    }
    @Override
    public ConsoleRepresentation getCellConsoleRepresentation() {
        return CELL_REPRESENTATION;
    }
}
