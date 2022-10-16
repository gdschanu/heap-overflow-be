package hanu.gdsc.contest_contest.exception;

import hanu.gdsc.share.exceptions.BusinessLogicException;

public class AlreadyJoinedException extends BusinessLogicException {
    public AlreadyJoinedException(String message) {
        super(message, "ALREADY_JOINED");
    }
}
