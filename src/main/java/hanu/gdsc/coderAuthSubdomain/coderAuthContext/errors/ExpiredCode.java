package hanu.gdsc.coderAuthSubdomain.coderAuthContext.errors;

import hanu.gdsc.share.error.BusinessLogicError;

public class ExpiredCode extends BusinessLogicError {
    public ExpiredCode(String message) {
        super(message, "EXPIRED_CODE");
    } 
}
