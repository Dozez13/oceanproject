package exception;

public class CellGroupExceptionContainer {
    private CellGroupExceptionContainer(){
        throw new AssertionError("Exception Container can't be instanced");
    }
    public static class MatrixSizeInvalidException extends BaseCellGroupException{
        public MatrixSizeInvalidException(String message){
            super(message);
        }
    }
    public static class MatrixConsistOfOneTypeException extends BaseCellGroupException{
        public MatrixConsistOfOneTypeException(String message){
            super(message);
        }
    }
}
