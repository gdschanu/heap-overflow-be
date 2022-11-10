package hanu.gdsc.domain.contest.exception;

import hanu.gdsc.domain.share.exceptions.BusinessLogicException;

public class ContestNotStartedException extends BusinessLogicException {
    public ContestNotStartedException() {
        super("Contest not started.", "CONTEST_NOT_STARTED");
    }
}
