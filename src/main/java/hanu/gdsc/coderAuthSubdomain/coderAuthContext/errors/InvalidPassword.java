package hanu.gdsc.coderAuthSubdomain.coderAuthContext.errors;

import hanu.gdsc.share.error.BusinessLogicError;

public class InvalidPassword extends BusinessLogicError { 
    public InvalidPassword(String message) {
        super(message, "INVALID_PASSWORD");
    } 
}
