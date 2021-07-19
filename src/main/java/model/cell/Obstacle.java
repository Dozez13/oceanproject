package model.cell;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.ConsoleRepresentation;

import model.point.Point;

import java.util.Objects;

public class Obstacle extends Cell {
    private final ImageView obstaclesDesktopImage = new ImageView(new Image("obstacles.png"));
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Obstacle obstacle = (Obstacle) o;
        return Objects.equals(obstaclesDesktopImage, obstacle.obstaclesDesktopImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), obstaclesDesktopImage);
    }

    @Override
    public ImageView getDesktopRepresentation(){
        return obstaclesDesktopImage;
    }
    @Override
    public ConsoleRepresentation getCellConsoleRepresentation() {
        return ConsoleRepresentation.OBSTACLE;
    }
}
