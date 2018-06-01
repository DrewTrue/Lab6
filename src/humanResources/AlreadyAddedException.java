package humanResources;

public class AlreadyAddedException extends RuntimeException{
    public AlreadyAddedException(){
        super("Element already added!");
    }

    public AlreadyAddedException(String message){
        super(message);
    }

    public String getMessageException(){
        return super.getMessage();
    }
}
