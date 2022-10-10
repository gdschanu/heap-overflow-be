package hanu.gdsc.core_order.exceptions;

import hanu.gdsc.share.exceptions.BusinessLogicException;

public class InvalidStatusException extends BusinessLogicException {
    public InvalidStatusException(String message) {
        super(message, "INVALID_STATUS");
    }
}
