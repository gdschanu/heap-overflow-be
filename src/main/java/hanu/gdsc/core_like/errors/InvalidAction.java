package hanu.gdsc.core_like.errors;

import hanu.gdsc.share.error.BusinessLogicError;

public class InvalidAction extends BusinessLogicError{

    public InvalidAction(String message) {
        super(message, "INVALID_ACTION");
    }
    
}
