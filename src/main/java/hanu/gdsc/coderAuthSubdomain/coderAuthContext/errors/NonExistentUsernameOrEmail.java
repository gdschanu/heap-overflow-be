package hanu.gdsc.coderAuthSubdomain.coderAuthContext.errors;

import hanu.gdsc.share.error.BusinessLogicError;

public class NonExistentUsernameOrEmail extends BusinessLogicError {
    public NonExistentUsernameOrEmail() {
        super("Username/email does not exist", "NON-EXISTENT_USERNAME_OR_EMAIL");
    }
}
