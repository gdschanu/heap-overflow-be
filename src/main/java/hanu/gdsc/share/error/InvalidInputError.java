package hanu.gdsc.share.error;

public class InvalidInputError extends BusinessLogicError {
    public InvalidInputError(String message) {
        super(message, "INVALID_INPUT");
    }
}
