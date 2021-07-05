package model.cell;

import model.ConsoleRepresentation;
import model.point.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import static org.junit.jupiter.api.Assertions.*;

class CellGroupTest {
    private CellGroup cellGroup;
    @BeforeEach
    public void init(){
        cellGroup = new CellGroup(2,3,5,5,2);
        cellGroup.initCellGroup();
    }
    @Test
    void addCell() {
        List<Cell> cells = new ArrayList<>(5);
        cellGroup.addCell(5,Cell::new,cells);
        assertEquals(5,cells.size());
    }

    @Test
    void setMoveFalse() {
        cellGroup.setMoveFalse();
        assertTrue(cellGroup.getCells().stream().allMatch(cellList->cellList.stream().noneMatch(Cell::isMoveIsDone)));
    }

    @Test
    void getCellTypeByPoint() {
        Point point = null;
        for(List<Cell> cellList:cellGroup.getCells()){
            for(Cell cell:cellList){
                if(cell.getCellRepresentation().equals(ConsoleRepresentation.PREY)){
                    point = cell.getOceanCoordinate();
                }
            }
        }
        assertEquals(ConsoleRepresentation.PREY,cellGroup.getCellTypeByPoint(Objects.requireNonNull(point)));
    }

}