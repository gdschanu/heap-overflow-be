package hanu.gdsc.domain.contest.exception;

import hanu.gdsc.domain.share.exceptions.BusinessLogicException;

public class AlreadyJoinedException extends BusinessLogicException {
    public AlreadyJoinedException(String message) {
        super(message, "ALREADY_JOINED");
    }
}
