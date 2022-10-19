package hanu.gdsc.contest.exception;

import hanu.gdsc.share.exceptions.BusinessLogicException;

public class AlreadyJoinedException extends BusinessLogicException {
    public AlreadyJoinedException(String message) {
        super(message, "ALREADY_JOINED");
    }
}
