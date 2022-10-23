package hanu.gdsc.domain.core_order.exceptions;

import hanu.gdsc.domain.share.exceptions.BusinessLogicException;

public class InsufficientBudgetException extends BusinessLogicException {
    public InsufficientBudgetException(String message) {
        super(message, "INSUFFICIENT_BUDGET");
    }
}