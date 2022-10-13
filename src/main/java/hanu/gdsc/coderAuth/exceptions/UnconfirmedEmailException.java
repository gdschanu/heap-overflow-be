package hanu.gdsc.coderAuth.exceptions;

import hanu.gdsc.share.exceptions.BusinessLogicException;

public class UnconfirmedEmailException extends BusinessLogicException {
    public UnconfirmedEmailException() {
        super("You haven't confirm your email", "UNCONFIRMED_MAIL");
    }
}
