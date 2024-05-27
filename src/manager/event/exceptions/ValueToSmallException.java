package manager.event.exceptions;

public class ValueToSmallException extends IllegalArgumentException{
    public ValueToSmallException(String message){
        super(message);
    }
}
