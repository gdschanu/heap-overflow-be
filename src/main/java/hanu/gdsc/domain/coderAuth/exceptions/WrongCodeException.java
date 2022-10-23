package hanu.gdsc.domain.coderAuth.exceptions;

import hanu.gdsc.domain.share.exceptions.BusinessLogicException;

public class WrongCodeException extends BusinessLogicException {
    public WrongCodeException() {
        super("Your code is wrong", "WRONG_CODE");
    }
    
}
