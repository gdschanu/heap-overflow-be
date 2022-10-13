package hanu.gdsc.coderAuth.exceptions;

import hanu.gdsc.share.exceptions.BusinessLogicException;

public class ExistedUsernameOrEmailException extends BusinessLogicException {
    public ExistedUsernameOrEmailException() {
        super("Username/email existed", "EXISTED_USERNAME_OR_EMAIL");
    }
}
