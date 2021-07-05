package model.cell;

import model.ConsoleRepresentation;
import model.point.Point;

import java.util.Objects;

public class Predator extends Prey {
    private static final ConsoleRepresentation CELL_REPRESENTATION = ConsoleRepresentation.PREDATOR;
    private int timeToFeed;
    public Predator(int timeToReproduce,int timeToFeed){
        this(new Point(0,0),timeToReproduce,timeToFeed);
    }
    public Predator(Point oceanCoordinate, int timeToReproduce, int timeToFeed) {
        super(oceanCoordinate, timeToReproduce);
        this.timeToFeed = timeToFeed;
    }

    @Override
    public ConsoleRepresentation getCellRepresentation() {
        return CELL_REPRESENTATION;
    }
    public void setTimeToFeed(int timeToFeed) {
        if(this.timeToFeed==0)return;
        this.timeToFeed = timeToFeed;
    }

    public int getTimeToFeed() {
        return timeToFeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Predator predator = (Predator) o;
        return getTimeToFeed() == predator.getTimeToFeed();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTimeToFeed());
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Type of cell is ").append(getCellRepresentation().getRepresentation())
                .append(System.lineSeparator()).
                append("X : ").append(getOceanCoordinate().getX())
                .append(" Y : ").append(getOceanCoordinate().getY())
                .append(" Time to reproduce is ").append(getTimeToReproduce())
        .append(" Time to feed is ").append(getTimeToFeed())
                .append("Is move done : ").append(isMoveIsDone());
        return builder.toString();
    }
}
