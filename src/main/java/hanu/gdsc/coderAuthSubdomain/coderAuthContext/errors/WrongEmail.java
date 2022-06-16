package hanu.gdsc.coderAuthSubdomain.coderAuthContext.errors;

import hanu.gdsc.share.error.BusinessLogicError;

public class WrongEmail extends BusinessLogicError {
    public WrongEmail() {
        super("Your email is wrong","WRONG_EMAIL");
    }
}
