package hanu.gdsc.coderAuth.exceptions;

import hanu.gdsc.share.exceptions.BusinessLogicException;

public class WrongPasswordException extends BusinessLogicException {
    public WrongPasswordException() {
        super("Your password is wrong", "WRONG_PASSWORD");
    }
}
