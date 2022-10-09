package hanu.gdsc.coderAuth.exceptions;

import hanu.gdsc.share.exceptions.BusinessLogicException;

public class UnsentCodeException extends BusinessLogicException {
    public UnsentCodeException() {
        super("You haven't sent email yet", "UNSENT_CODE");
    }
}
