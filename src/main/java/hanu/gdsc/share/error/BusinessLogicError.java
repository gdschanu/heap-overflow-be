package hanu.gdsc.share.error;

public class BusinessLogicError extends Error {
    public String code;

    public BusinessLogicError(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
