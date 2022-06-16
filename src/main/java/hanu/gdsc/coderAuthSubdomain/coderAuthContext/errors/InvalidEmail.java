package hanu.gdsc.coderAuthSubdomain.coderAuthContext.errors;

import hanu.gdsc.share.error.BusinessLogicError;

public class InvalidEmail extends BusinessLogicError {
    public InvalidEmail() {
        super("Your email is invalid", "INVALID_EMAIL");
    }   
}
