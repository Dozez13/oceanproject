package demo;

import constant.Constant;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;

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


        Slider sliderColumnNumber = new Slider(20, 70, 5);
        Slider sliderRowNumber = new Slider(35, 25, 5);
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
            Stage simulationStage = new Stage();
            GridPane gridPane1 = new GridPane();
            for(int i=0;i<20;i++){
                gridPane1.getRowConstraints().add(new RowConstraints(50));
                for(int j=0;j<20;j++){
                    Image image = new Image("predator.png");
                    ImageView imageView = new ImageView(image);
                    imageView.getStyleClass().add("img-view");
                    Pane pane = new Pane();
                    pane.getChildren().add(imageView);
                    pane.getStyleClass().add("myBorder");
                    gridPane1.getColumnConstraints().add(new ColumnConstraints(50));
                    gridPane1.add(pane,i,j);
                }

            }
            gridPane1.getStylesheets().add("style.css");
            simulationStage.initModality(Modality.APPLICATION_MODAL);
            simulationStage.setScene(new Scene(gridPane1,1000,1000));
            simulationStage.show();
        }));

        List<Slider> sliders = new ArrayList<>(6);

        sliders.addAll(Arrays.asList(sliderColumnNumber,sliderRowNumber,sliderPredatorNumber,sliderPreyNumber,sliderObstaclesNumber,sliderIterationNumber));
        sliders.forEach(slider -> slider.valueProperty().addListener((obs, oldVal, newVal) ->
                slider.setValue(newVal.intValue())));

        gridPane.add(title,2,0);
        IntStream.range(0,sliderLabels.length).forEach(i->{
            Label textLabel = new Label(sliderLabels[i].getText());
            Label currentValue = new Label();
            Label maxValue = new Label();
            currentValue.textProperty().bind(sliders.get(i).valueProperty().asString(format));
            maxValue.textProperty().bind(sliders.get(i).maxProperty().asString(format));
            gridPane.add(textLabel,0,i+1);
            gridPane.add(currentValue,1,i+1);
            gridPane.add(sliders.get(i),2,i+1);
            gridPane.add(maxValue,3,i+1);
        });

        gridPane.add(startSimulationButton,3,7);
        gridPane.getStylesheets().add("style.css");
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(gridPane, 900, 500));
        primaryStage.show();
    }
}
