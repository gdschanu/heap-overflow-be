package hanu.gdsc.domain.contest.exception;

import hanu.gdsc.domain.share.exceptions.BusinessLogicException;

public class ContestEndedException extends BusinessLogicException {
    public ContestEndedException() {
        super("Contest ended.", "CONTEST_ENDED");
    }
}
