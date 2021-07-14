package model.cell;

import constant.Constant;
import model.ConsoleRepresentation;
import model.point.Point;

import java.util.Objects;

public class Prey extends Cell {
    private static final ConsoleRepresentation CELL_REPRESENTATION = ConsoleRepresentation.PREY;
    private int timeToReproduce;
    public Prey(){
        super();
        this.timeToReproduce = Constant.TIME_TO_REPRODUCE;
    }
    public Prey(CellGroup cellGroup,int timeToReproduce){
        this(new Point(0,0),cellGroup,timeToReproduce);
    }
    public Prey(Point oceanCoordinate,CellGroup cellGroup, int timeToReproduce) {
        super(oceanCoordinate,cellGroup);
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
    public void process(){
        if (this.isMoveIsDone()) return;
        this.setTimeToReproduce(this.getTimeToReproduce() - 1);
        Point pointTo = getCellGroup().findPoint(this);
        if (pointTo == null) return;
        Point prevPoint = this.getOceanCoordinate();
        getCellGroup().moveCell(this.getOceanCoordinate(), pointTo);
        if (this.getTimeToReproduce() == 0) {
            getCellGroup().destroyCell(prevPoint);
            getCellGroup().createPrey(prevPoint);
            this.setTimeToReproduce(Constant.TIME_TO_REPRODUCE);
            getCellGroup().setPreyNumber(getCellGroup().getPreyNumber()+1);
        }else {
            getCellGroup().createCell(prevPoint);
        }
        this.setMoveIsDone(true);
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
        .append(" Time to reproduce is ").append(getTimeToReproduce())
                .append("Is move done : ").append(isMoveIsDone());
        return builder.toString();
    }

}
