package view;

import model.Ocean;
import model.cell.*;

public class OceanPrinter implements OceanView{

    public void show(Ocean ocean) {
        CellGroup cellGroup = ocean.getCellGroup();
        System.out.print(cellGroup);
    }

}
