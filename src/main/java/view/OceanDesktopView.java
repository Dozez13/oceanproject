package view;

import constant.Constant;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import model.Ocean;
import model.cell.Cell;
import model.cell.CellGroup;

import java.util.List;

public class OceanDesktopView implements OceanView{
    private final Stage stage;
    public OceanDesktopView(Stage stage){
        this.stage = stage;
    }
    @Override
    public void show(Ocean ocean) {
        GridPane simulationGrid = new GridPane();
        CellGroup cellGroup = ocean.getCellGroup();
        List<List<Cell>> cellsList = cellGroup.getCells();
        cellsList.forEach(cellList->{
            simulationGrid.getRowConstraints().add(new RowConstraints(Constant.SIMULATION_HEIGHT/(double)cellGroup.getRowNum()));
            System.out.println("height "+Constant.SIMULATION_HEIGHT/(double)cellGroup.getRowNum());
            System.out.println("width "+Constant.SIMULATION_WIDTH/(double)cellGroup.getColNum());
            cellList.forEach(cell->{
                simulationGrid.getColumnConstraints().add(new ColumnConstraints(Constant.SIMULATION_WIDTH/(double)cellGroup.getColNum()));
                ImageView cellImage = cell.getDesktopRepresentation().getImage();
                cellImage.setFitHeight(Constant.SIMULATION_HEIGHT/(double)cellGroup.getRowNum());
                cellImage.setFitWidth(Constant.SIMULATION_WIDTH/(double)cellGroup.getColNum());
                Pane pane = new Pane();
                System.out.println("Image is added to pane"+pane.getChildren().add(cellImage));
                pane.getStyleClass().add("myBorder");
                simulationGrid.add(pane,cellList.indexOf(cell),cellsList.indexOf(cellList));
            });

        });
        Scene scene = new Scene(simulationGrid,Constant.SIMULATION_WIDTH,Constant.SIMULATION_HEIGHT);
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);

    }
}
