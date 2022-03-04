package hanu.gdsc.coderAuth.services.signUp;

import hanu.gdsc.share.error.BusinessLogicError;

public class Password {
    private String value;

    public Password(String value) {
        if (value.length() < 8) {
            throw new BusinessLogicError("Password cần tối thiểu 8 kí tự");
        }
        if (!containsLetter(value) || !containsNumber(value)) {
            throw new BusinessLogicError("Password cần chứa cả số và chữ");
        }
    }

    private boolean containsNumber(String value) {
        int count = 0;
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c <= 9 && c >= 0) {
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
}
