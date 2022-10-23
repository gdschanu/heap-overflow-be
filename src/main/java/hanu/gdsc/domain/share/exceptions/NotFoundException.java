package hanu.gdsc.domain.share.exceptions;

public class NotFoundException extends BusinessLogicException {
    public NotFoundException(String message) {
        super(message, "NOT_FOUND");
    }
}
