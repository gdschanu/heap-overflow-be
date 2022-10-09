package hanu.gdsc.contest_contest.exception;

import hanu.gdsc.share.exceptions.BusinessLogicException;

public class InvalidOrdinalError extends BusinessLogicException {
    public InvalidOrdinalError() {
        super("Invalid ordinal", "INVALID_ORDINAL");
    }
}
