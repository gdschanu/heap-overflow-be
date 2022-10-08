package hanu.gdsc.contest_contest.errors;

import hanu.gdsc.share.error.BusinessLogicError;

public class ContestEndedError extends BusinessLogicError {
    public ContestEndedError() {
        super("Contest ended.", "CONTEST_ENDED");
    }
}
