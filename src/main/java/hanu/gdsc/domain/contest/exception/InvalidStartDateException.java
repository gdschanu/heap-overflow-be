package hanu.gdsc.domain.contest.exception;

import hanu.gdsc.domain.share.exceptions.BusinessLogicException;

public class InvalidStartDateException extends BusinessLogicException {
    public InvalidStartDateException(String message) {
        super(message, "INVALID_START_DATE");
    }
}
