package manager.event.exceptions;

public class PastDateException extends IllegalArgumentException{
    public PastDateException(String errorMessage){
       super(errorMessage);
    }
}
