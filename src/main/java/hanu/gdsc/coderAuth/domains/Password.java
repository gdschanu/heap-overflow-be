package hanu.gdsc.coderAuth.domains;

import hanu.gdsc.share.error.BusinessLogicError;

public class Password {
    private String value;

    public Password(String value) {
        this.value = value;
        if (value.length() < 8) {
            throw new BusinessLogicError("Password needs at least 8 characters", "INVALID_PASSWORD");
        }
        if (!containsLetter(value) || !containsNumber(value)) {
            throw new BusinessLogicError("Password need both number and letter", "INVALID_PASSWORD");
        }
    }

    private boolean containsNumber(String value) {
        int count = 0;
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c <= '9' && c >= '0') {
                count++;
            }
        }
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean containsLetter(String value) {
        int count = 0;
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c <= 'z' && c >= 'a' || c <= 'Z' && c >= 'A') {
                count++;
            }
        }
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return value;
    }  
    
}
