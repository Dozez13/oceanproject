package controller;


import javafx.application.Platform;
import javafx.concurrent.Task;
import model.Ocean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.OceanView;


public class OceanController {
    private static final Logger LOGGER = LogManager.getLogger(OceanController.class);
    private final Ocean ocean;
    private final OceanView view;
    private final int iterationNumber;

    public OceanController(Ocean ocean, OceanView view, int iterationNumber) {
        this.view = view;
        this.ocean = ocean;
        this.iterationNumber = iterationNumber;
    }

    public void start() {
        LOGGER.info("Simulation is started");
     Task<Void> task = new Task<Void>() {
         @Override
         protected Void call() throws Exception {
             for (int i = 0; i < iterationNumber; i++) {

                 Platform.runLater(OceanController.this::display);
                 Thread.sleep(500);
                 ocean.processCell();
                 if (ocean.getCellGroup().getPredatorNumber() - ocean.getCellGroup().getObstaclesNumber() == ocean.getCellGroup().getRowNum() * ocean.getCellGroup().getColNum() ||
                         ocean.getCellGroup().getPreyNumber() == 0 || ocean.getCellGroup().getPredatorNumber() == 0) return null;

             }
             return null;
         }
     };
     Thread thread = new Thread(task);
     thread.setDaemon(true);
     thread.start();

    }

    public void display() {
        view.show(ocean);
    }


}
