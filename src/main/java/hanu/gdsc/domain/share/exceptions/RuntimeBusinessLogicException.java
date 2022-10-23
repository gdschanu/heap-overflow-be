package hanu.gdsc.domain.share.exceptions;

public class RuntimeBusinessLogicException extends RuntimeException {
    public String code;

    public RuntimeBusinessLogicException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
