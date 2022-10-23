package hanu.gdsc.domain.coderAuth.models;

import hanu.gdsc.domain.share.exceptions.InvalidInputException;

public class Username {
    private String value;

    public Username(String value) throws InvalidInputException {
        this.value = value;
        if(value.length() < 8) {
            throw new InvalidInputException("Username needs at least 8 characters");
        }
        if (Email.isValidEmail(value)) {
            throw new InvalidInputException("Username can't be in the form of email");
        }
    }
    @Override
    public String toString() {
        return value;
    }  
}
