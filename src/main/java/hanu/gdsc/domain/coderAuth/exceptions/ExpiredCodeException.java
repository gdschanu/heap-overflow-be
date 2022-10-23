package hanu.gdsc.domain.coderAuth.exceptions;

import hanu.gdsc.domain.share.exceptions.BusinessLogicException;

public class ExpiredCodeException extends BusinessLogicException {
    public ExpiredCodeException(String message) {
        super(message, "EXPIRED_CODE");
    } 
}
