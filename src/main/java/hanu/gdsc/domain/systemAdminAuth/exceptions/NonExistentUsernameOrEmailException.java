package hanu.gdsc.domain.systemAdminAuth.exceptions;

import hanu.gdsc.domain.share.exceptions.BusinessLogicException;

public class NonExistentUsernameOrEmailException extends BusinessLogicException {
    public NonExistentUsernameOrEmailException() {
        super("Username/email does not exist", "NON-EXISTENT_USERNAME_OR_EMAIL");
    }
}
