package view;

import model.Ocean;
import model.cell.*;

public class OceanPrinter {

    public void show(Ocean ocean) {
        CellGroup cellGroup = ocean.getCellGroup();
        System.out.print(cellGroup);
    }

}
