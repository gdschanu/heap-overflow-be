package hanu.gdsc.share.error;

public class UnauthorizedError extends BusinessLogicError {
    public UnauthorizedError() {
        super("Unauthorized", "UNAUTHORIZED");
    }

    public UnauthorizedError(String message) {
        super(message, "UNAUTHORIZED");
    }
}
