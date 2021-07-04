package demo;

import controller.OceanController;
import exception.EntityException;
import model.Ocean;
import model.cell.Cell;
import model.cell.CellGroup;
import model.validator.CellGroupValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.OceanPrinter;

import java.util.Arrays;


public class Demo {
    private static final Logger LOGGER = LogManager.getLogger(Demo.class);
    public static void main(String[] args) {
//        CellGroup cellGroup = new CellGroup(100, 150, 75, 25, 70);
//        cellGroup.setValidator(new CellGroupValidator());
//        try {
//            cellGroup.validate();
//        } catch (EntityException e) {
//          LOGGER.error(e.getMessage());
//        }
//        Ocean ocean = new Ocean(cellGroup);
//        OceanPrinter oceanPrinter = new OceanPrinter();
//        OceanController oceanController = new OceanController(ocean,oceanPrinter,200);
//        oceanController.start();

        Cell[] celss = new Cell[15];
        Arrays.fill(celss,0,5,new Cell());
        System.out.println(celss[0]==celss[3]);





    }
}
