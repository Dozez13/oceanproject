package model.validator;


import model.cell.CellGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellGroupValidatorTest {
    private CellGroupValidator cellGroupValidator;
   @BeforeEach
   public void init(){
       cellGroupValidator = new CellGroupValidator();
   }
    @Test
    void validateTrue() {
       CellGroup cellGroup = new CellGroup.Builder()
               .setPredatorNumber(25)
               .setPreyNumber(150)
               .setObstaclesNumber(75)
               .setRowNum(25)
               .setColNum(70)
               .build();
       assertTrue(cellGroupValidator.validate(cellGroup));
    }
    @Test
    void validateFalse(){
        CellGroup cellGroup = new CellGroup.Builder()
                .setPredatorNumber(25)
                .setPreyNumber(150)
                .setObstaclesNumber(75)
                .setRowNum(26)
                .setColNum(70)
                .build();
        assertFalse(cellGroupValidator.validate(cellGroup));
    }


}