package hanu.gdsc.coderAuth.domains;

import hanu.gdsc.share.error.BusinessLogicError;

public class Email {
    private String value;

    public Email(String value) {
        this.value = value;
        if(!isValidEmail(value)) {
            throw new BusinessLogicError("Email không hợp lệ", "INVALID_EMAIL");
        }
    }

    public static boolean isValidEmail(String value) {
            String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(value);
           return m.matches();      
    }

    @Override
    public String toString() {
        return value;
    }    
}
