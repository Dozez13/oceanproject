package view;

import model.Ocean;
import model.cell.*;

public class OceanPrinter {

    public void show(Ocean ocean) {
        CellGroup cellGroup = ocean.getCellGroup();
        cellGroup.getCells().forEach(el -> {
            el.forEach(eld->System.out.print(eld.getCellRepresentation().getRepresentation()));
            System.out.println();
        });
    }

}
