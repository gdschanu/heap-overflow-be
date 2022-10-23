package hanu.gdsc.domain.core_like.exceptions;

import hanu.gdsc.domain.share.exceptions.BusinessLogicException;

public class InvalidActionException extends BusinessLogicException {
    public InvalidActionException(String message) {
        super(message, "INVALID_ACTION");
    }
}
