package hanu.gdsc.coderAuth.exceptions;

import hanu.gdsc.share.exceptions.BusinessLogicException;

public class ExpiredCodeException extends BusinessLogicException {
    public ExpiredCodeException(String message) {
        super(message, "EXPIRED_CODE");
    } 
}
