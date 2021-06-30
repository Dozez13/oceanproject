package model.cell;

import model.point.Point;

public class Prey extends Cell{
    private final String representation  = "F";
    private int timeToReproduce;
    public Prey(int timeToReproduce){
        this.timeToReproduce = timeToReproduce;
    }
    public Prey(Point oceanCoordinate, int timeToReproduce){
        super(oceanCoordinate);
        this.timeToReproduce = timeToReproduce;
    }
    public int getTimeToReproduce(){
        return timeToReproduce;
    }
    public void setTimeToReproduce(int timeToReproduce){
        if(this.timeToReproduce==0)return;
        this.timeToReproduce = timeToReproduce;
    }

    @Override
    public String getRepresentation() {
        return representation;
    }
}
