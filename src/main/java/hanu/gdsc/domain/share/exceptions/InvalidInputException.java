package hanu.gdsc.domain.share.exceptions;

public class InvalidInputException extends BusinessLogicException {
    public InvalidInputException(String message) {
        super(message, "INVALID_INPUT");
    }
}
