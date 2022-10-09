package hanu.gdsc.core_order.exceptions;

import hanu.gdsc.share.exceptions.BusinessLogicException;

public class InsufficientQuantityException extends BusinessLogicException {
    public InsufficientQuantityException(String message) {
        super(message, "INSUFFICIENT_QUANTITY");
    }
}
