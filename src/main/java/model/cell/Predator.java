package model.cell;

import constant.Constant;
import model.ConsoleRepresentation;

import model.DesktopRepresentation;
import model.point.Point;

import java.util.Map;
import java.util.Objects;

public class Predator extends Prey {
    private int timeToFeed;
    public Predator(){
        super();
        this.timeToFeed = Constant.TIME_TO_FEED;
    }
    public Predator(CellGroup cellGroup,int timeToReproduce,int timeToFeed){
        this(new Point(0,0),cellGroup,timeToReproduce,timeToFeed);
    }
    public Predator(Point oceanCoordinate,CellGroup cellGroup, int timeToReproduce, int timeToFeed) {
        super(oceanCoordinate,cellGroup, timeToReproduce);
        this.timeToFeed = timeToFeed;
    }

    @Override
    public DesktopRepresentation getDesktopRepresentation(){
        return DesktopRepresentation.PREDATOR;
    }
    @Override
    public ConsoleRepresentation getCellConsoleRepresentation() {
        return ConsoleRepresentation.PREDATOR;
    }
    public void setTimeToFeed(int timeToFeed) {
        if(this.timeToFeed==0)return;
        this.timeToFeed = timeToFeed;
    }

    public int getTimeToFeed() {
        return timeToFeed;
    }
    @Override
    public void process(){
        if (this.isMoveIsDone()) return;
        this.setTimeToFeed(this.getTimeToFeed() - 1);
        if (this.getTimeToFeed() == 0) {
            getCellGroup().destroyCell(this.getOceanCoordinate());
            getCellGroup().createCell(this.getOceanCoordinate());
            getCellGroup().setPredatorNumber( getCellGroup().getPredatorNumber()-1);
        }else {
            this.setTimeToReproduce(this.getTimeToReproduce() - 1);
            Map.Entry<Point, ConsoleRepresentation> point =  getCellGroup().findPoint(this);
            if (point == null) return;
            Point prevPoint = this.getOceanCoordinate();
            getCellGroup().moveCell(this.getOceanCoordinate(), point.getKey());
            if (point.getValue().equals(ConsoleRepresentation.PREY)) {
                this.setTimeToFeed(Constant.TIME_TO_FEED);
                getCellGroup().setPreyNumber( getCellGroup().getPreyNumber()-1);
            }

            if (this.getTimeToReproduce() == 0 && this.getTimeToFeed() != 0) {
                getCellGroup().destroyCell(prevPoint);
                getCellGroup().createPredator(prevPoint);
                this.setTimeToReproduce(Constant.TIME_TO_REPRODUCE);
                getCellGroup().setPredatorNumber( getCellGroup().getPredatorNumber()+1);
            }else {
                getCellGroup().createCell(prevPoint);
            }
        }
        this.setMoveIsDone(true);
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
        builder.append("Type of cell is ").append(getCellConsoleRepresentation().getRepresentation())
                .append(System.lineSeparator()).
                append("X : ").append(getOceanCoordinate().getX())
                .append(" Y : ").append(getOceanCoordinate().getY())
                .append(" Time to reproduce is ").append(getTimeToReproduce())
        .append(" Time to feed is ").append(getTimeToFeed())
                .append("Is move done : ").append(isMoveIsDone());
        return builder.toString();
    }
}
