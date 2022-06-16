package hanu.gdsc.coderAuthSubdomain.coderAuthContext.errors;

import hanu.gdsc.share.error.BusinessLogicError;

public class WrongPassword extends BusinessLogicError{
    public WrongPassword() {
        super("Your password is wrong", "WRONG_PASSWORD");
    }
}
