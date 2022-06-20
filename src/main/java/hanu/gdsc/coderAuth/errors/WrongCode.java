package hanu.gdsc.coderAuth.errors;

import hanu.gdsc.share.error.BusinessLogicError;

public class WrongCode extends BusinessLogicError {
    public WrongCode() {
        super("Your code is wrong", "WRONG_CODE");
    }
    
}
