package demo;

import controller.OceanController;
import exception.EntityException;
import model.Ocean;
import model.cell.Cell;
import model.cell.CellGroup;
import model.point.Point;
import model.validator.CellGroupValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.OceanPrinter;



public class Demo {
    private static final Logger LOGGER = LogManager.getLogger(Demo.class);
    public static void main(String[] args) {
        CellGroup cellGroup = new CellGroup(20, 150, 75, 25, 70);
        cellGroup.setValidator(new CellGroupValidator());
        try {
            cellGroup.validate();
        } catch (EntityException e) {
          LOGGER.error(e.getMessage());
        }
        Ocean ocean = new Ocean(cellGroup);
        OceanPrinter oceanPrinter = new OceanPrinter();
        OceanController oceanController = new OceanController(ocean,oceanPrinter,5);
        oceanController.start();




    }
}
