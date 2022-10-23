package hanu.gdsc.domain.coderAuth.exceptions;

import hanu.gdsc.domain.share.exceptions.BusinessLogicException;

public class UnsentCodeException extends BusinessLogicException {
    public UnsentCodeException() {
        super("You haven't sent email yet", "UNSENT_CODE");
    }
}
