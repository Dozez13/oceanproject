package model.cell;



import java.util.ArrayList;

import java.util.List;


public class CellGroup {
    private List<List<Cell>> cells;

    public CellGroup(List<List<Cell>>  cells){

        this.cells = new ArrayList<>(cells);

    }
    public List<List<Cell>>   getCells() {
        return cells;
    }

    public void setCells(List<List<Cell>>  cells) {
        this.cells = cells;
    }


}
