package hanu.gdsc.coder.domains;

import hanu.gdsc.share.error.BusinessLogicError;

public class Phone {
    private String value;

    public Phone(String value) {
        if(value.length() == 10) {
            this.value = value;
        } else {
            throw new BusinessLogicError("Invalid phone number", null);
        }
    }
}
