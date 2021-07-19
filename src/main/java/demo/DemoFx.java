package demo;

import constant.Constant;
import controller.OceanController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Ocean;
import model.cell.CellGroup;
import view.OceanDesktopView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class DemoFx extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Constant.SliderLabel[] sliderLabels = Constant.SliderLabel.values();
        String format = "%.0f";
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        Scene scene = new Scene(gridPane, Constant.SIMULATION_WIDTH, Constant.SIMULATION_HEIGHT);
        scene.getStylesheets().add("style.css");
        Slider sliderColumnNumber = new Slider(20, 70, 5);
        Slider sliderRowNumber = new Slider(5, 25, 5);
        Slider sliderPredatorNumber = new Slider();
        Slider sliderPreyNumber = new Slider();
        Slider sliderObstaclesNumber = new Slider();
        Slider sliderIterationNumber = new Slider(1, 1000, 5);
        sliderPreyNumber.setMin(1);
        sliderPredatorNumber.setMin(1);
        sliderObstaclesNumber.setMin(1);


        sliderPredatorNumber.maxProperty().bind(sliderColumnNumber.valueProperty().multiply(sliderRowNumber.valueProperty()).subtract(sliderPreyNumber.valueProperty().add(sliderObstaclesNumber.valueProperty())));
        sliderPreyNumber.maxProperty().bind(sliderColumnNumber.valueProperty().multiply(sliderRowNumber.valueProperty()).subtract(sliderPredatorNumber.valueProperty().add(sliderObstaclesNumber.valueProperty())));
        sliderObstaclesNumber.maxProperty().bind(sliderColumnNumber.valueProperty().multiply(sliderRowNumber.valueProperty()).subtract(sliderPreyNumber.valueProperty().add(sliderPredatorNumber.valueProperty())));

        Label title = new Label("Choose properties of simulation");
        title.setAlignment(Pos.CENTER_LEFT);

        Button startSimulationButton = new Button("Start simulation");
        startSimulationButton.setAlignment(Pos.CENTER_RIGHT);

        startSimulationButton.setOnAction((event -> {
            System.out.println(Thread.currentThread().toString());
            CellGroup cellGroup = new CellGroup.Builder().setColNum((int) sliderColumnNumber.getValue()).setRowNum((int) sliderRowNumber.getValue())
                    .setObstaclesNumber((int) sliderObstaclesNumber.getValue()).setPreyNumber((int) sliderPreyNumber.getValue()).setPredatorNumber((int) sliderPredatorNumber.getValue())
                    .build();
            cellGroup.populateCellList();
            Ocean ocean = new Ocean(cellGroup);
            primaryStage.setHeight(Constant.SIMULATION_HEIGHT);
            primaryStage.setWidth(Constant.SIMULATION_WIDTH);
            OceanDesktopView desktopView = new OceanDesktopView(scene);
            OceanController oceanController = new OceanController(ocean, desktopView, (int) sliderIterationNumber.getValue());
            oceanController.start();


        }));

        List<Slider> sliders = new ArrayList<>(6);

        sliders.addAll(Arrays.asList(sliderColumnNumber, sliderRowNumber, sliderPredatorNumber, sliderPreyNumber, sliderObstaclesNumber, sliderIterationNumber));
        sliders.forEach(slider -> slider.valueProperty().addListener((obs, oldVal, newVal) ->
                slider.setValue(newVal.intValue())));

        gridPane.add(title, 2, 0);
        IntStream.range(0, sliderLabels.length).forEach(i -> {
            Label textLabel = new Label(sliderLabels[i].getText());
            Label currentValue = new Label();
            Label maxValue = new Label();
            currentValue.textProperty().bind(sliders.get(i).valueProperty().asString(format));
            maxValue.textProperty().bind(sliders.get(i).maxProperty().asString(format));
            gridPane.add(textLabel, 0, i + 1);
            gridPane.add(currentValue, 1, i + 1);
            gridPane.add(sliders.get(i), 2, i + 1);
            gridPane.add(maxValue, 3, i + 1);
        });

        gridPane.add(startSimulationButton, 3, 7);
        gridPane.getStylesheets().add("style.css");
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
