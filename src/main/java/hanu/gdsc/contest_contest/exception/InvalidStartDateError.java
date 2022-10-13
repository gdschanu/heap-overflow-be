package hanu.gdsc.contest_contest.exception;

import hanu.gdsc.share.exceptions.BusinessLogicException;

public class InvalidStartDateError extends BusinessLogicException {
    public InvalidStartDateError(String message) {
        super(message, "INVALID_START_DATE");
    }
}
