package model.validator;


import model.cell.CellGroup;


public class CellGroupValidator implements Validator<CellGroup> {

    @Override
    public boolean validate(CellGroup cellGroup) {
        int totalCellsNumber = cellGroup.getColNum() * cellGroup.getRowNum();
        return cellGroup.getObstaclesNumber() <= totalCellsNumber &&
                cellGroup.getPreyNumber() <= totalCellsNumber &&
                cellGroup.getPredatorNumber() <= totalCellsNumber && cellGroup.getColNum() <= 70 &&
                cellGroup.getColNum() >= 0 && cellGroup.getRowNum() <= 25 && cellGroup.getRowNum() >= 0 &&
                cellGroup.getObstaclesNumber() != totalCellsNumber && cellGroup.getPreyNumber() != totalCellsNumber && cellGroup.getPredatorNumber() != totalCellsNumber;

    }
}
