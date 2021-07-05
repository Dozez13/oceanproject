package demo;


import controller.OceanController;
import exception.ApplicationException;
import exception.EntityException;
import model.Ocean;
import model.cell.CellGroup;
import model.validator.CellGroupValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.OceanPrinter;



public class Demo {
    private static final Logger LOGGER = LogManager.getLogger(Demo.class);
    public static void main(String[] args) throws ApplicationException {
        CellGroup cellGroup = new CellGroup(1, 2, 3, 5, 10);
        cellGroup.setValidator(new CellGroupValidator());
        try {
            cellGroup.validate();
        } catch (EntityException e) {
          LOGGER.error(e.getMessage());
          throw new ApplicationException(e.getMessage());
        }
        cellGroup.initCellGroup();
        Ocean ocean = new Ocean(cellGroup);
        OceanPrinter oceanPrinter = new OceanPrinter();
        OceanController oceanController = new OceanController(ocean,oceanPrinter,5);
        oceanController.start();
    }
}
