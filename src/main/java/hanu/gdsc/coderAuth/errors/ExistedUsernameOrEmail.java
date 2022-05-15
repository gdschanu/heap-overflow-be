package hanu.gdsc.coderAuth.errors;

import hanu.gdsc.share.error.BusinessLogicError;

public class ExistedUsernameOrEmail extends BusinessLogicError {
    public ExistedUsernameOrEmail() {
        super("Username/email existed", "EXISTED_USERNAME_OR_EMAIL");
    }
}
