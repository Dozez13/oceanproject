package controller;

import model.Ocean;
import model.cell.CellGroup;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.OceanPrinter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class OceanControllerTest {
     private Ocean ocean;
    private OceanController oceanController;
    private static PrintStream systemOut;
    private static ByteArrayOutputStream baos;
    @BeforeAll
    public static void MockSystemOut(){
        baos = new ByteArrayOutputStream();
        systemOut = System.out;
        System.setOut(new PrintStream(baos));
    }
     @BeforeEach
     public void init(){
         CellGroup cellGroup = new CellGroup(1, 2, 3, 4, 10);
         cellGroup.initCellGroup();
         ocean = new Ocean(cellGroup);
         OceanPrinter oceanPrinter = new OceanPrinter();
         oceanController = new OceanController(ocean, oceanPrinter,1000);
     }
    @Test
    void display() {
        StringBuilder expectedResult = new StringBuilder();
        CellGroup cellGroup = ocean.getCellGroup();
        cellGroup.getCells().forEach(el -> {
            el.forEach(eld->expectedResult.append(eld.getCellRepresentation().getRepresentation()));
            expectedResult.append(System.lineSeparator());
        });
        oceanController.display();
        assertEquals(expectedResult.toString(),baos.toString());
    }
    @AfterAll
    public static void unMockSystemOut(){
        System.setOut(systemOut);
    }
}