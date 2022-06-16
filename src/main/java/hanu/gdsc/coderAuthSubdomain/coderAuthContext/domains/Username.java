package hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains;

import hanu.gdsc.coderAuthSubdomain.coderAuthContext.errors.InvalidUsername;

public class Username {
    private String value;

    public Username(String value) {
        this.value = value;
        if(value.length() < 8) {
            throw new InvalidUsername("Username needs at least 8 characters");
        }
        if (Email.isValidEmail(value)) {
            throw new InvalidUsername("Username can't be in the form of email");
        }
    }
    @Override
    public String toString() {
        return value;
    }  
}
