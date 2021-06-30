package view;

import model.Ocean;
import model.cell.CellGroup;

public class OceanPrinter {
    private Ocean ocean;
    public OceanPrinter(Ocean ocean){
        this.ocean = ocean;
    }
    public void show(){
        CellGroup cellGroup = ocean.getCellGroup();
        cellGroup.getCells().forEach(el-> {
            el.forEach(els->System.out.print(els.getRepresentation()));
            System.out.println();

        });
    }
}
