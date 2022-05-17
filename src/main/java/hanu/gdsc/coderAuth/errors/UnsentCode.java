package hanu.gdsc.coderAuth.errors;

import hanu.gdsc.share.error.BusinessLogicError;

public class UnsentCode extends BusinessLogicError {
    public UnsentCode() {
        super("You haven't sent email yet", "UNSENT_CODE");
    }
}
