package hanu.gdsc.coderAuth.domains;

import hanu.gdsc.share.error.BusinessLogicError;

public class Username {
    private String value;

    public Username(String value) {
        this.value = value;
        if(value.length() < 8) {
            throw new BusinessLogicError("Username needs at least 8 characters", "INVALID_USERNAME");
        }
        if (Email.isValidEmail(value)) {
            throw new BusinessLogicError("Username can't be in the form of email", "INVALID_USERNAME");
        }
    }
    @Override
    public String toString() {
        return value;
    }  
}
