package constant;

public class Constant {
    private Constant(){
        throw new AssertionError("You can't instance this class");
    }
    public enum SliderLabel{
        COL_NUMBER_TEXT("Cols number is "),
        ROW_NUMBER_TEXT("Rows' number is "),
        PREDATOR_NUMBER_TEXT("Predators' number is"),
        PREY_NUMBER_TEXT("Preys' number is "),
        OBSTACLES_NUMBER_TEXT("Obstacles' number is "),
        ITERATION_NUMBER_TEXT("Iterations' number is ");
        private final String text;
        SliderLabel(String text){
            this.text = text;
        }

        public String getText(){
            return this.text;
        }
    }
    public static final int TIME_TO_FEED = 6;
    public static final int TIME_TO_REPRODUCE = 6;
    public static final int SIMULATION_HEIGHT=1000;
    public static final int SIMULATION_WIDTH=1000;

}
