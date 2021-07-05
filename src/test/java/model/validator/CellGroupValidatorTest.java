package model.validator;

import exception.BaseCellGroupException;
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
    void validateWithoutException() {
       CellGroup cellGroup = new CellGroup(1,2,3,5,10);
       assertDoesNotThrow(()->cellGroupValidator.validate(cellGroup));
    }
    @Test
    void validateThrowsMatrixSizeException(){
        CellGroup cellGroup = new CellGroup(1,2,3,26,10);
        assertThrows(BaseCellGroupException.class,()->cellGroupValidator.validate(cellGroup));
    }


}