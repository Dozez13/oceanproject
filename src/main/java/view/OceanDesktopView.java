package view;

import constant.Constant;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import model.Ocean;
import model.cell.Cell;
import model.cell.CellGroup;

import java.util.List;

public class OceanDesktopView implements OceanView{
    private final Scene scene;
    public OceanDesktopView(Scene scene){
        this.scene = scene;
    }
    @Override
    public void show(Ocean ocean) {
        System.out.println("Desktop view"+Thread.currentThread());
            GridPane simulationGrid = new GridPane();
            CellGroup cellGroup = ocean.getCellGroup();
            List<List<Cell>> cellsList = cellGroup.getCells();
            cellsList.forEach(cellList -> {
                simulationGrid.getRowConstraints().add(new RowConstraints(Constant.SIMULATION_HEIGHT / (double) cellGroup.getRowNum()));
                cellList.forEach(cell -> {
                    simulationGrid.getColumnConstraints().add(new ColumnConstraints(Constant.SIMULATION_WIDTH / (double) cellGroup.getColNum()));
                    ImageView cellImage = cell.getDesktopRepresentation().getImage();
                    cellImage.setFitHeight(Constant.SIMULATION_HEIGHT / (double) cellGroup.getRowNum());
                    cellImage.setFitWidth(Constant.SIMULATION_WIDTH / (double) cellGroup.getColNum());
                    Pane pane = new Pane();
                    pane.getChildren().add(cellImage);
                    pane.getStyleClass().add("myBorder");
                    System.out.println(cell+" index of cell list "+cellList.indexOf(cell)+" index of cell in list "+cellsList.indexOf(cellList));
                    simulationGrid.add(pane, cellList.indexOf(cell), cellsList.indexOf(cellList));
                });

            });
         scene.setRoot(simulationGrid);




    }
}
