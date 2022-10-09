package hanu.gdsc.coderAuth.exceptions;

import hanu.gdsc.share.exceptions.BusinessLogicException;

public class WrongCodeException extends BusinessLogicException {
    public WrongCodeException() {
        super("Your code is wrong", "WRONG_CODE");
    }
    
}
