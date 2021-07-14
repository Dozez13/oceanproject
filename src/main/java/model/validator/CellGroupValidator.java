package model.validator;


import model.cell.CellGroup;


public class CellGroupValidator implements Validator<CellGroup> {

    @Override
    public boolean validate(CellGroup cellGroup) {

         return cellGroup.getTotalCells()>1&&cellGroup.getObstaclesNumber() <  cellGroup.getTotalCells() &&cellGroup.getObstaclesNumber()>0&&
                cellGroup.getPreyNumber() <  cellGroup.getTotalCells() && cellGroup.getPreyNumber()>0&&
                cellGroup.getPredatorNumber() <  cellGroup.getTotalCells() &&cellGroup.getPredatorNumber()>0&& cellGroup.getColNum() <= 70 &&
                cellGroup.getColNum() > 0 && cellGroup.getRowNum() <= 25 && cellGroup.getRowNum() > 0&&cellGroup.getPredatorNumber()+cellGroup.getPreyNumber()+cellGroup.getObstaclesNumber()<= cellGroup.getTotalCells();

    }
}
