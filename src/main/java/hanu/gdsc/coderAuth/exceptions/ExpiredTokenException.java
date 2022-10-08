package hanu.gdsc.coderAuth.exceptions;

import hanu.gdsc.share.exceptions.BusinessLogicException;

public class ExpiredTokenException extends BusinessLogicException {

    public ExpiredTokenException() {
        super("Token is expired", "EXPIRED_TOKEN");
    }
}
