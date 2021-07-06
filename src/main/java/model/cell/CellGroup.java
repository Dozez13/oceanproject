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
         addCell(predatorNumber,()->new Predator(Constant.TIME_TO_REPRODUCE, Constant.TIME_TO_FEED),cellList);
         addCell(preyNumber,()->new Prey(Constant.TIME_TO_REPRODUCE),cellList);
         addCell(obstaclesNumber, Obstacle::new,cellList);
         addCell(defaultCellsNumber, Cell::new,cellList);
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
        for (List<Cell> cell : cells) {
            for (Cell value : cell) {
                if (value.getCellRepresentation().equals(ConsoleRepresentation.PREY)) {
                    LOGGER.info("Prey before processing : {}",value);
                    processCell((Prey) value);
                    LOGGER.info("Prey after processing : {}",value);
                }
                if (value.getCellRepresentation().equals(ConsoleRepresentation.PREDATOR)) {
                    LOGGER.info("Predator before processing : {}",value);
                    processCell((Predator) value);
                    LOGGER.info("Predator after processing : {}",value);
                }
            }
        }
    }

    public void processCell(Prey prey) {
        if (prey.isMoveIsDone()) return;
        prey.setTimeToReproduce(prey.getTimeToReproduce() - 1);
        Point pointTo = findPoint(prey);
        if (pointTo == null) return;
        Point prevPoint = prey.getOceanCoordinate();
        moveCell(prey.getOceanCoordinate(), pointTo);
        if (prey.getTimeToReproduce() == 0) {
            destroyCell(prevPoint);
            createPrey(prevPoint);
            prey.setTimeToReproduce(Constant.TIME_TO_REPRODUCE);
            setPreyNumber(getPreyNumber()+1);
        }else {
            createCell(prevPoint);
        }
        prey.setMoveIsDone(true);
    }

    public void processCell(Predator predator) {
        if (predator.isMoveIsDone()) return;
        predator.setTimeToFeed(predator.getTimeToFeed() - 1);
        if (predator.getTimeToFeed() == 0) {
            destroyCell(predator.getOceanCoordinate());
            createCell(predator.getOceanCoordinate());
            setPredatorNumber(getPredatorNumber()-1);
            return;
        }
        predator.setTimeToReproduce(predator.getTimeToReproduce() - 1);
        Map.Entry<Point, ConsoleRepresentation> point = findPoint(predator);
        if (point == null) return;
        Point prevPoint = predator.getOceanCoordinate();
        moveCell(predator.getOceanCoordinate(), point.getKey());
        if (point.getValue().equals(ConsoleRepresentation.PREY)) {
            predator.setTimeToFeed(Constant.TIME_TO_FEED);
        }

        if (predator.getTimeToReproduce() == 0 && predator.getTimeToFeed() != 0) {
            destroyCell(prevPoint);
            createPredator(prevPoint);
            predator.setTimeToReproduce(Constant.TIME_TO_REPRODUCE);
            setPredatorNumber(getPredatorNumber()+1);
        }else {
            createCell(prevPoint);
        }
        predator.setMoveIsDone(true);
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
        points.removeIf(point -> !getCellTypeByPoint(point).equals(ConsoleRepresentation.CELL));
        if (points.isEmpty()) {
            return null;
        }
        return points.stream().findAny().orElse(null);

    }

    public ConsoleRepresentation getCellTypeByPoint(Point point) {
        return cells.get(point.getY()).get(point.getX()).getCellRepresentation();

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
        Cell cell = new Cell();
        cell.setOceanCoordinate(place);
        cells.get(place.getY()).set(place.getX(), cell);
    }

    public void createPredator(Point place) {
        Predator predator = new Predator(Constant.TIME_TO_REPRODUCE, Constant.TIME_TO_FEED);
        predator.setOceanCoordinate(place);
        cells.get(place.getY()).set(place.getX(), predator);
    }

    public void createPrey(Point place) {
        Prey prey = new Prey(Constant.TIME_TO_REPRODUCE);
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

    @Override
    public void setValidator(Validator<CellGroup> validator) {
        this.cellGroupValidator = validator;
    }

    @Override
    public boolean validate(){
       return this.cellGroupValidator.validate(this);
    }
}
