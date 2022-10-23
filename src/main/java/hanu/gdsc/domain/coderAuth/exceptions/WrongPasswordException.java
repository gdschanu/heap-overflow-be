package hanu.gdsc.domain.coderAuth.exceptions;

import hanu.gdsc.domain.share.exceptions.BusinessLogicException;

public class WrongPasswordException extends BusinessLogicException {
    public WrongPasswordException() {
        super("Your password is wrong", "WRONG_PASSWORD");
    }
}
