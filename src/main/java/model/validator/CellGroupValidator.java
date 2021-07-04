package model.validator;

import exception.BaseCellGroupException;
import exception.CellGroupExceptionContainer;
import model.ConsoleRepresentation;
import model.cell.Cell;
import model.cell.CellGroup;


import java.util.Arrays;
import java.util.List;

public class CellGroupValidator implements Validator<CellGroup>{
    private boolean matrixConsistsOneType( List<List<Cell>> cells,ConsoleRepresentation type){
        int totalSize =  cells.size()*cells.get(0).size();
        return cells.stream().mapToInt(cellList -> (int)cellList.stream().filter(cell -> cell.getCellRepresentation().equals(type)).count()).sum() == totalSize;
    }

    @Override
    public void validate(CellGroup cellGroup) throws BaseCellGroupException {
        List<List<Cell>> cells = cellGroup.getCells();
        if(cells.size()>25||cells.isEmpty()){
            throw new CellGroupExceptionContainer.MatrixSizeInvalidException("Rows value Is invalid");
        }
        if(cells.stream().anyMatch(cellList->cellList.size()>70||cellList.isEmpty())){
            throw new CellGroupExceptionContainer.MatrixSizeInvalidException("Column value Is invalid");
        }
        if(Arrays.stream(ConsoleRepresentation.values()).anyMatch(representation->matrixConsistsOneType(cells, representation))){
            throw new CellGroupExceptionContainer.MatrixConsistOfOneTypeException("Matrix only consist of one type");
        }


    }
}
