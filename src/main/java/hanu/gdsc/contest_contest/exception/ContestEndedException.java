package hanu.gdsc.contest_contest.exception;

import hanu.gdsc.share.exceptions.BusinessLogicException;

public class ContestEndedException extends BusinessLogicException {
    public ContestEndedException() {
        super("Contest ended.", "CONTEST_ENDED");
    }
}
