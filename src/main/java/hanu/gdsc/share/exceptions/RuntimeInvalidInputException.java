package hanu.gdsc.share.exceptions;

public class RuntimeInvalidInputException extends RuntimeBusinessLogicException {
    public RuntimeInvalidInputException(String message) {
        super(message, "INVALID_INPUT");
    }
}
