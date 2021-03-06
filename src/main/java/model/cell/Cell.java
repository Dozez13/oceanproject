package model.cell;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.ConsoleRepresentation;


import model.point.Point;

import java.util.Objects;

public class Cell implements BaseCell {
    private final CellGroup cellGroup;
    private final ImageView cellDesktopImage = new ImageView(new Image("cell.png"));
    private Point oceanCoordinate;
    private boolean moveIsDone = false;
    public Cell(){
        this(new Point(0, 0), new CellGroup.Builder()
                .setPredatorNumber(25)
                .setPreyNumber(150)
                .setObstaclesNumber(75)
                .setRowNum(25)
                .setColNum(70)
                .build());
    }
    public Cell(CellGroup cellGroup){
        this(new Point(0, 0), cellGroup);
    }
    public Cell(Point oceanCoordinate,CellGroup cellGroup) {
        this.oceanCoordinate = oceanCoordinate;
        this.cellGroup = cellGroup;
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

    public CellGroup getCellGroup(){
        return cellGroup;
    }

    public ImageView getDesktopRepresentation(){
        return cellDesktopImage;
    }
    public ConsoleRepresentation getCellConsoleRepresentation() {
        return ConsoleRepresentation.CELL;
    }

    @Override
    public void process() {

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
        builder.append("Type of cell is ").append(getCellConsoleRepresentation().getRepresentation())
                .append(System.lineSeparator()).
                append(getOceanCoordinate().toString())
        .append("Is move done : ").append(isMoveIsDone())
        ;
        return builder.toString();
    }


}
