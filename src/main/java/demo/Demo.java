package demo;


import controller.OceanController;
import model.Ocean;
import model.cell.CellGroup;
import model.validator.CellGroupValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.OceanPrinter;



public class Demo {
    private static final Logger LOGGER = LogManager.getLogger(Demo.class);
    public static void main(String[] args){
        CellGroup cellGroup = new CellGroup.Builder()
                .setPredatorNumber(25)
                .setPreyNumber(150)
                .setObstaclesNumber(75)
                .setRowNum(25)
                .setColNum(70)
                .build();
        cellGroup.setValidator(new CellGroupValidator());
            if(!cellGroup.validate()){
                String message = "Matrix invalid parameters";
                LOGGER.error(message);
        }else {
                cellGroup.populateCellList();
                Ocean ocean = new Ocean(cellGroup);
                OceanPrinter oceanPrinter = new OceanPrinter();
                OceanController oceanController = new OceanController(ocean,oceanPrinter,100);
                oceanController.start();
            }

    }
}
