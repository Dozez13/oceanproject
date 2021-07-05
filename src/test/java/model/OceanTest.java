package model;

import model.cell.CellGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OceanTest {

    private Ocean ocean;
    @BeforeEach
    public void initOcean(){
        CellGroup cellGroup = new CellGroup(1,2,3,5,10);
        ocean = new Ocean(cellGroup);
    }

    @Test
    void getCellGroup() {
        assertNotNull(ocean.getCellGroup());
    }

    @Test
    void setCellGroup() {
        CellGroup cellGroup1 = new CellGroup(1,2,3,5,10);
        ocean.setCellGroup(cellGroup1);
        assertEquals(cellGroup1,ocean.getCellGroup());
    }
}