package hanu.gdsc.coderAuth_coderAuth.errors;

import hanu.gdsc.share.error.BusinessLogicError;

public class WrongEmail extends BusinessLogicError {
    public WrongEmail() {
        super("Your email is wrong","WRONG_EMAIL");
    }
}
