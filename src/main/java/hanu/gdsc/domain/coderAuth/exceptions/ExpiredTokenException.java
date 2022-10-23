package hanu.gdsc.domain.coderAuth.exceptions;

import hanu.gdsc.domain.share.exceptions.BusinessLogicException;

public class ExpiredTokenException extends BusinessLogicException {

    public ExpiredTokenException() {
        super("Token is expired", "EXPIRED_TOKEN");
    }
}
