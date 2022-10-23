package hanu.gdsc.domain.core_order.exceptions;

import hanu.gdsc.domain.share.exceptions.BusinessLogicException;

public class InvalidStatusException extends BusinessLogicException {
    public InvalidStatusException(String message) {
        super(message, "INVALID_STATUS");
    }
}
