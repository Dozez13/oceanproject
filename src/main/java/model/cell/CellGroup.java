package model.cell;



import constant.Constant;
import model.ConsoleRepresentation;
import model.point.Point;
import model.validator.Validatable;
import model.validator.Validator;
import org.apache.commons.collections4.ListUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;


import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class CellGroup implements Validatable<CellGroup> {
    private static final Logger LOGGER = LogManager.getLogger(CellGroup.class);
    private List<List<Cell>> cells;
    private int predatorNumber;
    private int preyNumber;
    private int obstaclesNumber;
    private int rowNum;
    private int colNum;
    private Validator<CellGroup> cellGroupValidator;
    public static class Builder{
        private int predatorNumber;
        private int preyNumber;
        private int obstaclesNumber;
        private int rowNum;
        private int colNum;
        public int getPredatorNumber() {
            return predatorNumber;
        }

        public Builder setPredatorNumber(int predatorNumber) {
            this.predatorNumber = predatorNumber;
            return this;
        }

        public int getPreyNumber() {
            return preyNumber;
        }

        public Builder setPreyNumber(int preyNumber) {
            this.preyNumber = preyNumber;
            return this;
        }

        public int getObstaclesNumber() {
            return obstaclesNumber;
        }

        public Builder setObstaclesNumber(int obstaclesNumber) {
            this.obstaclesNumber = obstaclesNumber;
            return this;
        }

        public int getRowNum() {
            return rowNum;
        }

        public Builder setRowNum(int rowNum) {
            this.rowNum = rowNum;
            return this;
        }

        public int getColNum() {
            return colNum;
        }

        public Builder setColNum(int colNum) {
            this.colNum = colNum;
            return this;
        }

        public CellGroup build(){
            return new CellGroup(this);
        }
    }
    public CellGroup(Builder builder){
        this.predatorNumber = builder.getPredatorNumber();
        this.preyNumber = builder.getPreyNumber();
        this.obstaclesNumber = builder.getObstaclesNumber();
        this.rowNum = builder.getRowNum();
        this.colNum = builder.getColNum();
        LOGGER.info("CellGroup is created. Predators number : {}, prey's number : {}, obstacles' number : {}",predatorNumber,preyNumber,obstaclesNumber);
    }
     public void populateCellList(){
         int totalCells = rowNum * colNum;
         int defaultCellsNumber = totalCells - (predatorNumber + preyNumber + obstaclesNumber);
         List<Cell> cellList = new ArrayList<>(totalCells);
         addCell(predatorNumber,()->new Predator(this,Constant.TIME_TO_REPRODUCE, Constant.TIME_TO_FEED),cellList);
         addCell(preyNumber,()->new Prey(this,Constant.TIME_TO_REPRODUCE),cellList);
         addCell(obstaclesNumber, ()->new Obstacle(this),cellList);
         addCell(defaultCellsNumber, ()->new Cell(this),cellList);
         Collections.shuffle(cellList);
         IntStream.range(0, totalCells).forEach(i -> cellList.get(i).setOceanCoordinate(new Point(i % colNum, i / colNum)));
         this.cells = ListUtils.partition(cellList, colNum);

     }

    public CellGroup(List<List<Cell>> cells) {
        this.cells = new ArrayList<>(cells);
    }
    public <T>void addCell(int cellNumber, Supplier<T> supplier,List<?super T> cells){

        Stream.generate(supplier).limit(cellNumber).collect(Collectors.toCollection(()->cells));
    }
        public void setMoveFalse(){
        cells.forEach(cellList->cellList.forEach(cell -> cell.setMoveIsDone(false)));
        }
    public void processCells() {
        cells.forEach(cellList->cellList.forEach(cell->{
            LOGGER.info("Cell before processing : {}",cell);
            cell.process();
            LOGGER.info("Cell after processing : {}",cell);
        }));
    }

    public Point findPoint(Prey prey) {
        List<Point> points = new ArrayList<>(4);
        if (prey.getOceanCoordinate().getX() - 1 >= 0) {
            points.add(new Point(prey.getOceanCoordinate().getX() - 1, prey.getOceanCoordinate().getY()));
        }
        if (prey.getOceanCoordinate().getX() + 1 < cells.get(0).size()) {
            points.add(new Point(prey.getOceanCoordinate().getX() + 1, prey.getOceanCoordinate().getY()));
        }
        if (prey.getOceanCoordinate().getY() - 1 >= 0) {
            points.add(new Point(prey.getOceanCoordinate().getX(), prey.getOceanCoordinate().getY() - 1));
        }
        if (prey.getOceanCoordinate().getY() + 1 < cells.size()) {
            points.add(new Point(prey.getOceanCoordinate().getX(), prey.getOceanCoordinate().getY() + 1));
        }
        if (points.isEmpty()) {
            return null;
        }
        points.removeIf(point -> !getCellTypeByPoint(point).equals(ConsoleRepresentation.CELL));
        return points.stream().findAny().orElse(null);

    }

    public ConsoleRepresentation getCellTypeByPoint(Point point) {
        return cells.get(point.getY()).get(point.getX()).getCellConsoleRepresentation();

    }

    public Map.Entry<Point, ConsoleRepresentation> findPoint(Predator predator) {
        Map<Point, ConsoleRepresentation> pointMap = new HashMap<>(4);
        if (predator.getOceanCoordinate().getX() - 1 >= 0) {
            Point point = new Point(predator.getOceanCoordinate().getX() - 1, predator.getOceanCoordinate().getY());
            pointMap.put(point, getCellTypeByPoint(point));
        }
        if (predator.getOceanCoordinate().getX() + 1 < cells.get(0).size()) {
            Point point = new Point(predator.getOceanCoordinate().getX() + 1, predator.getOceanCoordinate().getY());
            pointMap.put(point, getCellTypeByPoint(point));
        }
        if (predator.getOceanCoordinate().getY() - 1 >= 0) {
            Point point = new Point(predator.getOceanCoordinate().getX(), predator.getOceanCoordinate().getY() - 1);
            pointMap.put(point, getCellTypeByPoint(point));
        }
        if (predator.getOceanCoordinate().getY() + 1 < cells.size()) {
            Point point = new Point(predator.getOceanCoordinate().getX(), predator.getOceanCoordinate().getY() + 1);
            pointMap.put(point, getCellTypeByPoint(point));
        }
        if (pointMap.isEmpty()) {
            return null;
        }
        pointMap.entrySet().removeIf(point -> point.getValue().equals(ConsoleRepresentation.PREDATOR) || point.getValue().equals(ConsoleRepresentation.OBSTACLE));
        Optional<Map.Entry<Point, ConsoleRepresentation>> point = pointMap.entrySet().stream().filter(element -> element.getValue().equals(ConsoleRepresentation.PREY)).findAny();
        return point.orElseGet(() -> pointMap.entrySet().stream().findAny().orElse(null));

    }

    public void moveCell(Point from, Point to) {
        Cell movedCell1 = cells.get(from.getY()).get(from.getX());
        movedCell1.setOceanCoordinate(to);
        cells.get(to.getY()).set(to.getX(), movedCell1);
    }

    public void createCell(Point place) {
        Cell cell = new Cell(this);
        cell.setOceanCoordinate(place);
        cells.get(place.getY()).set(place.getX(), cell);
    }

    public void createPredator(Point place) {
        Predator predator = new Predator(this,Constant.TIME_TO_REPRODUCE, Constant.TIME_TO_FEED);
        predator.setOceanCoordinate(place);
        cells.get(place.getY()).set(place.getX(), predator);
    }

    public void createPrey(Point place) {
        Prey prey = new Prey(this,Constant.TIME_TO_REPRODUCE);
        prey.setOceanCoordinate(place);
        cells.get(place.getY()).set(place.getX(), prey);

    }

    public int getPredatorNumber() {
        return predatorNumber;
    }

    public void setPredatorNumber(int predatorNumber) {
        this.predatorNumber = predatorNumber;
    }

    public int getPreyNumber() {
        return preyNumber;
    }

    public void setPreyNumber(int preyNumber) {
        this.preyNumber = preyNumber;
    }

    public int getObstaclesNumber() {
        return obstaclesNumber;
    }

    public void setObstaclesNumber(int obstaclesNumber) {
        this.obstaclesNumber = obstaclesNumber;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    public void destroyCell(Point place) {
        cells.get(place.getY()).set(place.getX(), null);
    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    public void setCells(List<List<Cell>> cells) {
        this.cells = cells;
    }

    public int getTotalCells(){
        return getRowNum()*getColNum();
    }
    @Override
    public void setValidator(Validator<CellGroup> validator) {
        this.cellGroupValidator = validator;
    }

    @Override
    public boolean validate(){
       return this.cellGroupValidator.validate(this);
    }
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder(getCells().size());
        getCells().forEach(el -> {
            el.forEach(eld->result.append(eld.getCellConsoleRepresentation().getRepresentation()));
            result.append(System.lineSeparator());
        });
        return result.toString();
    }
}
