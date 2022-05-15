package hanu.gdsc.coderAuth.errors;

import hanu.gdsc.share.error.BusinessLogicError;

public class UnconfirmedEmail extends BusinessLogicError { 
    public UnconfirmedEmail() {
        super("You haven't confirm your email", "UNCONFIRMED_MAIL");
    }
}
