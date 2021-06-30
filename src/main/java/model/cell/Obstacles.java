package model.cell;

import model.point.Point;

public class Obstacles extends Cell{
    private final String representation  = "#";
    public Obstacles(){}
    public Obstacles(Point oceanCoordinate) {
        super(oceanCoordinate);
    }

    @Override
    public String getRepresentation() {
        return representation;
    }


}
