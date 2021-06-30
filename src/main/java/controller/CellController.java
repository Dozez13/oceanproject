package controller;

import model.Ocean;
import model.cell.*;
import model.point.Point;
import org.apache.commons.collections4.ListUtils;
import view.OceanPrinter;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class CellController {
    private Ocean ocean;
    private OceanPrinter view;
    private int iterationNumber;

    public CellController(OceanPrinter view){
        this.view = view;
    }

    public Ocean getOcean() {
        return ocean;
    }

    public void setOcean(Ocean ocean) {
        this.ocean = ocean;
    }

    public void initOcean(int predatorNumber, int preyNumber, int obstaclesNumber, int rowNum, int colNum,int iterationNumber){
        if(rowNum>25 ||colNum>70)return;
        this.iterationNumber = iterationNumber;
        int totalCells = rowNum*colNum;
        List<Cell> cells = IntStream.range(0,totalCells).mapToObj(i->{
            if(i<predatorNumber)return new Predator(4,4);
            if(i<predatorNumber+preyNumber) return new Prey(4);
            if(i<predatorNumber+preyNumber+obstaclesNumber)return new Obstacles();
            return new Cell();
        }).collect(Collectors.toList());
        Collections.shuffle(cells);
        IntStream.range(0,totalCells).forEach(i-> cells.get(i).setOceanCoordinate(new Point(i/colNum,i > colNum - 1 ? i % colNum : i)));
        ocean = new Ocean(new CellGroup(ListUtils.partition(cells,colNum)));
    }
    public void process(){
        CellGroup cellGroup = ocean.getCellGroup();
        List<List<Cell>> cells = cellGroup.getCells();
        cells.forEach(cellList-> cellList.forEach(cell->{
            switch (cell.getRepresentation()){
                case "F":{
                    Prey prey = (Prey)cell;
                    processCell(prey);
                    break;
                }
                case "P":{
                    Predator predator = (Predator)cell;
                    processCell(predator);
                    break;
                }
            }
        }));
    }
    public void run(){
        for(int i=0;i<iterationNumber;i++){
             process();
        }
    }
    public void processCell(Predator processedPredator){
        if(processedPredator.getTimeToFeed()==0)destroyCell(processedPredator.getOceanCoordinate());
        processedPredator.setTimeToReproduce(processedPredator.getTimeToReproduce()-1);
        Point point = processedPredator.getOceanCoordinate();
        if(moveCellRandom(point)&&processedPredator.getTimeToReproduce()==0){
            createPrey(point);
        }
    }
    public void processCell(Prey processedPrey){
        processedPrey.setTimeToReproduce(processedPrey.getTimeToReproduce()-1);
        Point point = processedPrey.getOceanCoordinate();
        if(moveCellRandom(point)&&processedPrey.getTimeToReproduce()==0){
            createPrey(point);
        }
    }
    public boolean moveCellRandom(Point from){
        return true;
    }
    public void createPredator(Point place){

    }
    public void createPrey(Point place){

    }
    public void destroyCell(Point place){

    }


}
