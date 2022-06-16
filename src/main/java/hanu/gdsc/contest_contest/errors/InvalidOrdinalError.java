package hanu.gdsc.contest_contest.errors;

import hanu.gdsc.share.error.BusinessLogicError;

public class InvalidOrdinalError extends BusinessLogicError {
    public InvalidOrdinalError() {
        super("Invalid ordinal", "INVALID_ORDINAL");
    }
}
