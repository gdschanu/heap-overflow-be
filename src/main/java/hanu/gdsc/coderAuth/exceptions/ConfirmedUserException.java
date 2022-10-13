package hanu.gdsc.coderAuth.exceptions;

import hanu.gdsc.share.exceptions.BusinessLogicException;

public class ConfirmedUserException extends BusinessLogicException {
    public ConfirmedUserException() {
        super("You already confirm mail registration", "CONFIRMED_USER");
    }
}
