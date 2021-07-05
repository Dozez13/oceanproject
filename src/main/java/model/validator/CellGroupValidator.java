package model.validator;

import exception.BaseCellGroupException;
import exception.CellGroupExceptionContainer;
import model.cell.CellGroup;




public class CellGroupValidator implements Validator<CellGroup>{

    @Override
    public void validate(CellGroup cellGroup) throws BaseCellGroupException {
        int totalCellsNumber = cellGroup.getColNum()* cellGroup.getRowNum();
        if(cellGroup.getObstaclesNumber()>totalCellsNumber||cellGroup.getPreyNumber()>totalCellsNumber||cellGroup.getPredatorNumber()>totalCellsNumber){
            throw new CellGroupExceptionContainer.MatrixSizeInvalidException("Number of cols is not acceptable");
        }
        if(cellGroup.getColNum()>70||cellGroup.getColNum()<0){
            throw new CellGroupExceptionContainer.MatrixSizeInvalidException("Number of cols is not acceptable");
        }
        if(cellGroup.getRowNum()>25||cellGroup.getRowNum()<0){
            throw new CellGroupExceptionContainer.MatrixSizeInvalidException("Number of rows is not acceptable");
        }
        if(cellGroup.getObstaclesNumber()==totalCellsNumber||cellGroup.getPreyNumber()==totalCellsNumber||cellGroup.getPredatorNumber()==totalCellsNumber){
            throw new CellGroupExceptionContainer.MatrixConsistOfOneTypeException("Cells matrix consist only of one cell type");
        }


    }
}
