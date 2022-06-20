package hanu.gdsc.share.error;

public class DuplicatedError extends BusinessLogicError {
    public DuplicatedError(String message) {
        super(message, "DUPLICATED");
    }
}
