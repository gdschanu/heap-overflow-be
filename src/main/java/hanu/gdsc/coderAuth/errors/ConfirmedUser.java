package hanu.gdsc.coderAuth.errors;

import hanu.gdsc.share.error.BusinessLogicError;

public class ConfirmedUser extends BusinessLogicError {
    public ConfirmedUser() {
        super("You already confirm mail registration", "CONFIRMED_USER");
    }
}
