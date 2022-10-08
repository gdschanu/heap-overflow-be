package hanu.gdsc.coderAuth.exceptions;

import hanu.gdsc.share.exceptions.BusinessLogicException;

public class NonExistentUsernameOrEmailException extends BusinessLogicException {
    public NonExistentUsernameOrEmailException() {
        super("Username/email does not exist", "NON-EXISTENT_USERNAME_OR_EMAIL");
    }
}
