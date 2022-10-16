package hanu.gdsc.contest_contest.exception;

import hanu.gdsc.share.exceptions.BusinessLogicException;

public class InvalidOrdinalException extends BusinessLogicException {
    public InvalidOrdinalException() {
        super("Invalid ordinal", "INVALID_ORDINAL");
    }
}
