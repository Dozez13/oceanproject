package model.cell;

import model.ConsoleRepresentation;

import model.DesktopRepresentation;
import model.point.Point;

public class Obstacle extends Cell {
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
    public DesktopRepresentation getDesktopRepresentation(){
        return DesktopRepresentation.OBSTACLES;
    }
    @Override
    public ConsoleRepresentation getCellConsoleRepresentation() {
        return ConsoleRepresentation.OBSTACLE;
    }
}
