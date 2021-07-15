package controller;

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
        for (int i = 0; i < iterationNumber; i++) {
            if (ocean.getCellGroup().getPredatorNumber() - ocean.getCellGroup().getObstaclesNumber() == ocean.getCellGroup().getRowNum() * ocean.getCellGroup().getColNum() ||
                    ocean.getCellGroup().getPreyNumber() == 0 || ocean.getCellGroup().getPredatorNumber() == 0) return;
            display();
            System.out.println("Predator number is "+ocean.getCellGroup().getPredatorNumber()+" Prey Number is "+ocean.getCellGroup().getPreyNumber()+" Obstacles number is "+ocean.getCellGroup().getObstaclesNumber());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
            }
            ocean.processCell();
            System.out.println(System.lineSeparator() + System.lineSeparator());
            if (ocean.getCellGroup().getPredatorNumber() - ocean.getCellGroup().getObstaclesNumber() == ocean.getCellGroup().getRowNum() * ocean.getCellGroup().getColNum() ||
                    ocean.getCellGroup().getPreyNumber() == 0 || ocean.getCellGroup().getPredatorNumber() == 0) return;

        }

    }

    public void display() {
        view.show(ocean);
    }


}
