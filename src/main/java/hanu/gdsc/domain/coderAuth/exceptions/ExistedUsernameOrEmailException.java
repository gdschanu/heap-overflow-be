package hanu.gdsc.domain.coderAuth.exceptions;

import hanu.gdsc.domain.share.exceptions.BusinessLogicException;

public class ExistedUsernameOrEmailException extends BusinessLogicException {
    public ExistedUsernameOrEmailException() {
        super("Username/email existed", "EXISTED_USERNAME_OR_EMAIL");
    }
}
