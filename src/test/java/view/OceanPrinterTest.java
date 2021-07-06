package view;

import model.Ocean;
import model.cell.CellGroup;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import static org.junit.jupiter.api.Assertions.*;

class OceanPrinterTest {
    private OceanPrinter oceanPrinter;
    private Ocean ocean;
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
        oceanPrinter = new OceanPrinter();
        CellGroup cellGroup = new CellGroup.Builder()
                .setPredatorNumber(25)
                .setPreyNumber(150)
                .setObstaclesNumber(75)
                .setRowNum(25)
                .setColNum(70)
                .build();
        cellGroup.populateCellList();
        ocean = new Ocean(cellGroup);
    }
    @Test
    void show() {
        StringBuilder expectedResult = new StringBuilder();
        CellGroup cellGroup = ocean.getCellGroup();
        cellGroup.getCells().forEach(el -> {
            el.forEach(eld->expectedResult.append(eld.getCellRepresentation().getRepresentation()));
            expectedResult.append(System.lineSeparator());
        });
        oceanPrinter.show(ocean);
        assertEquals(expectedResult.toString(),baos.toString());
    }
    @AfterAll
    public static void unMockSystemOut(){
        System.setOut(systemOut);
    }
}