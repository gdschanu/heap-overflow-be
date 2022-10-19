package hanu.gdsc.contest.exception;

import hanu.gdsc.share.exceptions.BusinessLogicException;

public class InvalidOrdinalException extends BusinessLogicException {
    public InvalidOrdinalException() {
        super("Invalid ordinal", "INVALID_ORDINAL");
    }
}
