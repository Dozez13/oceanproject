package controller;

import model.Ocean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.OceanPrinter;




public class OceanController {
    private static final Logger LOGGER = LogManager.getLogger(OceanController.class);
    private final Ocean ocean;
    private final OceanPrinter view;
    private final int iterationNumber;

    public OceanController(Ocean ocean,OceanPrinter view,int iterationNumber) {
        this.view = view;
        this.ocean = ocean;
        this.iterationNumber = iterationNumber;
    }

    public void start(){
        LOGGER.info("Simulation is started");
        for(int i=0;i<iterationNumber;i++){
               if(ocean.getCellGroup().getPreyNumber()-ocean.getCellGroup().getObstaclesNumber()==ocean.getCellGroup().getRowNum()*ocean.getCellGroup().getColNum()||
                    ocean.getCellGroup().getPredatorNumber()-ocean.getCellGroup().getObstaclesNumber()==ocean.getCellGroup().getRowNum()*ocean.getCellGroup().getColNum()||
            ocean.getCellGroup().getPreyNumber()==0||ocean.getCellGroup().getPredatorNumber()==0)return;
            display();
            ocean.processCell();
            System.out.println(System.lineSeparator()+System.lineSeparator());
        }

    }
    public void display() {
        view.show(ocean);
    }



}
