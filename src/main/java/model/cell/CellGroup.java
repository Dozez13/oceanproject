package model.cell;



import constant.Constant;
import exception.EntityException;
import model.ConsoleRepresentation;
import model.point.Point;
import model.validator.Validatable;
import model.validator.Validator;
import org.apache.commons.collections4.ListUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;


import java.util.stream.IntStream;


public class CellGroup implements Validatable<CellGroup> {
    private static final Logger LOGGER = LogManager.getLogger(CellGroup.class);
    private List<List<Cell>> cells;
    private Validator<CellGroup> cellGroupValidator;

    public CellGroup(int predatorNumber, int preyNumber, int obstaclesNumber, int rowNum, int colNum) {
        int totalCells = rowNum * colNum;
        int defaultCellsNumber = totalCells - (predatorNumber + preyNumber + obstaclesNumber);
        List<Cell> cellList = new ArrayList<>(totalCells);
        addCell(predatorNumber, cellList, new Predator(Constant.TIME_TO_REPRODUCE, Constant.TIME_TO_FEED));
        addCell(preyNumber, cellList, new Prey(Constant.TIME_TO_REPRODUCE));
        addCell(obstaclesNumber, cellList, new Obstacle());
        addCell(defaultCellsNumber, cellList, new Cell());
        Collections.shuffle(cellList);
        IntStream.range(0, totalCells).forEach(i -> cellList.get(i).setOceanCoordinate(new Point(i % colNum, i / colNum)));
        this.cells = ListUtils.partition(cellList, colNum);

        LOGGER.info("CellGroup is created. Predators number : {}, prey's number : {}, obstacles' number : {}",predatorNumber,preyNumber,obstaclesNumber);
    }

    public CellGroup(List<List<Cell>> cells) {
        this.cells = new ArrayList<>(cells);
    }

    public void addCell(int cellNumber, List<Cell> cells, Cell cell) {
        for (int i = 0; i < cellNumber; i++) {
            cells.add(new Cell(cell));
        }
    }

    public void addCell(int cellNumber, List<Cell> cells, Prey cell) {
        for (int i = 0; i < cellNumber; i++) {
            cells.add(new Prey(cell));
        }
    }

    public void addCell(int cellNumber, List<Cell> cells, Obstacle cell) {
        for (int i = 0; i < cellNumber; i++) {
            cells.add(new Obstacle(cell));
        }
    }

    public void addCell(int cellNumber, List<Cell> cells, Predator cell) {
        for (int i = 0; i < cellNumber; i++) {
            cells.add(new Predator(cell));
        }
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
        createCell(prevPoint);
        if (prey.getTimeToReproduce() == 0) {
            destroyCell(prevPoint);
            createPrey(prevPoint);
            prey.setTimeToReproduce(Constant.TIME_TO_REPRODUCE);
        }
        prey.setMoveIsDone(true);
    }

    public void processCell(Predator predator) {
        if (predator.isMoveIsDone()) return;
        predator.setTimeToReproduce(predator.getTimeToReproduce() - 1);
        Map.Entry<Point, ConsoleRepresentation> point = findPoint(predator);
        if (point == null) return;
        Point prevPoint = predator.getOceanCoordinate();
        moveCell(predator.getOceanCoordinate(), point.getKey());
        createCell(prevPoint);
        predator.setTimeToFeed(predator.getTimeToFeed() - 1);
        if (point.getValue().equals(ConsoleRepresentation.PREY)) {
            predator.setTimeToFeed(Constant.TIME_TO_FEED);
        }
        if (predator.getTimeToFeed() == 0) {
            destroyCell(predator.getOceanCoordinate());
            createCell(predator.getOceanCoordinate());
        }
        if (predator.getTimeToReproduce() == 0 && predator.getTimeToFeed() != 0) {
            destroyCell(prevPoint);
            createPredator(prevPoint);
            predator.setTimeToReproduce(Constant.TIME_TO_REPRODUCE);
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
    public void validate() throws EntityException {
        this.cellGroupValidator.validate(this);
    }
}
