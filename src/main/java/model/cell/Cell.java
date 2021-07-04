package model.cell;


import model.ConsoleRepresentation;
import model.point.Point;

import java.util.Objects;

public class Cell {
    private Point oceanCoordinate;
    private static final ConsoleRepresentation CELL_REPRESENTATION = ConsoleRepresentation.CELL;
    private boolean moveIsDone = false;
    public Cell() {
        this(new Point(0,0));
    }

    public Cell(Point oceanCoordinate) {
        this.oceanCoordinate = oceanCoordinate;
    }
    public Cell(Cell cell){
        this.oceanCoordinate = new Point(cell.getOceanCoordinate());
    }

    public boolean isMoveIsDone() {
        return moveIsDone;
    }

    public void setMoveIsDone(boolean moveIsDone) {
        this.moveIsDone = moveIsDone;
    }

    public Point getOceanCoordinate() {
        return oceanCoordinate;
    }

    public void setOceanCoordinate(Point oceanCoordinate) {
        this.oceanCoordinate = oceanCoordinate;
    }

    public ConsoleRepresentation getCellRepresentation() {
        return CELL_REPRESENTATION;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return getOceanCoordinate().equals(cell.getOceanCoordinate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOceanCoordinate());
    }
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Type of cell is ").append(getCellRepresentation().getRepresentation())
                .append(System.lineSeparator()).
                append("X : ").append(getOceanCoordinate().getX())
                .append(" Y : ").append(getOceanCoordinate().getY());
        return builder.toString();
    }
}
