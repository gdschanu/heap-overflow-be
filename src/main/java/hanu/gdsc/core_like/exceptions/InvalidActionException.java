package hanu.gdsc.core_like.exceptions;

import hanu.gdsc.share.exceptions.BusinessLogicException;

public class InvalidActionException extends BusinessLogicException {
    public InvalidActionException(String message) {
        super(message, "INVALID_ACTION");
    }
}
