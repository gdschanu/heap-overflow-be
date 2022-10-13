package hanu.gdsc.core_order.exceptions;

import hanu.gdsc.share.exceptions.BusinessLogicException;

public class InsufficientBudgetException extends BusinessLogicException {
    public InsufficientBudgetException(String message) {
        super(message, "INSUFFICIENT_BUDGET");
    }
}