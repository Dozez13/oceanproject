package model;

import model.cell.CellGroup;

public class Ocean {
    private CellGroup cellGroup;

    public Ocean(CellGroup cellGroup) {
        this.cellGroup = cellGroup;

    }
    public void processCell(){
        cellGroup.processCells();
        cellGroup.setMoveFalse();
    }
    public CellGroup getCellGroup() {
        return cellGroup;
    }

    public void setCellGroup(CellGroup cellGroup) {
        this.cellGroup = cellGroup;
    }
}
