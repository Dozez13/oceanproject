package model.cell;

import model.point.Point;

public class Predator extends Prey{
    private final String representation  = "P";
    private int timeToFeed;
    public Predator(int timeToReproduce, int timeToFeed){
        super(timeToReproduce);
        this.timeToFeed = timeToFeed;
    }
    public Predator(Point oceanCoordinate, int timeToReproduce, int timeToFeed){
        super(oceanCoordinate,timeToReproduce);
        this.timeToFeed = timeToFeed;
    }

    @Override
    public String getRepresentation() {
        return representation;
    }


    public void setTimeToFeed(int timeToFeed) {
        this.timeToFeed = timeToFeed;
    }

    public int getTimeToFeed() {
        return timeToFeed;
    }
}
