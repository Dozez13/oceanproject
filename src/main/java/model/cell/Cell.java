package model.cell;

import model.point.Point;

import java.util.Objects;

public class Cell {
    private Point oceanCoordinate;
    private final String representation = "*";
    public Cell() {}
    public Cell(Point oceanCoordinate){
        this.oceanCoordinate = oceanCoordinate;
    }
    public Point getOceanCoordinate() {
        return oceanCoordinate;
    }

    public void setOceanCoordinate(Point oceanCoordinate) {
        this.oceanCoordinate = oceanCoordinate;
    }

    public String getRepresentation() {
        return representation;
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
}
