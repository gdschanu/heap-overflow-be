package hanu.gdsc.coderAuth.errors;

import hanu.gdsc.share.error.BusinessLogicError;

public class ExpiredSession extends BusinessLogicError {
    public ExpiredSession() {
        super("Session is expired", "EXPIRED_SESSION");
    }
    
}
