package model.cell;

import model.ConsoleRepresentation;
import model.point.Point;

import java.util.Objects;

public class Prey extends Cell {
    private static final ConsoleRepresentation CELL_REPRESENTATION = ConsoleRepresentation.PREY;
    private int timeToReproduce;

    public Prey(int timeToReproduce) {
        this(new Point(0,0),timeToReproduce);
    }
    public Prey(Prey prey){
        super(prey);
        this.timeToReproduce = prey.getTimeToReproduce();
    }
    public Prey(Point oceanCoordinate, int timeToReproduce) {
        super(oceanCoordinate);
        this.timeToReproduce = timeToReproduce;
    }
    @Override
    public ConsoleRepresentation getCellRepresentation() {
        return CELL_REPRESENTATION;
    }
    public int getTimeToReproduce() {
        return timeToReproduce;
    }

    public void setTimeToReproduce(int timeToReproduce) {
        if (this.timeToReproduce == 0) return;
        this.timeToReproduce = timeToReproduce;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Prey prey = (Prey) o;
        return getTimeToReproduce() == prey.getTimeToReproduce();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTimeToReproduce());
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Type of cell is ").append(getCellRepresentation().getRepresentation())
                .append(System.lineSeparator()).
                append("X : ").append(getOceanCoordinate().getX())
                .append(" Y : ").append(getOceanCoordinate().getY())
        .append(" Time to reproduce is ").append(getTimeToReproduce());
        return builder.toString();
    }

}
