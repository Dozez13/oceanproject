package model;

public enum ConsoleRepresentation {
    CELL("-"),OBSTACLE("#"),PREDATOR("P"),PREY("F");
    private final String representation;
    ConsoleRepresentation(String representation){
        this.representation = representation;
    }
    public String getRepresentation() {
        return representation;
    }
}
