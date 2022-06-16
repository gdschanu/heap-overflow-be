package hanu.gdsc.contestSubdomain.contestContext.errors;

import hanu.gdsc.share.error.BusinessLogicError;

public class InvalidOrdinalError extends BusinessLogicError {
    public InvalidOrdinalError() {
        super("Invalid ordinal", "INVALID_ORDINAL");
    }
}
