package hanu.gdsc.coderAuth_coderAuth.errors;

import hanu.gdsc.share.error.BusinessLogicError;

public class InvalidToken extends BusinessLogicError {
    public InvalidToken() {
        super("Token is invalid", "INVALID_TOKEN");
    }
}
