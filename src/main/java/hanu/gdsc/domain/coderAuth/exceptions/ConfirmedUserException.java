package hanu.gdsc.domain.coderAuth.exceptions;

import hanu.gdsc.domain.share.exceptions.BusinessLogicException;

public class ConfirmedUserException extends BusinessLogicException {
    public ConfirmedUserException() {
        super("You already confirm mail registration", "CONFIRMED_USER");
    }
}
