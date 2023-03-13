package hanu.gdsc.domain.contest.exception;

import hanu.gdsc.domain.share.exceptions.BusinessLogicException;

public class NotJoinedException extends BusinessLogicException {
    public NotJoinedException(String message) {
        super(message, "NOT_JOINED");
    }
}
