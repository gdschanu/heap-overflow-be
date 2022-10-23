package hanu.gdsc.domain.contest.exception;

import hanu.gdsc.domain.share.exceptions.BusinessLogicException;

public class InvalidOrdinalException extends BusinessLogicException {
    public InvalidOrdinalException() {
        super("Invalid ordinal", "INVALID_ORDINAL");
    }
}
