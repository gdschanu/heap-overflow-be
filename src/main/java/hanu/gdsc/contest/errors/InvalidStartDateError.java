package hanu.gdsc.contest.errors;

import hanu.gdsc.share.error.BusinessLogicError;

public class InvalidStartDateError extends BusinessLogicError {
    public InvalidStartDateError(String message) {
        super(message, "INVALID_START_DATE");
    }
}
