package hanu.gdsc.share.error;

public class NotFoundError extends BusinessLogicError {
    public NotFoundError(String message) {
        super(message, "NOT_FOUND");
    }
}
