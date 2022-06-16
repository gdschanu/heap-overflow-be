package hanu.gdsc.contestSubdomain.contestContext.errors;

import hanu.gdsc.share.error.BusinessLogicError;

public class InvalidStartDateError extends BusinessLogicError {
    public InvalidStartDateError(String message) {
        super(message, "INVALID_START_DATE");
    }
}
