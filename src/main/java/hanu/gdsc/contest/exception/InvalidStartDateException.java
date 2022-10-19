package hanu.gdsc.contest.exception;

import hanu.gdsc.share.exceptions.BusinessLogicException;

public class InvalidStartDateException extends BusinessLogicException {
    public InvalidStartDateException(String message) {
        super(message, "INVALID_START_DATE");
    }
}
