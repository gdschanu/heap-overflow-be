package hanu.gdsc.share.error;

public class NotFound extends BusinessLogicError {
    public NotFound(String message) {
        super(message, "NOT_FOUND");
    }
}
